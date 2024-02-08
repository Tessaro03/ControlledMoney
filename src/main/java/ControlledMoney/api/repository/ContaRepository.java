package ControlledMoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ControlledMoney.api.domain.conta.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long> {

    @Query("SELECT c FROM Conta c WHERE c.usuario.id = :idUsuario")
    Conta contaPorIdUsuario(Long idUsuario);
    
}
