package dds.miliechi.parcialpractico.entities;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "roles_por_usuario",
            joinColumns = @JoinColumn(name = "usuario"),
            inverseJoinColumns = @JoinColumn(name = "rol")
    )
    private List<AppRole> roles = new ArrayList<>();

    public void addRole(AppRole role) {
        roles.add(role);
    }

}
