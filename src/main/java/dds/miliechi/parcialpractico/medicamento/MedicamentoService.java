package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.dtos.PublicarComentarioRequest;
import dds.miliechi.parcialpractico.security.AppUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class MedicamentoService {

    private final MedicamentoRepository medicamentoRepository;
    private final ComentarioRepository comentarioRepository;

    public MedicamentoService(MedicamentoRepository medicamentoRepository, ComentarioRepository comentarioRepository) {
        this.medicamentoRepository = medicamentoRepository;
        this.comentarioRepository = comentarioRepository;
    }

    @Transactional
    public MedicamentoDto findById(long id) {
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
    public void publicarComentario(PublicarComentarioRequest request, AppUser usuario) {
        Medicamento medicamento = medicamentoRepository.findById(request.getIdMedicamento());

        Comentario.Builder comentarioBuilder = new Comentario.Builder()
                .setUsuario(usuario)
                .setMedicamento(medicamento)
                .setTitulo(request.getTitulo())
                .setDescripcion(request.getDescripcion())
                .setFechaPublicacion(LocalDateTime.now());

        if (request.getIdComentarioPadre() != null) {
            comentarioBuilder.setComentarioPadre(comentarioRepository.findById(request.getIdComentarioPadre()));
        }

        Comentario comentario = comentarioBuilder.build();
        medicamento.addComentario(comentario);
        medicamentoRepository.merge(medicamento);
    }

}
