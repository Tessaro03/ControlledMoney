package ControlledMoney.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import ControlledMoney.api.domain.gasto.Gasto;

public interface GastoRepository  extends JpaRepository<Gasto, Long>{

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND g.pago = true")
    Double gastosPagoIdConta(Long idConta);

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND g.pago = true AND MONTH(g.data) = :mes AND YEAR(g.data) = :ano")
    Double gastoPagoIdContaPorMesEAno(Long idConta, int mes, int ano);
    
    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta" )
    Double gastoPrevistoIdConta(Long idConta);

    @Query("SELECT SUM(g.valor) FROM Gasto g WHERE g.conta.id = :idConta AND MONTH(g.data) = :mes AND YEAR(g.data) = :ano" )
    Double gastoPrevistoIdContaPorMesEAno(Long idConta, int mes, int ano);

    @Modifying
    @Query("DELETE FROM Gasto g WHERE g.parcela.id = :id")
    void deletarGastoIdParcela(Long id);

    @Modifying
    @Query("DELETE FROM Gasto g WHERE g.conta.id = :id")
    void deletarGastoIdConta(Long id);

    @Query("SELECT g FROM Gasto g WHERE g.conta.usuario.id = :idUsuario")
    List<Gasto> gastoPorIdUsuario(Long idUsuario);

    
}
    

