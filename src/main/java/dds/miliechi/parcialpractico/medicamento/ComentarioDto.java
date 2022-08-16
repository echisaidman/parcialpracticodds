package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.dtos.IdTextPair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ComentarioDto {

    private long id;
    private IdTextPair usuario;
    // private IdTextPair medicamento;
    private String titulo;
    private String mensaje;
    private LocalDateTime fechaPublicacion;
    private Long idComentarioPadre;
    private List<ComentarioDto> respuestas = new ArrayList<>();

    public static ComentarioDto from(Comentario comentario) {
        ComentarioDto comentarioDto = new ComentarioDto();
        comentarioDto.setId(comentario.getId());
        comentarioDto.setUsuario(
                new IdTextPair(comentario.getUsuario().getId(), comentario.getUsuario().getUsername())
        );
        if (comentario.getTitulo() != null) {
            comentarioDto.setTitulo(comentario.getTitulo());
        }
        comentarioDto.setMensaje(comentario.getMensaje());
        comentarioDto.setFechaPublicacion(comentario.getFechaPublicacion());
        if (comentario.getComentarioPadre() != null) {
            comentarioDto.setIdComentarioPadre(comentario.getComentarioPadre().getId());
        }
        for (Comentario respuesta : comentario.getRespuestas()) {
            ComentarioDto respuestaDto = ComentarioDto.from(respuesta);
            comentarioDto.getRespuestas().add(respuestaDto);
        }
        return comentarioDto;
    }

}
