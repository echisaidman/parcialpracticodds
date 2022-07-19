package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.dtos.EmpresaDto;
import dds.miliechi.parcialpractico.security.IsAdmin;
import dds.miliechi.parcialpractico.services.EmpresaService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/empresas")
public class EmpresaController {

    private final EmpresaService empresaService;

    public EmpresaController(EmpresaService empresaService) {
        this.empresaService = empresaService;
    }

    @PostMapping
    @IsAdmin
    public ResponseEntity<EmpresaDto> save(@RequestBody EmpresaDto empresaDto) {
        EmpresaDto savedEmpresaDto = empresaService.save(empresaDto);
        return ResponseEntity.ok(savedEmpresaDto);
    }

    @GetMapping
    public ResponseEntity<List<EmpresaDto>> list() {
        List<EmpresaDto> empresasDtos = empresaService.list();
        return ResponseEntity.ok(empresasDtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmpresaDto> getById(@Param("id") UUID id) {
        return ResponseEntity.ok(empresaService.findById(id));
    }

}
