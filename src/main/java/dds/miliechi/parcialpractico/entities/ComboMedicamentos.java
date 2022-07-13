package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue(value = "COMBO")
@Getter
@Setter
@NoArgsConstructor
public class ComboMedicamentos extends Medicamento {

    // El precio de un Combo tiene un 10% de descuento respecto al precio de sus Componentes
    private static final double PORCENTAJE_DESCUENTO_COMBO = 10;

    @OneToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "medicamentos_por_combo",
            joinColumns = @JoinColumn(name = "combo_medicamento"),
            inverseJoinColumns = @JoinColumn(name = "medicamento")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    @Override
    public double getPrecio() {
        double precioTotalComponentes = medicamentos.stream().mapToDouble(Medicamento::getPrecio).sum();
        return precioTotalComponentes * (100 - PORCENTAJE_DESCUENTO_COMBO) / 100;
    }
}
