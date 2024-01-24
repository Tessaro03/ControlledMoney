package ControlledMoney.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ControlledMoney.api.domain.lucro.Lucro;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroOutputDTO;
import ControlledMoney.api.domain.lucro.service.LucroService;
import ControlledMoney.api.repository.LucroRepository;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/lucro")
public class LucroController {
    
    @Autowired
    private LucroRepository lucroRepository;

    @Autowired 
    private LucroService service;

    @GetMapping
    @Transactional
    public ResponseEntity listarGastos(){
        List<Lucro> lucros = lucroRepository.findAll();
        return ResponseEntity.ok(lucros.stream().map(LucroOutputDTO::new));
    }

    @PostMapping
    public void CriarLucro(@RequestBody LucroInputDTO dados){
        service.adicionarLucro(dados);
        
    }

}
