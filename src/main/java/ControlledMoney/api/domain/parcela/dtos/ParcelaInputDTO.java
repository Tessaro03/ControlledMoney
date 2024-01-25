package ControlledMoney.api.domain.parcela.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ParcelaInputDTO(

    @NotNull
    Long idConta,

    @NotBlank
    String motivo,

    @NotNull
    Double valor,
    
    Long parcelas,

    @NotNull
    Boolean pago,

    @NotNull
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data

    
) {
    
}
