package ControlledMoney.api.domain.lucro.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record LucroAlterarDTO(

    @NotNull
    Long idLucro,
    Double valor,
    Boolean recebido,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data,
    String motivo
) {

}
