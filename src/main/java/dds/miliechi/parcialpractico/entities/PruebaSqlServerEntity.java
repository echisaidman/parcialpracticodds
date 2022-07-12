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
public class PruebaSqlServerEntity extends BaseEntity {

    private String username;
    private String password;

    public PruebaSqlServerEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

}
