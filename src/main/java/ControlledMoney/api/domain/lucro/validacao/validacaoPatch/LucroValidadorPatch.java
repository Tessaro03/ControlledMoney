package ControlledMoney.api.domain.lucro.validacao.validacaoPatch;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;

public interface LucroValidadorPatch {

    public void validar(LucroAlterarDTO dados);
    
}