package ControlledMoney.api.domain.conta.validacao.validacaoPost;

import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface ContaValidacaoPost {

    public void validar(Usuario usuario,ContaInputDTO dados);

}
