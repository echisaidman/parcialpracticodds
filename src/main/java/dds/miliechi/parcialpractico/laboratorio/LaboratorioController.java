package dds.miliechi.parcialpractico.laboratorio;

import dds.miliechi.parcialpractico.security.IsAdmin;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/laboratorios")
public class LaboratorioController {

    private final LaboratorioService laboratorioService;

    public LaboratorioController(LaboratorioService laboratorioService) {
        this.laboratorioService = laboratorioService;
    }

    @PostMapping
    @IsAdmin
    public ResponseEntity<LaboratorioDto> save(@RequestBody LaboratorioDto laboratorioDto) {
        LaboratorioDto savedLaboratorioDto = laboratorioService.save(laboratorioDto);
        return ResponseEntity.ok(savedLaboratorioDto);
    }

    @GetMapping
    public ResponseEntity<List<LaboratorioDto>> list() {
        List<LaboratorioDto> laboratoriosDtos = laboratorioService.list();
        return ResponseEntity.ok(laboratoriosDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LaboratorioDto> getById(@PathVariable("id") long id) {
        return ResponseEntity.ok(laboratorioService.findById(id));
    }

}
