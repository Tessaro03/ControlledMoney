package ControlledMoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ControlledMoney.api.domain.gasto.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {

    

    
}
