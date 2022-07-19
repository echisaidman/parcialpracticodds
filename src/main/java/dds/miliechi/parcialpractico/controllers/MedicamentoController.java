package dds.miliechi.parcialpractico.controllers;

import dds.miliechi.parcialpractico.dtos.MedicamentoDto;
import dds.miliechi.parcialpractico.dtos.PublicarComentarioRequest;
import dds.miliechi.parcialpractico.entities.AppUser;
import dds.miliechi.parcialpractico.security.IsAdmin;
import dds.miliechi.parcialpractico.services.LaboratorioService;
import dds.miliechi.parcialpractico.services.MedicamentoService;
import dds.miliechi.parcialpractico.services.UserService;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/medicamentos")
public class MedicamentoController {

    private final MedicamentoService medicamentoService;
    private final LaboratorioService laboratorioService;
    private final UserService userService;

    public MedicamentoController(MedicamentoService medicamentoService, LaboratorioService laboratorioService, UserService userService) {
        this.medicamentoService = medicamentoService;
        this.laboratorioService = laboratorioService;
        this.userService = userService;
    }

    @PostMapping
    @IsAdmin
    public ResponseEntity<MedicamentoDto> save(@RequestBody MedicamentoDto medicamentoDto) {
        MedicamentoDto savedMedicamentoDto = laboratorioService.addNewMedicamento(medicamentoDto);
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

    @PostMapping("/publicarComentario")
    public void publicarComentario(@RequestBody PublicarComentarioRequest request, Principal currentUserUsername) {
        AppUser currentUser = userService.findByUsername(currentUserUsername.getName()).get();
        medicamentoService.publicarComentario(request, currentUser.getId());
    }

}
