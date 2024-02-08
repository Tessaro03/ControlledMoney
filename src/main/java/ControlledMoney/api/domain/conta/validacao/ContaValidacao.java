package ControlledMoney.api.domain.conta.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.conta.validacao.validacaoPost.ContaValidacaoPost;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class ContaValidacao {

    @Autowired
    private List<ContaValidacaoPost> validacaoPost;
    
    public void validarPost(Usuario usuario, ContaInputDTO dados){
        validacaoPost.forEach(v -> v.validar(usuario, dados));
    }

}
