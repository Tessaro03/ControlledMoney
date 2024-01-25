package ControlledMoney.api.domain.gasto.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;

@Service
public class GastoService {
    
    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private ContaRepository contaRepository;

    public void criarGasto(GastoInputDTO dados){
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

    public void alterarGasto(GastoAlterarDTO dados) {
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

    public void deletarGasto(Long id) {
        var gasto = gastoRepository.getReferenceById(id);
        var conta = contaRepository.getReferenceById(gasto.getConta().getId());

        if (gasto.getPago()) {
            conta.depositar(gasto.getValor());
        }
        gastoRepository.delete(gasto);
    }

}
