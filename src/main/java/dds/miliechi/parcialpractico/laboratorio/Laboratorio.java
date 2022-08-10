package dds.miliechi.parcialpractico.laboratorio;

import dds.miliechi.parcialpractico.base.BaseEntity;
import dds.miliechi.parcialpractico.medicamento.Medicamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Laboratorio")
@Table(name = "laboratorios")
@Getter
@Setter
@NoArgsConstructor
public class Laboratorio extends BaseEntity {

    private String nombre;

    @OneToMany(mappedBy = "laboratorio", cascade = {CascadeType.ALL})
    private List<Medicamento> medicamentos = new ArrayList<>();

    public void addMedicamento(Medicamento medicamento) {
        medicamentos.add(medicamento);
        medicamento.setLaboratorio(this);
    }
}
