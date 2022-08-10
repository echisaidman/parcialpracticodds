package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.base.BaseEntity;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
/* Esta clase se persistiria como JSON en una BD NoSQL, por su estructura anidada de Comentario padre
 * y Comentarios hijos, y que hay algunos campos (como el Titulo) que van a ser generalmente NULL
 */
public class Comentario extends BaseEntity {

    private UUID idUsuario;
    private UUID idMedicamento;
    private String titulo;
    private String descripcion;
    private LocalDateTime fechaPublicacion;
    private List<Comentario> respuestas = new ArrayList<>();
    private UUID idComentarioPadre;

    @Override
    public String toString() {
        return "Comentario{" +
                "id=" + id +
                ", idUsuario=" + idUsuario +
                ", idMedicamento=" + idMedicamento +
                ", titulo='" + (titulo != null ? titulo : "") + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fechaPublicacion=" + fechaPublicacion +
                ", idComentarioPadre=" + (idComentarioPadre != null ? idComentarioPadre : "") +
                '}';
    }

    private Comentario(UUID idUsuario, UUID idMedicamento, String titulo, String descripcion, LocalDateTime fechaPublicacion, UUID idComentarioPadre) {
        this.idUsuario = idUsuario;
        this.idMedicamento = idMedicamento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.idComentarioPadre = idComentarioPadre;
    }

    public void addRespuesta(Comentario respuesta) {
        respuestas.add(respuesta);
        respuesta.setIdComentarioPadre(id);
    }

    @Accessors(fluent = true, chain = true)
    @Setter
    public static class Builder {
        private UUID idUsuario = null;
        private UUID idMedicamento = null;
        private String titulo = null;
        private String descripcion = null;
        private LocalDateTime fechaPublicacion = LocalDateTime.now();
        private UUID idComentarioPadre = null;

        public Comentario build() {
            validar();
            return new Comentario(idUsuario, idMedicamento, titulo, descripcion, fechaPublicacion, idComentarioPadre);
        }

        private void validar() {
            // Si el comentario es una respuesta, no puede tener Titulo (solo tienen titulos los
            // comentarios "padres")
            if (idComentarioPadre != null && titulo != null)
                throw new IllegalStateException("Una respuesta a un comentario no puede tener titulo");
        }
    }

}
