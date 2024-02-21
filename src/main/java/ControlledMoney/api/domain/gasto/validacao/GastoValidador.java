package ControlledMoney.api.domain.gasto.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.gasto.validacao.validacaoDelete.GastoValidadorDeleteUsuario;
import ControlledMoney.api.domain.gasto.validacao.validacaoPatch.GastoValidadorPatch;
import ControlledMoney.api.domain.gasto.validacao.validacaoPatch.GastoValidadorPatchUsuario;
import ControlledMoney.api.domain.gasto.validacao.validacaoPost.GastoValidadorPostUsuario;
import ControlledMoney.api.domain.usuario.Usuario;

@Service
public class GastoValidador {
    
    @Autowired
    private List<GastoValidadorPatch> validarPath;

    @Autowired
    private List<GastoValidadorPatchUsuario> validarPatchUsuarios;

    @Autowired
    private List<GastoValidadorPostUsuario> validarPostUsuario;

    @Autowired
    private List<GastoValidadorDeleteUsuario> validadorDeleteUsuarios;
 
    public void validadorPath(Usuario usuario, GastoAlterarDTO dados){
        validarPath.forEach( v -> v.validar(dados));
        validarPatchUsuarios.forEach(v -> v.validar(dados, usuario));
    }

    public void validadorPost(Usuario usuario, GastoInputDTO dados){
        validarPostUsuario.forEach(v -> validadorPost(usuario, dados));
    }

    public void validarDelete(Long id, Usuario Usuario){
        validadorDeleteUsuarios.forEach(v -> v.validar(id, Usuario));
    }

}
