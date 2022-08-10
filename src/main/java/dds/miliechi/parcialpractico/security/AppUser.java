package dds.miliechi.parcialpractico.security;

import dds.miliechi.parcialpractico.base.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "AppUser")
@Table(name = "app_users")
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
            inverseJoinColumns = @JoinColumn(name = "rol"),
            foreignKey = @ForeignKey(name = "FK_RolesPorUsuario_Usuario"),
            inverseForeignKey = @ForeignKey(name = "FK_RolesPorUsuario_Rol")
    )
    private List<AppRole> roles = new ArrayList<>();

    public void addRole(AppRole role) {
        roles.add(role);
    }

}
