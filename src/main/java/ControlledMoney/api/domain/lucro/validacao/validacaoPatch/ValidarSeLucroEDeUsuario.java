package ControlledMoney.api.domain.lucro.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.domain.usuario.Usuario;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.LucroRepository;

@Service
public class ValidarSeLucroEDeUsuario implements LucroValidadorPatchUsuario {

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private LucroRepository lucroRepository;

    @Override
    public void validar(Usuario usuario, LucroAlterarDTO dados) {
        var conta = contaRepository.contaPorIdUsuario(usuario.getId());
        var lucro =  lucroRepository.getReferenceById(dados.idLucro());
        if (conta.getId() != lucro.getConta().getId()) {
            throw new ValidacaoException("Lucro não pertence ao Usuário");
        }


    }
    
}
