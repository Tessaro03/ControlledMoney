package ControlledMoney.api.domain.gasto.validacao.validacaoPost;

import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface GastoValidadorPostUsuario {
    
    public void validar(Usuario usuario, GastoInputDTO dados);

}
