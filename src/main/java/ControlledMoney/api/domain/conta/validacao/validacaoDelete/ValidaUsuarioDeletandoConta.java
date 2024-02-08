package ControlledMoney.api.domain.conta.validacao.validacaoDelete;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ContaRepository;

@Service
public class ValidaUsuarioDeletandoConta implements ContaValidacaoDelete{

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void validar(Usuario usuario, Long id) {
        var conta = contaRepository.getReferenceById(id);
        if (usuario.getId() != conta.getUsuario().getId()) {
            throw new ValidacaoException("Usuário não pode deletar a conta de outro usuário");
            
        }
    }
    


}
