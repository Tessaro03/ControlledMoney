package ControlledMoney.api.domain.gasto.dtos;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotNull;

public record GastoAlterarDTO(

    @NotNull
    Long idGasto,
    Double valor,
    Boolean pago,
    @JsonFormat(pattern = "dd/MM/yyyy")
    LocalDate data,
    String motivo

) {


}
