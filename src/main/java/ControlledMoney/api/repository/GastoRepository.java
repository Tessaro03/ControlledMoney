package ControlledMoney.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import ControlledMoney.api.domain.gasto.Gasto;

public interface GastoRepository  extends JpaRepository<Gasto, Long>{

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND g.pago = true")
    Long gastosPagoIdConta(Long idConta);

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND g.pago = true AND MONTH(g.data) = :mes AND YEAR(g.data) = :ano")
    Long gastoPagoIdContaPorMesEAno(Long idConta, int mes, int ano);
    
    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta" )
    Long gastoPrevistoIdConta(Long idConta);

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND MONTH(g.data) = :mes AND YEAR(g.data) = :ano" )
    Long gastoPrevistoIdContaPorMesEAno(Long idConta, int mes, int ano);

    
}
    

