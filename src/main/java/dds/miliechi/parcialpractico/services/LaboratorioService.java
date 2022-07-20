package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.dtos.LaboratorioDto;
import dds.miliechi.parcialpractico.dtos.IdTextPair;
import dds.miliechi.parcialpractico.dtos.MedicamentoDto;
import dds.miliechi.parcialpractico.entities.ComboMedicamentos;
import dds.miliechi.parcialpractico.entities.Laboratorio;
import dds.miliechi.parcialpractico.entities.Medicamento;
import dds.miliechi.parcialpractico.entities.MedicamentoIndividual;
import dds.miliechi.parcialpractico.repositories.LaboratorioRepository;
import dds.miliechi.parcialpractico.repositories.MedicamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LaboratorioService {

    private final LaboratorioRepository laboratorioRepository;
    private final MedicamentoRepository medicamentoRepository;
    // private final MedicamentoService medicamentoService;

    public LaboratorioService(LaboratorioRepository laboratorioRepository, MedicamentoRepository medicamentoRepository) {
        this.laboratorioRepository = laboratorioRepository;
        // this.medicamentoService = medicamentoService;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Transactional
    public Optional<LaboratorioDto> findByName(String name) {
        Optional<Laboratorio> laboratorioOptional = laboratorioRepository.findByName(name);
        return laboratorioOptional.map(LaboratorioDto::from);
    }

    @Transactional
    public LaboratorioDto findById(UUID id) {
        return LaboratorioDto.from(laboratorioRepository.findById(id));
    }

    @Transactional
    public LaboratorioDto save(LaboratorioDto laboratorioDto) {
        Laboratorio laboratorio = new Laboratorio();
        laboratorio.setNombre(laboratorioDto.getNombre());
        laboratorioRepository.save(laboratorio);
        return LaboratorioDto.from(laboratorio);
    }

    @Transactional
    public List<LaboratorioDto> list() {
        List<Laboratorio> laboratorios = laboratorioRepository.list();
        return laboratorios.stream().map(LaboratorioDto::from).collect(Collectors.toList());
    }

    @Transactional
    public MedicamentoDto addNewMedicamento(MedicamentoDto medicamentoDto) {
        Laboratorio laboratorio = laboratorioRepository.findById(medicamentoDto.getLaboratorio().getId());

        Medicamento medicamento;
        if (medicamentoDto.getMedicamentos() == null || medicamentoDto.getMedicamentos().isEmpty()) {
            // Si no tiene Medicamentos, es un MedicamentoIndividual
            medicamento = new MedicamentoIndividual();
            ((MedicamentoIndividual) medicamento).setPrecio(medicamentoDto.getPrecio());
        } else {
            // Si tiene Medicamentos, es un ComboMedicamentos
            medicamento = new ComboMedicamentos();
            List<UUID> idsMedicamentosComponentes = medicamentoDto.getMedicamentos().stream()
                    .map(IdTextPair::getId)
                    .collect(Collectors.toList());
            for (UUID idComponente : idsMedicamentosComponentes) {
                Medicamento medicamentoComponente = medicamentoRepository.findById(idComponente);
                ((ComboMedicamentos) medicamento).addMedicamento(medicamentoComponente);
            }
        }

        medicamento.setNombre(medicamentoDto.getNombre());
        laboratorio.addMedicamento(medicamento);
        // return medicamentoService.save(medicamento);
        return MedicamentoDto.from(medicamento);
    }
}
