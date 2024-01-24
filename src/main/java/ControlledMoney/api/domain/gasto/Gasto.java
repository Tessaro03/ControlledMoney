package ControlledMoney.api.domain.gasto;

import java.time.LocalDate;

import ControlledMoney.api.domain.conta.Conta;
import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "gastos")
@Entity(name = "Gasto")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@EqualsAndHashCode(of = "id")
public class Gasto {

    public Gasto(GastoInputDTO dados, Conta conta) {
        this.conta = conta;
        this.motivo = dados.motivo();
        this.valor = dados.valor();
        this.pago = dados.pago();
        if (dados.data() == null) {
            this.data = LocalDate.now();    
        } else {
            this.data = dados.data();
        }        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String motivo;
    private Long valor;
    private Boolean pago;
    private LocalDate data;
    private Long numeroParcela;

    @ManyToOne
    @JoinColumn(name = "parcela_id")
    private Parcela parcela;


    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;

}
