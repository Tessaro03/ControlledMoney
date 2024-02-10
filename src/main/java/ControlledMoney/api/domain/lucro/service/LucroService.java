package ControlledMoney.api.domain.lucro.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.Lucro;
import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroOutputDTO;
import ControlledMoney.api.domain.lucro.validacao.LucroValidador;
import ControlledMoney.api.infra.token.TokenUsuario;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.LucroRepository;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class LucroService {

    @Autowired
    public ContaRepository contaRepository;

    @Autowired
    private LucroRepository lucroRepository;

    @Autowired
    private LucroValidador validador;

    @Autowired
    private TokenUsuario tokenUsuario;

    public List<LucroOutputDTO> listarLucro(HttpServletRequest request){
        var usuario = tokenUsuario.usuarioToken(request);
        List<Lucro> lucros = lucroRepository.LucroPorIdUsuario(usuario.getId());
        return lucros.stream().map(LucroOutputDTO::new).collect(Collectors.toList());
    }
    
    public void adicionarLucro(HttpServletRequest request, LucroInputDTO dados){
        var usuario = tokenUsuario.usuarioToken(request);
        validador.validarPost(usuario, dados);
        
        var conta = contaRepository.getReferenceById(dados.idConta());
        var lucro = new Lucro(dados, conta);
        if (dados.recebido()) {
            conta.depositar(lucro.getValor());
        }
        lucroRepository.save(lucro);
    }

    public void alterarLucro(HttpServletRequest request,LucroAlterarDTO dados) {
        var usuario = tokenUsuario.usuarioToken(request);

        validador.validarPath(usuario,dados);
        var lucro = lucroRepository.getReferenceById(dados.idLucro());
        if (dados.valor() != null) {lucro.setValor(dados.valor());}
        if (dados.data() != null) { lucro.setData(dados.data());}
        if (dados.motivo() != null) { lucro.setMotivo(dados.motivo());}
        if (dados.recebido() != null) { 
            lucro.setRecebido(dados.recebido());
            var conta = contaRepository.getReferenceById(lucro.getConta().getId());
            if (lucro.getRecebido()) {
                conta.depositar(lucro.getValor());
            } else {
                conta.sacar(lucro.getValor());
            }
        }
        lucroRepository.save(lucro);
    }

    public void deletarLucro(Long id) {
        var lucro = lucroRepository.getReferenceById(id);
        var conta = contaRepository.getReferenceById(lucro.getConta().getId());
        if (lucro.getRecebido()) {
            conta.sacar(lucro.getValor());
        }
        lucroRepository.delete(lucro);
    }




}
