package ControlledMoney.api.domain.lucro.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.LucroRepository;

@Service
public class ValidarSeLucroDeletadoEDeUsuario implements LucroValidadorDeleteUsuario {
    
    @Autowired
    private LucroRepository lucroRepository;

    @Override
    public void validar(Long id, Usuario usuario) {
        var lucro = lucroRepository.getReferenceById(id);
        if (!lucro.getConta().getUsuario().equals(usuario)) {
            
            throw new ValidacaoException("Lucro não pertence ao Usuario");
        }
    }
    
}
