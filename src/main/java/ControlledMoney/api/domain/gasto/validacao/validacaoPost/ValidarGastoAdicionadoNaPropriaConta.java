package ControlledMoney.api.domain.gasto.validacao.validacaoPost;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ContaRepository;

@Service
public class ValidarGastoAdicionadoNaPropriaConta implements GastoValidadorPostUsuario {

    @Autowired
    private ContaRepository contaRepository;

    @Override
    public void validar(Usuario usuario, GastoInputDTO dados) {
        var conta = contaRepository.getReferenceById(dados.idConta());
        if (conta.getUsuario().getId() != usuario.getId()) {
            throw new ValidacaoException("Conta não pertence ao Usuário");
        }
        
    }
    
}
