package ControlledMoney.api.domain.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record UsuarioInputDTO(

    @NotBlank
    String login,

    @NotBlank 
    String senha,

    @NotBlank 
    String email,

    @NotBlank 
    String nome

) {

}
