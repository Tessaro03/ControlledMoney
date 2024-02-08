package ControlledMoney.api.domain.conta.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.conta.validacao.validacaoDelete.ContaValidacaoDelete;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class ContaValidacao {

    @Autowired
    private List<ContaValidacaoDelete> validacaoDelete;
    
    public void validarDelete(Usuario usuario, Long id){
        validacaoDelete.forEach(v -> v.validar(usuario, id));
    }

}
