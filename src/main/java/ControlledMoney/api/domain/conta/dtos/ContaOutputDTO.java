package ControlledMoney.api.domain.conta.dtos;

import ControlledMoney.api.domain.conta.Conta;

public record ContaOutputDTO(
    Long id, 
    Long saldo,
    Long totalLucro,
    Long totalGasto,
    Long previstoLucro,
    Long previstoGasto
     ){

    public ContaOutputDTO(Conta conta){
        this(conta.getId(), conta.getSaldo(), conta.getTotalLucro(), 
        conta.getTotalGasto(), conta.getPrevistoLucro(), conta.getPrevistoGasto());
    }

}
