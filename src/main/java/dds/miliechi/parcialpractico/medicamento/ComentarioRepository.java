package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class ComentarioRepository implements BaseRepository<Comentario> {

    private final EntityManager entityManager;

    public ComentarioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Comentario> list() {
        String sql = "select c from Comentario c";
        return entityManager.createQuery(sql, Comentario.class)
                .getResultList();
    }

    @Override
    public Comentario findById(UUID id) {
        String sql = "select c from Comentario c where c.id = :id";
        return entityManager.createQuery(sql, Comentario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Comentario Comentario) {
        entityManager.persist(Comentario);
    }

    @Override
    public Comentario merge(Comentario entity) {
        return entityManager.merge(entity);
    }

}
