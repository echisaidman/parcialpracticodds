package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.base.BaseRepository;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.UUID;

@Repository
public class MedicamentoRepository implements BaseRepository<Medicamento> {

    private final EntityManager entityManager;

    public MedicamentoRepository(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    public List<Medicamento> list() {
        String sql = "select m from Medicamento m";
        return entityManager.createQuery(sql, Medicamento.class)
                .getResultList();
    }

    @Override
    public Medicamento findById(UUID id) {
        String sql = "select m from Medicamento m where m.id = :id";
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
