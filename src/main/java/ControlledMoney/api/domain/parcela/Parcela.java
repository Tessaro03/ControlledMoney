package ControlledMoney.api.domain.parcela;


import java.time.LocalDate;

import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
import ControlledMoney.api.domain.parcela.dtos.ParcelaInputDTO;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Table(name = "parcelas")
@Entity(name = "Parcela")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Setter
public class Parcela {

    public Parcela(ParcelaInputDTO dados ) {
        this.parcelas = dados.parcelas();
        this.pago = dados.pago();
        this.valor = dados.valor();
        
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Long parcelas;
    private Boolean pago;
}
