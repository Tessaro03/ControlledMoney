package ControlledMoney.api.domain.gasto.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoOutputDTO;
import ControlledMoney.api.domain.gasto.validacao.GastoValidador;
import ControlledMoney.api.infra.token.TokenUsuario;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class GastoService {
    
    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private TokenUsuario tokenUsuario;

    @Autowired
    private GastoValidador validador;


    public List<GastoOutputDTO> listarGastos(HttpServletRequest request){
        var usuario = tokenUsuario.usuarioToken(request);
        List<Gasto> gastos = gastoRepository.gastoPorIdUsuario(usuario.getId());
        return gastos.stream().map(GastoOutputDTO::new).collect(Collectors.toList());
    }

    public void criarGasto(HttpServletRequest request, GastoInputDTO dados){
        var usuario = tokenUsuario.usuarioToken(request);
        validador.validadorPost(usuario, dados);

        var conta = contaRepository.getReferenceById(dados.idConta());        
        var gasto = new Gasto(dados, conta);
        gastoRepository.save(gasto);
        adicionarGasto(dados);
    }

    public void adicionarGasto(GastoInputDTO dados){
        var conta = contaRepository.getReferenceById(dados.idConta());      
        if (dados.pago()) {
            conta.sacar(dados.valor());
            contaRepository.save(conta);
        }
    }

    public void alterarGasto(HttpServletRequest request,GastoAlterarDTO dados) {
        var usuario = tokenUsuario.usuarioToken(request);

        validador.validadorPath(usuario, dados);
        var gasto = gastoRepository.getReferenceById(dados.idGasto());

        if (dados.valor() != null) {gasto.setValor(dados.valor());}
        if (dados.data() != null) { gasto.setData(dados.data());}
        if (dados.motivo() != null) { gasto.setMotivo(dados.motivo());}
        
        if (dados.pago() != null) { 
            gasto.setPago(dados.pago());
            var conta = contaRepository.getReferenceById(gasto.getConta().getId());
            if (gasto.getPago() ) {
                conta.sacar(gasto.getValor());
            } else {
                conta.depositar(gasto.getValor());
            }
        }   
        gastoRepository.save(gasto);
    }

    public void deletarGasto(HttpServletRequest request, Long id) {
        var usuario = tokenUsuario.usuarioToken(request);

        validador.validarDelete(id, usuario);
        var gasto = gastoRepository.getReferenceById(id);
        var conta = contaRepository.getReferenceById(gasto.getConta().getId());

        if (gasto.getPago()) {
            conta.depositar(gasto.getValor());
        }
        gastoRepository.delete(gasto);
    }

}
