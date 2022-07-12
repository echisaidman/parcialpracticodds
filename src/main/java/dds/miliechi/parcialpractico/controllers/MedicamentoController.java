package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.dtos.MedicamentoDto;
import dds.miliechi.parcialpractico.services.EmpresaService;
import dds.miliechi.parcialpractico.services.MedicamentoService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;
    private final EmpresaService empresaService;

    public MedicamentoController(MedicamentoService medicamentoService, EmpresaService empresaService) {
        this.medicamentoService = medicamentoService;
        this.empresaService = empresaService;
    }

    @PostMapping
    public ResponseEntity<MedicamentoDto> save(@RequestBody MedicamentoDto medicamentoDto) {
        MedicamentoDto savedMedicamentoDto = empresaService.addNewMedicamento(medicamentoDto);
        return ResponseEntity.ok(savedMedicamentoDto);
    }

    @GetMapping
    public ResponseEntity<List<MedicamentoDto>> list() {
        List<MedicamentoDto> medicamentosDtos = medicamentoService.list();
        return ResponseEntity.ok(medicamentosDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicamentoDto> getById(@Param("id") UUID id) {
        return ResponseEntity.ok(medicamentoService.findById(id));
    }

}
