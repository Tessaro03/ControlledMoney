package ControlledMoney.api.domain.lucro.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.lucro.validacao.validacaoPatch.LucroValidadorPatch;
import ControlledMoney.api.domain.lucro.validacao.validacaoPost.LucroValidadorPost;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class LucroValidador {
    
    @Autowired
    private List<LucroValidadorPatch> validadorPatch;

    @Autowired
    private List<LucroValidadorPost> validadorPost;

    public void validarPath(LucroAlterarDTO dados){
        validadorPatch.forEach(v -> v.validar(dados));
    }

    public void validarPost(Usuario usuario, LucroInputDTO dados){
        validadorPost.forEach(v -> v.validar(usuario, dados));
    }
}
