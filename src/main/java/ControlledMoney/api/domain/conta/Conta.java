package ControlledMoney.api.domain.conta;

import java.util.List;

import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.lucro.Lucro;
import ControlledMoney.api.domain.parcela.Parcela;
import ControlledMoney.api.domain.usuario.Usuario;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@EqualsAndHashCode(of = "id")
public class Conta {
    
    public Conta(ContaInputDTO dados, Usuario usuario) {
        this.saldo = dados.saldo();
        this.saldoPrevisto = 0d;
        this.usuario = usuario;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    private Double saldo;

    private Double saldoPrevisto;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lucro> lucros;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Gasto> gastos;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Parcela> parcelas;

    private Double totalLucro;

    private Double totalGasto;

    private Double previstoLucro;

    private Double previstoGasto;

    public void depositar(Double valor){
        this.saldo += valor;
    }

    public void sacar(Double valor){
        this.saldo -= valor;
    }

    public void definirSaldoPrevisto(Double gastoPrevisto, Double lucroPrevisto) {
        this.saldoPrevisto = this.saldo + (lucroPrevisto - this.totalLucro) - (gastoPrevisto - this.totalGasto);
    }
    
    

}
