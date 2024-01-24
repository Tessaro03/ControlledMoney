package ControlledMoney.api.domain.conta.dtos;

import ControlledMoney.api.domain.conta.Conta;

public record ContaOutputDTO(
    Long id, 
    Double saldo,
    Double totalLucro,
    Double totalGasto,
    Double previstoLucro,
    Double previstoGasto
     ){

    public ContaOutputDTO(Conta conta){
        this(conta.getId(), conta.getSaldo(), conta.getTotalLucro(), 
        conta.getTotalGasto(), conta.getPrevistoLucro(), conta.getPrevistoGasto());
    }

}
