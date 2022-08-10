package dds.miliechi.parcialpractico.laboratorio;

import dds.miliechi.parcialpractico.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class LaboratorioRepository implements BaseRepository<Laboratorio> {

    private final EntityManager entityManager;

    public LaboratorioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Laboratorio> list() {
        String sql = "select l from Laboratorio l";
        return entityManager.createQuery(sql, Laboratorio.class)
                .getResultList();
    }

    @Override
    public Laboratorio findById(long id) {
        String sql = "select l from Laboratorio l where l.id = :id";
        return entityManager.createQuery(sql, Laboratorio.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Laboratorio laboratorio) {
        entityManager.persist(laboratorio);
    }

    @Override
    public Laboratorio merge(Laboratorio laboratorio) {
        return entityManager.merge(laboratorio);
    }

}
