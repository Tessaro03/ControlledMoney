package ControlledMoney.api.domain.conta.dtos;

import jakarta.validation.constraints.NotNull;

public record ContaInputDTO(
    

    @NotNull
    Double saldo
) 
{

}
