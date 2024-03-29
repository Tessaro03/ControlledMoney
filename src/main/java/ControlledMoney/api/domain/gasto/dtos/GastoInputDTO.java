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
    Double valor,
  
    @NotNull
    Boolean pago,

    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data

) {

}
