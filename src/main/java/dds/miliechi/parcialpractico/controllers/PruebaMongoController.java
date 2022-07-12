package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.entities.PruebaMongoEntity;
import dds.miliechi.parcialpractico.services.PruebaMongoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebamongo")
public class PruebaMongoController {

    private final PruebaMongoService service;

    public PruebaMongoController(PruebaMongoService service) {
        this.service = service;
    }

    @GetMapping
    public List<PruebaMongoEntity> list() {
        return service.list();
    }

    @PostMapping
    public void save(@RequestBody PruebaMongoEntity entity) {
        service.save(entity);
    }

}
