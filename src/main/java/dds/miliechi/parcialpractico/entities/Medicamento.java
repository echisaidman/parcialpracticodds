package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "MED_TYPE")
@Getter
@Setter
@NoArgsConstructor
public abstract class Medicamento extends BaseEntity {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "empresa", nullable = false)
    private Empresa empresa;

    public abstract double getPrecio();

}
