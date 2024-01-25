package ControlledMoney.api.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ControlledMoney.api.domain.conta.Conta;
import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.conta.service.ContaService;
import ControlledMoney.api.repository.ContaRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/conta")
public class ContaController {
    
    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private ContaService contaService;


    @GetMapping("/{id}")
    public ResponseEntity verConta(@PathVariable  Long id){
        var conta = contaService.dadosConta(id, 0 ,0);
        return ResponseEntity.ok(conta);
    }


    @GetMapping("/{id}/mes/{mes}/ano/{ano}")
    public ResponseEntity verContaMesAno(@PathVariable Long id, @PathVariable int mes, @PathVariable int ano){
        var conta = contaService.dadosConta(id, mes, ano);
        return ResponseEntity.ok(conta);
    }


    @PostMapping
    public void criarConta(@RequestBody @Valid ContaInputDTO dados){
        var conta = new Conta(dados);
        contaRepository.save(conta);
    }

}
