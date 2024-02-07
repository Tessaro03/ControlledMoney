package ControlledMoney.api.domain.usuario.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginInputDTO(

    @NotBlank
    String login, 

    @NotBlank
    String senha

) {
    
}
