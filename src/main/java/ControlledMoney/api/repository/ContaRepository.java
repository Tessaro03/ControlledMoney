package ControlledMoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ControlledMoney.api.domain.conta.Conta;


public interface ContaRepository extends JpaRepository<Conta, Long> {

    
}
