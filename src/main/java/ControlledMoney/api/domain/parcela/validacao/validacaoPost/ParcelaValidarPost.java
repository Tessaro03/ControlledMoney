package ControlledMoney.api.domain.parcela.validacao.validacaoPost;

import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;

public interface ParcelaValidarPost {
    
    public void validar(ParcelaInputDTO dados, Usuario usuario);
}
