package ControlledMoney.api.domain.lucro.dtos;

import java.time.LocalDate;

import ControlledMoney.api.domain.lucro.Lucro;

public record LucroOutputDTO( 
    Long id,
    Long idConta, 
    String motivo,
    Double valor,
    Boolean recebido,
    LocalDate data
) {
    
    public LucroOutputDTO(Lucro lucro){
        this(lucro.getId(),lucro.getConta().getId() ,lucro.getMotivo(), lucro.getValor(), lucro.getRecebido(), lucro.getData());
    }
}
