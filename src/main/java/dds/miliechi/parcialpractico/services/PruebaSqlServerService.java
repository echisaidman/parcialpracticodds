package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.entities.PruebaSqlServerEntity;
import dds.miliechi.parcialpractico.repositories.PruebaSqlServerRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class PruebaSqlServerService {

    private final PruebaSqlServerRepository repository;

    public PruebaSqlServerService(PruebaSqlServerRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public List<PruebaSqlServerEntity> list() {
        return repository.list();
    }

    @Transactional
    public void save(PruebaSqlServerEntity entity) {
        repository.save(entity);
    }

}
