package ControlledMoney.api.domain.conta.service;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.conta.Conta;
import ControlledMoney.api.domain.conta.dtos.ContaOutputDTO;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import ControlledMoney.api.repository.LucroRepository;



@Service
public class ContaService {
    
    @Autowired 
    private LucroRepository lucroRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    public ContaOutputDTO dadosConta(Long id){
        Conta conta = contaRepository.getReferenceById(id);
        Double lucroTotal = lucroRepository.lucrosPagoIdConta(conta.getId());
        Double lucroPrevisto = lucroRepository.lucrosPrevistoIdConta(conta.getId());

        Double gastoTotal = gastoRepository.gastosPagoIdConta(conta.getId());
        Double gastoPrevisto = gastoRepository.gastoPrevistoIdConta(conta.getId());

        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }

    public ContaOutputDTO dadosContaPorMes(Long id) {
        Conta conta = contaRepository.getReferenceById(id);
        LocalDate dataAtual = LocalDate.now();
        Double lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());
        Double lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());

        Double gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());
        Double gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());


        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }
    
    public ContaOutputDTO dadosContaPorMesEAno(Long id, int mes, int ano) {
        Conta conta = contaRepository.getReferenceById(id);
        Double lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        Double gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);


        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }
}                                                                              
