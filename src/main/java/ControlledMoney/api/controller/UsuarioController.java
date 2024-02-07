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

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.domain.usuario.dtos.UsuarioInputDTO;
import ControlledMoney.api.domain.usuario.dtos.UsuarioOutputDTO;
import ControlledMoney.api.repository.UsuarioRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;



    @GetMapping("/{id}")
    @Transactional
    public ResponseEntity buscarUsuario(@PathVariable Long id){
        var usuario = repository.getReferenceById(id);
        return ResponseEntity.ok(new UsuarioOutputDTO(usuario));
    }

    @PostMapping
    public void cadastrar(@RequestBody @Valid UsuarioInputDTO dados){
        var usuario = new Usuario(dados);
        repository.save(usuario);
    }


    
    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity deletar(HttpServletRequest request, @PathVariable Long id){
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
