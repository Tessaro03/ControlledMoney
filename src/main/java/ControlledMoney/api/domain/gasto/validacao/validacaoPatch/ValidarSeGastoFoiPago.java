package ControlledMoney.api.domain.gasto.validacao.validacaoPatch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.gasto.dtos.GastoAlterarDTO;
import ControlledMoney.api.infra.Exceptions.ValidacaoException;
import ControlledMoney.api.repository.GastoRepository;

@Service
public class ValidarSeGastoFoiPago implements GastoValidadorPatch{

    @Autowired
    private GastoRepository gastoRepository;

    @Override
    public void validar(GastoAlterarDTO dados) {
        var gasto = gastoRepository.getReferenceById(dados.idGasto());
        if (gasto.getPago().equals(dados.pago())) {
            throw new ValidacaoException("Verificar status do gasto");
        }
    }
    
}
