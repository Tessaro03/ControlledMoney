package ControlledMoney.api.domain.gasto.service;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.Parcela;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;

import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import ControlledMoney.api.repository.ParcelaRepository;

@Service
public class GastoService {
    
    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    public void criarGasto(GastoInputDTO dados){
        var conta = contaRepository.getReferenceById(dados.idConta());        
        if (dados.parcelas() != null) {
            var parcela = new Parcela(dados);
            parcela.setValor(dados.parcelas() * dados.valor());
            parcelaRepository.save(parcela); 

            for (Long i = 1l; i <= dados.parcelas() ; i++){
                var gasto = new Gasto(dados, conta);
                                
                gasto.setParcela(parcela);
                gasto.setNumeroParcela(i);
                gasto.setData(dados.data().plusMonths(i));
                gastoRepository.save(gasto);
            }
        } else {
            var gasto = new Gasto(dados, conta);
            gastoRepository.save(gasto);
        }
    }

    public void adicionarGasto(GastoInputDTO dados){
        var conta = contaRepository.getReferenceById(dados.idConta());        
        if (dados.pago()) {
            conta.sacar(dados.valor());
        }
    }

}
