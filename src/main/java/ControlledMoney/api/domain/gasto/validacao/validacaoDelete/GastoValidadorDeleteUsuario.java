package ControlledMoney.api.domain.gasto.validacao.validacaoDelete;

import ControlledMoney.api.domain.usuario.Usuario;

public interface GastoValidadorDeleteUsuario {
    
    public void validar(Long id, Usuario usuario);
}
