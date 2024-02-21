package ControlledMoney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.lucro.service.LucroService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/lucro")
public class LucroController {
    
    @Autowired 
    private LucroService service;

    @GetMapping
    @Transactional
    public ResponseEntity listarGastos(HttpServletRequest request){
        var lucros = service.listarLucro(request);
        return ResponseEntity.ok(lucros);
    }

    @PostMapping
    public void CriarLucro(HttpServletRequest request,@RequestBody @Valid LucroInputDTO dados){
        service.adicionarLucro(request, dados);
    }

    @PatchMapping
    public void alterarLucro(HttpServletRequest request, @RequestBody @Valid LucroAlterarDTO dados){
        service.alterarLucro(request,dados);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletarLucro(HttpServletRequest request,@PathVariable Long id){
        service.deletarLucro(request,id);
        return ResponseEntity.ok().build();
    }

}
