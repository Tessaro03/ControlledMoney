package ControlledMoney.api.domain.parcela.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ContaRepository;

/**
 * ValidarSeParcelaENaContaDoUsuario
 */
@Service
public class ValidarSeParcelaENaContaDoUsuario implements ParcelaValidarPost {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void validar(ParcelaInputDTO dados, Usuario usuario) {
        var conta = contaRepository.getReferenceById(dados.idConta());
        if (!conta.getUsuario().equals(usuario)) {
            throw new ValidacaoException("Conta não pertence ao Usuario");
        }

    }

    
}