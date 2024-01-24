package ControlledMoney.api.domain.gasto;

import java.util.List;

import ControlledMoney.api.domain.gasto.dtos.GastoInputDTO;
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

@Table(name = "parcelas")
@Entity(name = "Parcela")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@EqualsAndHashCode(of = "id")
@Setter
public class Parcela {

    public Parcela(GastoInputDTO dados ) {
        this.parcelas = dados.parcelas();
        this.pago = dados.pago();
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double valor;
    private Long parcelas;
    private Boolean pago;


}
