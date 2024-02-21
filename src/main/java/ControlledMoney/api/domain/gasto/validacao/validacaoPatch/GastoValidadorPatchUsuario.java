package ControlledMoney.api.domain.gasto.validacao.validacaoPatch;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface GastoValidadorPatchUsuario {

    public void validar(GastoAlterarDTO dados, Usuario usuario);

}
