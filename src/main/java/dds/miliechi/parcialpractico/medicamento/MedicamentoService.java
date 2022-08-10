package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.dtos.PublicarComentarioRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository) {
        this.medicamentoRepository = medicamentoRepository;
    }

    @Transactional
    public MedicamentoDto findById(UUID id) {
        return MedicamentoDto.from(medicamentoRepository.findById(id));
    }

    @Transactional
    public List<MedicamentoDto> list() {
        List<Medicamento> medicamentos = medicamentoRepository.list();
        return medicamentos.stream().map(MedicamentoDto::from).collect(Collectors.toList());
    }

    @Transactional
    public MedicamentoDto save(Medicamento medicamento) {
        medicamentoRepository.save(medicamento);
        return MedicamentoDto.from(medicamento);
    }

    @Transactional
    public void publicarComentario(PublicarComentarioRequest request, UUID idUsuario) {
        Comentario comentario = new Comentario.Builder()
                .idUsuario(idUsuario)
                .idMedicamento(request.getIdMedicamento())
                .titulo(request.getTitulo())
                .descripcion(request.getDescripcion())
                .fechaPublicacion(LocalDateTime.now())
                .idComentarioPadre(request.getIdComentarioPadre())
                .build();
        // Aca iria la parte de persistencia con la BD NoSQL, que no la implementamos
        log.debug("Comentario publicado: " + comentario.toString());
    }

}
