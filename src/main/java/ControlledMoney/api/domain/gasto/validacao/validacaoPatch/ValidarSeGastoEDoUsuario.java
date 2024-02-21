package ControlledMoney.api.domain.gasto.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.GastoRepository;

@Service
public class ValidarSeGastoEDoUsuario implements GastoValidadorPatchUsuario{

    @Autowired
    private GastoRepository gastoRepository;

    @Override
    public void validar(GastoAlterarDTO dados, Usuario usuario) {
        var gasto = gastoRepository.getReferenceById(dados.idGasto());
        if (!gasto.getConta().getUsuario().equals(usuario) ) {
            throw new ValidacaoException("Gasto não pertence ao Usuario");
        }
    }
    
}
