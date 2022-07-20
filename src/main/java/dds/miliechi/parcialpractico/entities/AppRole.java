package dds.miliechi.parcialpractico.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity(name = "AppRole")
@Table(name = "app_roles")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppRole extends BaseEntity {

    private String nombre;

}
