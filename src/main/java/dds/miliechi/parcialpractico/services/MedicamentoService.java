package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.dtos.MedicamentoDto;
import dds.miliechi.parcialpractico.entities.Medicamento;
import dds.miliechi.parcialpractico.repositories.MedicamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
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

}
