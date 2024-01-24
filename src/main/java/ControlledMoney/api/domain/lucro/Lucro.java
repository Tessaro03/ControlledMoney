package ControlledMoney.api.domain.lucro;

import java.time.LocalDate;

import ControlledMoney.api.domain.conta.Conta;
import ControlledMoney.api.domain.lucro.dtos.LucroInputDTO;
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

@Table(name = "lucros")
@Entity(name = "Lucro")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
public class Lucro {

    public Lucro(LucroInputDTO dados, Conta conta) {
        this.conta = conta;
        this.motivo = dados.motivo();
        this.valor = dados.valor();
        this.recebido = dados.recebido();
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
    private Double valor;
    private Boolean recebido;

    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "conta_id")
    private Conta conta;
    
}
