package ControlledMoney.api.domain.parcela.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.parcela.Parcela;
import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import ControlledMoney.api.repository.ParcelaRepository;
import jakarta.transaction.Transactional;

@Service
public class ParcelaService {
    
    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired 
    private ContaRepository contaRepository;

    public void cirarParcela(ParcelaInputDTO dados){
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
    public void deletarParcela(Long id) {
        gastoRepository.deletarGastoIdParcela(id);
        parcelaRepository.deleteById(id);
    }


}
