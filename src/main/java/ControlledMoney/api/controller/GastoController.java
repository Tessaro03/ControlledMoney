package ControlledMoney.api.controller;

import java.util.List;

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

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoOutputDTO;
import ControlledMoney.api.domain.gasto.service.GastoService;
import ControlledMoney.api.repository.GastoRepository;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/gasto")
public class GastoController {
    
    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private GastoService gastoService;

    @GetMapping
    public ResponseEntity listarGastos(){
        List<Gasto> gastos = gastoRepository.findAll();
        return ResponseEntity.ok(gastos.stream().map(GastoOutputDTO::new));
    }
    
   @PostMapping
   public void criarGasto(@RequestBody @Valid GastoInputDTO dados){
        gastoService.criarGasto(dados);
   }

   @PatchMapping
   public void alterarGasto(@RequestBody @Valid GastoAlterarDTO dados){
        gastoService.alterarGasto(dados);
   }

   @DeleteMapping("/{id}")
   public ResponseEntity deletarGasto(@PathVariable Long id){
     gastoService.deletarGasto(id);
     return ResponseEntity.ok().build();
   }
}
