package ControlledMoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ControlledMoney.api.domain.parcela.Parcela;

public interface ParcelaRepository extends JpaRepository<Parcela, Long> {


    @Modifying
    @Query("DELETE FROM Parcela p WHERE p.conta.id = :id")
    void deletarParcelaIdConta(Long id);

    

    
}
