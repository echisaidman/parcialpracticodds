package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "ComboMedicamento")
@DiscriminatorValue(value = "COMBO")
@Getter
@Setter
@NoArgsConstructor
public class ComboMedicamentos extends Medicamento {

    // El precio de un Combo tiene un 10% de descuento respecto al precio de sus Componentes
    private static final double PORCENTAJE_DESCUENTO_COMBO = 10;

    @ManyToMany(cascade = {CascadeType.ALL}, fetch = FetchType.EAGER)
    @JoinTable(
            name = "medicamentos_por_combo",
            joinColumns = @JoinColumn(name = "combo_medicamento"),
            inverseJoinColumns = @JoinColumn(name = "medicamento"),
            foreignKey = @ForeignKey(name = "FK_MedicamentosPorCombo_ComboMedicamento_Medicamentos_Id"),
            inverseForeignKey = @ForeignKey(name = "FK_MedicamentosPorCombo_Medicamento_Medicamentos_Id")
    )
    private List<Medicamento> medicamentos = new ArrayList<>();

    @Override
    public double getPrecio() {
        double precioTotalComponentes = medicamentos.stream().mapToDouble(Medicamento::getPrecio).sum();
        return precioTotalComponentes * (100 - PORCENTAJE_DESCUENTO_COMBO) / 100;
    }

    public void addMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
    }
}
