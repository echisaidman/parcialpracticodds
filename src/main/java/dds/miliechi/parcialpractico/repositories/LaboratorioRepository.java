package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.Laboratorio;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class LaboratorioRepository implements dds.miliechi.parcialpractico.repositories.Repository<Laboratorio> {

    private final EntityManager entityManager;

    public LaboratorioRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Laboratorio> list() {
        String sql = "from Laboratorio l";
        return entityManager.createQuery(sql, Laboratorio.class)
                .getResultList();
    }

    public Optional<Laboratorio> findByName(String name) {
        String sql = "from Laboratorio l where l.nombre = :nombre";
        return entityManager.createQuery(sql, Laboratorio.class)
                .setParameter("nombre", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Laboratorio findById(UUID id) {
        String sql = "from Laboratorio l where l.id = :id";
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
