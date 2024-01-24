package ControlledMoney.api.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ControlledMoney.api.domain.lucro.Lucro;

public interface LucroRepository extends JpaRepository<Lucro, Long>{
    
    @Query("SELECT SUM(l.valor) FROM Lucro l WHERE l.conta.id = :idConta AND l.recebido = true")
    Long lucrosPagoIdConta(Long idConta);

    @Query("SELECT SUM(l.valor) FROM Lucro l WHERE l.conta.id = :idConta AND l.recebido = true AND MONTH(l.data) = :mes AND YEAR(l.data) = :ano")
    Long lucrosPagoIdContaPorMesEAno(Long idConta, int mes, int ano);
    
    @Query("SELECT SUM(l.valor) FROM Lucro l WHERE l.conta.id = :idConta" )
    Long lucrosPrevistoIdConta(Long idConta);

    @Query("SELECT SUM(l.valor) FROM Lucro l WHERE l.conta.id = :idConta AND MONTH(l.data) = :mes AND YEAR(l.data) = :ano" )
    Long lucrosPrevistoIdContaPorMesEAno(Long idConta, int mes, int ano);

}