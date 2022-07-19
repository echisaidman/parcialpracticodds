package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class PublicarComentarioRequest {
    private UUID idMedicamento;
    private String titulo;
    private String descripcion;
    private UUID idComentarioPadre;
}
