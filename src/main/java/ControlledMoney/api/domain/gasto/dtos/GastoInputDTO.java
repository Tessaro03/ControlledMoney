package ControlledMoney.api.domain.gasto.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record GastoInputDTO(
   
    @NotNull
    Long idConta,

    @NotBlank
    String motivo,
    
    @NotNull
    Long valor,
  
    @NotBlank
    Boolean pago,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data,

    Long parcelas,
    
    Boolean pagoParcela

) {

}
