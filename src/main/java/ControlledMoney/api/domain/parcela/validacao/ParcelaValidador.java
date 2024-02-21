package ControlledMoney.api.domain.parcela.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.domain.parcela.validacao.validacaoDelete.ParcelaValidarDeleteUsuario;
import ControlledMoney.api.domain.parcela.validacao.validacaoPost.ParcelaValidarPost;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class ParcelaValidador {
    
    @Autowired
    private List<ParcelaValidarDeleteUsuario> parcelaValidarDeleteUsuarios;

    @Autowired
    private List<ParcelaValidarPost> parcelaValidarPost;

    public void validarDelete(Long id, Usuario usuario){
        parcelaValidarDeleteUsuarios.forEach(v -> v.validar(id, usuario));
    }

    public void validarPost(ParcelaInputDTO dados, Usuario usuario){
        parcelaValidarPost.forEach(v -> v.validar(dados, usuario));
    }
}
