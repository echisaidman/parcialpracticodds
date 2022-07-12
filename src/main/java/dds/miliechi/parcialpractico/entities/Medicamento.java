package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Medicamento extends BaseEntity {

    private String nombre;

    @ManyToOne
    @JoinColumn(name = "empresa", nullable = false)
    private Empresa empresa;

}
