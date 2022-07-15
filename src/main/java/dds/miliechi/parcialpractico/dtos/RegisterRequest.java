package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterRequest {
    private String username;
    private String password;
    private Double altura;
    private Double peso;
}
