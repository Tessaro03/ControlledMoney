package ControlledMoney.api.domain.lucro.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LucroInputDTO(
    
    @NotNull
    Long idConta,
    
    @NotBlank
    String motivo,

    @NotNull
    Long valor,
    
    @NotBlank
    Boolean recebido,
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data
    ) {
    
}
