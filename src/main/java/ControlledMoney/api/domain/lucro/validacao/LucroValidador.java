package ControlledMoney.api.domain.lucro.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.lucro.validacao.validacaoDelete.LucroValidadorDeleteUsuario;
import ControlledMoney.api.domain.lucro.validacao.validacaoPatch.LucroValidadorPatch;
import ControlledMoney.api.domain.lucro.validacao.validacaoPatch.LucroValidadorPatchUsuario;
import ControlledMoney.api.domain.lucro.validacao.validacaoPost.LucroValidadorPostUsuario;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class LucroValidador {
    
    @Autowired
    private List<LucroValidadorPatch> validadorPatch;

    @Autowired
    private List<LucroValidadorPatchUsuario> validadorPatchUsuario;

    @Autowired
    private List<LucroValidadorPostUsuario> validadorPost;

    @Autowired
    private List<LucroValidadorDeleteUsuario> validarDeleteUsuario;

    public void validarPath(Usuario usuario, LucroAlterarDTO dados){
        validadorPatchUsuario.forEach(v -> v.validar(usuario,dados));
        validadorPatch.forEach(v -> v.validar(dados));
    }

    public void validarPost(Usuario usuario, LucroInputDTO dados){
        validadorPost.forEach(v -> v.validar(usuario, dados));
    }

    public void validarDelete(Usuario usuario, Long id){
        validarDeleteUsuario.forEach( v -> v.validar(id, usuario));
    }
}
