package ControlledMoney.api.domain.conta.service;

import java.time.LocalDate;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ControlledMoney.api.domain.conta.Conta;
import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.conta.dtos.ContaOutputDTO;
import ControlledMoney.api.domain.conta.validacao.ContaValidacao;
import ControlledMoney.api.infra.token.TokenUsuario;
import ControlledMoney.api.repository.ContaRepository;
import ControlledMoney.api.repository.GastoRepository;
import ControlledMoney.api.repository.LucroRepository;
import ControlledMoney.api.repository.ParcelaRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;

@Service
public class ContaService {
    
    @Autowired 
    private LucroRepository lucroRepository;

    @Autowired
    private ContaRepository contaRepository;

    @Autowired
    private GastoRepository gastoRepository;

    @Autowired
    private ParcelaRepository parcelaRepository;

    @Autowired
    private ContaValidacao contaValidacao;

    @Autowired
    private TokenUsuario tokenUsuario;

    public ContaOutputDTO dadosConta(Long id, int mes, int ano) {
        if (mes == 0 || ano == 0) {
            LocalDate dataAtual = LocalDate.now();
            mes = dataAtual.getMonthValue();
            ano = dataAtual.getYear();
        }

        Conta conta = contaRepository.getReferenceById(id);
        Double lucroTotal = lucroRepository.lucrosPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double lucroPrevisto = lucroRepository.lucrosPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        Double gastoTotal = gastoRepository.gastoPagoIdContaPorMesEAno(conta.getId(), mes, ano);
        Double gastoPrevisto = gastoRepository.gastoPrevistoIdContaPorMesEAno(conta.getId(), mes, ano);

        if (lucroTotal == null) { lucroTotal = 0d;}
        if (lucroPrevisto == null) { lucroPrevisto = 0d;}
        if (gastoPrevisto == null) { gastoPrevisto = 0d;}
        if (gastoTotal == null) { gastoTotal = 0d;}

        conta.setTotalGasto(gastoTotal);
        conta.setPrevistoGasto(gastoPrevisto);
        conta.setTotalLucro(lucroTotal);
        conta.setPrevistoLucro(lucroPrevisto);
        conta.definirSaldoPrevisto(gastoPrevisto, lucroPrevisto);
        return new ContaOutputDTO(conta);
    }

    @Transactional
    public void deletarConta(HttpServletRequest request,Long id) {
        var usuario = tokenUsuario.usuarioToken(request);

        contaValidacao.validarDelete(usuario, id);
        lucroRepository.deletarLucroIdConta(id);
        parcelaRepository.deletarParcelaIdConta(id);
        gastoRepository.deletarGastoIdConta(id);
        contaRepository.deleteById(id);
    }

    @Transactional
    public void criarConta(HttpServletRequest request, ContaInputDTO dados){
        var usuario = tokenUsuario.usuarioToken(request);
        var conta = new Conta(dados, usuario);
        contaRepository.save(conta);
    }

 
}                                                                              
