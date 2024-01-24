package ControlledMoney.api.domain.lucro.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.Lucro;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.LucroRepository;

@Service
public class LucroService {

    @Autowired
    public ContaRepository contaRepository;

    @Autowired
    private LucroRepository lucroRepository;
    
    public void adicionarLucro(LucroInputDTO dados){
        var conta = contaRepository.getReferenceById(dados.idConta());
        var lucro = new Lucro(dados, conta);
        
        if (dados.recebido()) {
            conta.depositar(lucro.getValor());
        }
        lucroRepository.save(lucro);
    }


}
