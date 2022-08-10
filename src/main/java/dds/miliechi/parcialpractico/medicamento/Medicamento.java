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
    @JoinColumn(name = "laboratorio", nullable = false, foreignKey = @ForeignKey(name = "FK_Medicamentos_Laboratorio"))
    private Laboratorio laboratorio;

    @OneToMany(mappedBy = "medicamento", cascade = {CascadeType.ALL})
    private List<Comentario> comentarios = new ArrayList<>();

    public void addComentario(Comentario comentario) {
        comentarios.add(comentario);
        comentario.setMedicamento(this);
    }

    public abstract double getPrecio();

}
