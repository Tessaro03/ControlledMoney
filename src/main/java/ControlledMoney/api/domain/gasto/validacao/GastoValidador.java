package ControlledMoney.api.domain.gasto.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.gasto.validacao.validacaoPatch.GastoValidadorPatch;

@Service
public class GastoValidador {
    
    @Autowired
    private List<GastoValidadorPatch> validarPath;
 
    public void validadorPath(GastoAlterarDTO dados){
        validarPath.forEach( v -> v.validar(dados));
    }
}
