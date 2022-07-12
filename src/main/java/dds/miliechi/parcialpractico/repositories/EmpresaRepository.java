package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.Empresa;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class EmpresaRepository implements dds.miliechi.parcialpractico.repositories.Repository<Empresa> {

    private final EntityManager entityManager;

    public EmpresaRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Empresa> list() {
        String sql = "from Empresa e";
        return entityManager.createQuery(sql, Empresa.class)
                .getResultList();
    }

    public Optional<Empresa> findByName(String name) {
        String sql = "from Empresa e where e.nombre = :nombre";
        return entityManager.createQuery(sql, Empresa.class)
                .setParameter("nombre", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Empresa findById(UUID id) {
        String sql = "from Empresa e where e.id = :id";
        return entityManager.createQuery(sql, Empresa.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Empresa empresa) {
        entityManager.persist(empresa);
    }

    @Override
    public Empresa merge(Empresa empresa) {
        return entityManager.merge(empresa);
    }

}
