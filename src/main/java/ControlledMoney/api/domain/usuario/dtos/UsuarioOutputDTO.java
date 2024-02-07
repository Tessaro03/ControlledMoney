package ControlledMoney.api.domain.usuario.dtos;

import ControlledMoney.api.domain.usuario.Usuario;


public record UsuarioOutputDTO(Long idUsuario, String nome) {
    
    public UsuarioOutputDTO(Usuario usuario){
        this(usuario.getId(), usuario.getNome());
    }


}
