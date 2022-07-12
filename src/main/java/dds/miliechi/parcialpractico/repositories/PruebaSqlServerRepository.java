package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.PruebaSqlServerEntity;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class PruebaSqlServerRepository {

    private final EntityManager entityManager;

    public PruebaSqlServerRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<PruebaSqlServerEntity> list() {
        String query = "from PruebaSqlServerEntity e";
        return entityManager
                .createQuery(query, PruebaSqlServerEntity.class)
                .getResultList();
    }

    public void save(PruebaSqlServerEntity entity) {
        entityManager.persist(entity);
    }

}
