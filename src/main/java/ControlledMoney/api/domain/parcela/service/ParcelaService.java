package ControlledMoney.api.domain.parcela.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.parcela.Parcela;
import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.domain.parcela.validacao.ParcelaValidador;
import ControlledMoney.api.infra.token.TokenUsuario;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import ControlledMoney.api.repository.ParcelaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class ParcelaService {
    
    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired 
    private ContaRepository contaRepository;

    @Autowired
    private ParcelaValidador validador;

    @Autowired 
    private TokenUsuario tokenUsuario;

    public void criarParcela(HttpServletRequest request, ParcelaInputDTO dados){
        var usuario = tokenUsuario.usuarioToken(request);

        validador.validarPost(dados, usuario);

        GastoInputDTO gastoDTO = new GastoInputDTO(dados.idConta(), dados.motivo(), dados.valor() / dados.parcelas() , dados.pago(), dados.data());
        var conta = contaRepository.getReferenceById(dados.idConta());
        var parcela = new Parcela(dados);
        parcelaRepository.save(parcela); 


        for (Long i = 0l; i < dados.parcelas() ; i++){
            var gasto = new Gasto(gastoDTO, conta);
            gasto.setParcela(parcela);
            gasto.setNumeroParcela(i);
            gasto.setData(dados.data().plusMonths(i));
            gastoRepository.save(gasto);
        }
    }

    @Transactional
    public void deletarParcela(HttpServletRequest request, Long id) {
        var usuario = tokenUsuario.usuarioToken(request);

        validador.validarDelete(id, usuario);
        gastoRepository.deletarGastoIdParcela(id);
        parcelaRepository.deleteById(id);
    }



}
