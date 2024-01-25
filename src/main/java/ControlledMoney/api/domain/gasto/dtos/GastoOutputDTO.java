package ControlledMoney.api.domain.gasto.dtos;

import java.time.LocalDate;

import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.parcela.Parcela;

public record GastoOutputDTO(
    Long id,
    Long idConta, 
    String motivo,
    Double valor,
    Boolean pago,
    LocalDate data,
    Long numeroParcela,
    Parcela parcela
 ) {
    
    public GastoOutputDTO(Gasto gasto){
        this(gasto.getId(), gasto.getConta().getId(),gasto.getMotivo(), gasto.getValor(), gasto.getPago(), gasto.getData(), gasto.getNumeroParcela(),gasto.getParcela());
    }
}
