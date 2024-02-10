package ControlledMoney.api.domain.lucro.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ContaRepository;

@Service
public class ValidarLucroAdicionadoNaPropriaConta implements LucroValidadorPost{

    @Autowired
    private ContaRepository contaRepository;



    @Override
    public void validar(Usuario usuario, LucroInputDTO dados) {
        var conta = contaRepository.contaPorIdUsuario(usuario.getId());
        if (conta.getId() != dados.idConta()) {
            throw new ValidacaoException("Usuário deve criar lucro somente para sua conta");
        }

    }
    
}
