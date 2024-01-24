package ControlledMoney.api.domain.conta;

import java.util.List;

import ControlledMoney.api.domain.conta.dtos.ContaInputDTO;
import ControlledMoney.api.domain.gasto.Gasto;
import ControlledMoney.api.domain.lucro.Lucro;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import lombok.val;

@Table(name = "contas")
@Entity(name = "Conta")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@EqualsAndHashCode(of = "id")
public class Conta {
    
    public Conta(ContaInputDTO dados) {
        this.saldo = dados.saldo();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double saldo;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Lucro> lucros;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Gasto> gastos;

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
    
    

}
