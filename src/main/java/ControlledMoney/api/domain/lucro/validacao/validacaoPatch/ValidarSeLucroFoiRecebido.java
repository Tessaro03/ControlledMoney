package ControlledMoney.api.domain.lucro.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.lucro.dtos.LucroAlterarDTO;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.LucroRepository;

@Service
public class ValidarSeLucroFoiRecebido implements LucroValidadorPatch{

    @Autowired
    private LucroRepository lucroRepository;

    @Override
    public void validar(LucroAlterarDTO dados) {
        var lucro = lucroRepository.getReferenceById(dados.idLucro());
        if (lucro.getRecebido().equals(dados.recebido())) {
            throw new ValidacaoException("Verificar status do Lucro");
        }


    }
    
}
