package ControlledMoney.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.domain.parcela.service.ParcelaService;
import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/parcela")
public class ParcelaController {

    @Autowired 
    private ParcelaService parcelaService;

   

    @PostMapping
    public void criarParcela(HttpServletRequest request, @RequestBody ParcelaInputDTO dados){
        parcelaService.criarParcela(request, dados);
    }
    

    @DeleteMapping("/{id}")
    public ResponseEntity deletarParcela(HttpServletRequest request,@PathVariable Long id){
        parcelaService.deletarParcela(request, id);
        return ResponseEntity.ok().build();
    }
}
