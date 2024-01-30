package ControlledMoney.api.domain.gasto.validacao.validacaoPatch;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;

public interface GastoValidadorPatch {

    void validar(GastoAlterarDTO dados);

}
