package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PublicarComentarioRequest {
    private long idMedicamento;
    private String titulo;
    private String descripcion;
    private Long idComentarioPadre;
}
