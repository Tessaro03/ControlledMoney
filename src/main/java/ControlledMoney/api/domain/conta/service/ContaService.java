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
        Long lucroTotal = lucroRepository.lucrosPagoIdConta(conta.getId());
        Long lucroPrevisto = lucroRepository.lucrosPrevistoIdConta(conta.getId());

        Long gastoTotal = gastoRepository.gastosPagoIdConta(conta.getId());
        Long gastoPrevisto = gastoRepository.gastoPrevistoIdConta(conta.getId());

        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }

    public ContaOutputDTO dadosContaPorMes(Long id) {
        Conta conta = contaRepository.getReferenceById(id);
        LocalDate dataAtual = LocalDate.now();
        Long lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());
        Long lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());

        Long gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());
        Long gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), dataAtual.getMonthValue(), dataAtual.getYear());


        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }
    
    public ContaOutputDTO dadosContaPorMesEAno(Long id, int mes, int ano) {
        Conta conta = contaRepository.getReferenceById(id);
        Long lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Long lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        Long gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Long gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);


        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        return new ContaOutputDTO(conta);
    }
}                                                                              
