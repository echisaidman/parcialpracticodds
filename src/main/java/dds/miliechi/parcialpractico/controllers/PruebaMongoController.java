package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.entities.PruebaMongoEntity;
import dds.miliechi.parcialpractico.repositories.PruebaMongoRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebamongo")
public class PruebaMongoController {

    private final PruebaMongoRepository repository;

    public PruebaMongoController(PruebaMongoRepository repository) {
        this.repository = repository;

    }

    @GetMapping
    public List<PruebaMongoEntity> list() {
        return repository.list();
    }

    @PostMapping
    public void save(@RequestBody PruebaMongoEntity entity) {
        repository.save(entity);
    }

}
