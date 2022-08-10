package dds.miliechi.parcialpractico.laboratorio;

import dds.miliechi.parcialpractico.security.IsAdmin;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

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
    public ResponseEntity<LaboratorioDto> getById(@Param("id") UUID id) {
        return ResponseEntity.ok(laboratorioService.findById(id));
    }

}
