package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.base.BaseEntity;
import dds.miliechi.parcialpractico.security.AppUser;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Comentario")
@Table(name = "comentarios")
@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comentario extends BaseEntity {

    @ManyToOne
    @JoinColumn(name = "usuario", nullable = false, foreignKey = @ForeignKey(name = "FK_Comentarios_Usuario"))
    private AppUser usuario;

    @ManyToOne
    @JoinColumn(name = "medicamento", nullable = false, foreignKey = @ForeignKey(name = "FK_Comentarios_Medicamento"))
    private Medicamento medicamento;

    private String titulo;
    private String descripcion;
    private LocalDateTime fechaPublicacion;

    @ManyToOne
    @JoinColumn(name = "comentario_padre", foreignKey = @ForeignKey(name = "FK_Comentarios_ComentarioPadre"))
    private Comentario comentarioPadre;

    @OneToMany(mappedBy = "comentarioPadre", cascade = {CascadeType.ALL})
    private List<Comentario> respuestas = new ArrayList<>();

    private Comentario(AppUser usuario, Medicamento medicamento, String titulo, String descripcion, LocalDateTime fechaPublicacion, Comentario comentarioPadre) {
        this.usuario = usuario;
        this.medicamento = medicamento;
        this.titulo = titulo;
        this.descripcion = descripcion;
        this.fechaPublicacion = fechaPublicacion;
        this.comentarioPadre = comentarioPadre;
    }

    public void addRespuesta(Comentario respuesta) {
        respuestas.add(respuesta);
        respuesta.setComentarioPadre(this);
    }

    public static class Builder {
        private AppUser usuario = null;
        private Medicamento medicamento = null;
        private String titulo = null;
        private String descripcion = null;
        private LocalDateTime fechaPublicacion = LocalDateTime.now();
        private Comentario comentarioPadre = null;

        public Comentario build() {
            validar();
            return new Comentario(usuario, medicamento, titulo, descripcion, fechaPublicacion, comentarioPadre);
        }

        public Builder setUsuario(AppUser usuario) {
            this.usuario = usuario;
            return this;
        }

        public Builder setMedicamento(Medicamento medicamento) {
            this.medicamento = medicamento;
            return this;
        }

        public Builder setTitulo(String titulo) {
            this.titulo = titulo;
            return this;
        }

        public Builder setDescripcion(String descripcion) {
            this.descripcion = descripcion;
            return this;
        }

        public Builder setFechaPublicacion(LocalDateTime fechaPublicacion) {
            this.fechaPublicacion = fechaPublicacion;
            return this;
        }

        public Builder setComentarioPadre(Comentario comentarioPadre) {
            this.comentarioPadre = comentarioPadre;
            return this;
        }

        private void validar() {
            // Si el comentario es una respuesta, no puede tener Titulo (solo tienen titulos los
            // comentarios "raices", que son los que no tienen padre)
            if (comentarioPadre != null && titulo != null)
                throw new IllegalStateException("Una respuesta a un comentario no puede tener titulo");
        }
    }

}
