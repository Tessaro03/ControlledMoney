package ControlledMoney.api.domain.lucro.validacao.validacaoDelete;

import ControlledMoney.api.domain.usuario.Usuario;

public interface LucroValidadorDeleteUsuario {
    
    public void validar(Long id, Usuario usuario);

}
