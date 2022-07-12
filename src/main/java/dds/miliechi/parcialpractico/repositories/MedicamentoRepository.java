package dds.miliechi.parcialpractico.repositories;

import dds.miliechi.parcialpractico.entities.Medicamento;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class MedicamentoRepository implements dds.miliechi.parcialpractico.repositories.Repository<Medicamento> {

    private final EntityManager entityManager;

    public MedicamentoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Medicamento> list() {
        String sql = "from Medicamento m";
        return entityManager.createQuery(sql, Medicamento.class)
                .getResultList();
    }

    public Optional<Medicamento> findByName(String name) {
        String sql = "from Medicamento m where m.nombre = :nombre";
        return entityManager.createQuery(sql, Medicamento.class)
                .setParameter("nombre", name)
                .getResultStream()
                .findFirst();
    }

    @Override
    public Medicamento findById(UUID id) {
        String sql = "from Medicamento m where m.id = :id";
        return entityManager.createQuery(sql, Medicamento.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public void save(Medicamento medicamento) {
        entityManager.persist(medicamento);
    }

    @Override
    public Medicamento merge(Medicamento entity) {
        return entityManager.merge(entity);
    }

}
