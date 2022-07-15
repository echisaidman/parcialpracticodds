package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class AppUser extends BaseEntity {

    private String username;
    private String password;
    private Double altura; // En centimetros
    private Double peso; // En KGs

}
