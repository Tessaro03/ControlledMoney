package ControlledMoney.api.domain.parcela.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ParcelaRepository;

@Service
public class ValidarSeParcelaDeletadaEDeUsuario implements ParcelaValidarDeleteUsuario{

    @Autowired
    private ParcelaRepository parcelaRepository;


    @Override
    public void validar(Long id, Usuario usuario) {
        var parcela = parcelaRepository.getReferenceById(id);
        if (!parcela.getConta().getUsuario().equals(usuario)) {    
            throw new ValidacaoException("Parcela não pertence ao Usuario");
        }
        
    }
    
}
