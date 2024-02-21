package ControlledMoney.api.domain.parcela.validacao.validacaoDelete;

import ControlledMoney.api.domain.usuario.Usuario;

public interface ParcelaValidarDeleteUsuario {
    
    public void validar(Long id, Usuario usuario);

}
