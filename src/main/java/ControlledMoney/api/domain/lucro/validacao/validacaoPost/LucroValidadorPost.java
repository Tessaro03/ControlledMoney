package ControlledMoney.api.domain.lucro.validacao.validacaoPost;

import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface LucroValidadorPost {
    
    public void validar(Usuario usuario, LucroInputDTO dados);
}
