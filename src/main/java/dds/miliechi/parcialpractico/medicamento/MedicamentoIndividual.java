package dds.miliechi.parcialpractico.medicamento;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity(name = "MedicamentoIndividual")
@DiscriminatorValue(value = "INDIV")
@Getter
@Setter
@NoArgsConstructor
public class MedicamentoIndividual extends Medicamento {

    private double precio;

    @Override
    public double getPrecio() {
        return precio;
    }
}
