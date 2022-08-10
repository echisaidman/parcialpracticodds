package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.base.BaseEntity;
import dds.miliechi.parcialpractico.laboratorio.Laboratorio;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Medicamento")
@Table(name = "medicamentos")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MED_TYPE")
@Getter
@Setter
@NoArgsConstructor
public abstract class Medicamento extends BaseEntity {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "laboratorio", nullable = false, foreignKey = @ForeignKey(name = "FK_Medicamento_Id_Laboratorio_Id"))
    private Laboratorio laboratorio;

    @Transient
    private List<Comentario> comentarios = new ArrayList<>();

    public abstract double getPrecio();

}
