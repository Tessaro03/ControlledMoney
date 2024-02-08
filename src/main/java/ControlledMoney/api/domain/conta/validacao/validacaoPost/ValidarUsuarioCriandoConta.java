package ControlledMoney.api.domain.conta.validacao.validacaoPost;

import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;

@Service
public class ValidarUsuarioCriandoConta implements ContaValidacaoPost{

    @Override
    public void validar(Usuario usuario, ContaInputDTO dados) {
        if (usuario.getId() != dados.idUsuario()) {
            throw new ValidacaoException("Usuário deve criar conta com seu id");
        }
    }

    
    
}
