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

    public ContaOutputDTO dadosConta(Long id, int mes, int ano) {
        if (mes == 0 || ano == 0) {
            LocalDate dataAtual = LocalDate.now();
            mes = dataAtual.getMonthValue();
            ano = dataAtual.getYear();
        }

        Conta conta = contaRepository.getReferenceById(id);
        Double lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        Double gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        if (lucroTotal == null) { lucroTotal = 0d;}
        if (lucroPrevisto == null) { lucroPrevisto = 0d;}
        if (gastoPrevisto == null) { gastoPrevisto = 0d;}
        if (gastoTotal == null) { gastoTotal = 0d;}

        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        conta.definirSaldoPrevisto(gastoPrevisto, lucroPrevisto);
        return new ContaOutputDTO(conta);
    }

    

 
}                                                                              
