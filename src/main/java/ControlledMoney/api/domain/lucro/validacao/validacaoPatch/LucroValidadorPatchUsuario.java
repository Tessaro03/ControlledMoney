package ControlledMoney.api.domain.lucro.validacao.validacaoPatch;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface LucroValidadorPatchUsuario {
    
    public void validar(Usuario usuario, LucroAlterarDTO dados);

}
