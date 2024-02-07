package ControlledMoney.api.domain.autenticacao.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginInputDTO(

    @NotBlank
    String login, 

    @NotBlank
    String senha

) {
    
}
