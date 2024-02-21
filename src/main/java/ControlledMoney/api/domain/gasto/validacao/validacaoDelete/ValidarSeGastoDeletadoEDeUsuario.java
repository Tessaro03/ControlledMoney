package ControlledMoney.api.domain.gasto.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.GastoRepository;

@Service
public class ValidarSeGastoDeletadoEDeUsuario implements GastoValidadorDeleteUsuario{

    @Autowired
    private GastoRepository gastoRepository;

    @Override
    public void validar(Long id, Usuario usuario) {
        var gasto = gastoRepository.getReferenceById(id);
        if (!gasto.getConta().getUsuario().equals(usuario)) {
            throw new ValidacaoException("Gasto não pertence a usuario");        
        }
    }

    
}
