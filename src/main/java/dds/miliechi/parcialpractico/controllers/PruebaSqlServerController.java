package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.entities.PruebaSqlServerEntity;
import dds.miliechi.parcialpractico.repositories.PruebaSqlServerRepository;
import dds.miliechi.parcialpractico.services.PruebaSqlServerService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pruebasqlserver")
public class PruebaSqlServerController {

    private final PruebaSqlServerService service;

    public PruebaSqlServerController(PruebaSqlServerService service) {
        this.service = service;
    }

    @GetMapping
    public List<PruebaSqlServerEntity> list() {
        return service.list();
    }

    @PostMapping
    public void save(@RequestBody PruebaSqlServerEntity entity) {
        service.save(entity);
    }

}
