package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.entities.PruebaMongoEntity;
import dds.miliechi.parcialpractico.repositories.PruebaMongoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PruebaMongoService {

    private final PruebaMongoRepository repository;

    public PruebaMongoService(PruebaMongoRepository repository) {
        this.repository = repository;
    }

    public List<PruebaMongoEntity> list() {
        return repository.list();
    }

    public void save(PruebaMongoEntity entity) {
        repository.save(entity);
    }

}
