package ControlledMoney.api.domain.lucro.validacao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.lucro.validacao.validacaoPatch.LucroValidadorPatch;

@Service
public class LucroValidador {
    
    @Autowired
    private List<LucroValidadorPatch> validador;

    public void validarPath(LucroAlterarDTO dados){
        validador.forEach(v -> v.validar(dados));
    }

}
