package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.AppRole;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.Optional;
import java.util.UUID;

@Repository
public class RoleRepository implements dds.miliechi.parcialpractico.repositories.Repository<AppRole> {

    private final EntityManager entityManager;

    public RoleRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public Optional<AppRole> findByRoleName(String roleName) {
        String sql = "select role from AppRole role where role.nombre = :roleName";
        return entityManager.createQuery(sql, AppRole.class)
                .setParameter("roleName", roleName)
                .getResultStream()
                .findFirst();
    }

    @Override
    public AppRole findById(UUID id) {
        String sql = "select role from AppRole role where role.id = :id";
        return entityManager.createQuery(sql, AppRole.class)
                .setParameter("id", id)
                .getResultStream()
                .findFirst()
                .orElse(null);
    }

    @Override
    public void save(AppRole appRole) {
        entityManager.persist(appRole);
    }

    @Override
    public AppRole merge(AppRole entity) {
        return entityManager.merge(entity);
    }
}
