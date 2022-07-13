package dds.miliechi.parcialpractico.services;

import dds.miliechi.parcialpractico.dtos.EmpresaDto;
import dds.miliechi.parcialpractico.dtos.IdTextPair;
import dds.miliechi.parcialpractico.dtos.MedicamentoDto;
import dds.miliechi.parcialpractico.entities.ComboMedicamentos;
import dds.miliechi.parcialpractico.entities.Empresa;
import dds.miliechi.parcialpractico.entities.Medicamento;
import dds.miliechi.parcialpractico.entities.MedicamentoIndividual;
import dds.miliechi.parcialpractico.repositories.EmpresaRepository;
import dds.miliechi.parcialpractico.repositories.MedicamentoRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;
    private final MedicamentoRepository medicamentoRepository;
    // private final MedicamentoService medicamentoService;

    public EmpresaService(EmpresaRepository empresaRepository, MedicamentoRepository medicamentoRepository) {
        this.empresaRepository = empresaRepository;
        // this.medicamentoService = medicamentoService;
        this.medicamentoRepository = medicamentoRepository;
    }

    @Transactional
    public Optional<EmpresaDto> findByName(String name) {
        Optional<Empresa> empresaOptional = empresaRepository.findByName(name);
        return empresaOptional.map(EmpresaDto::from);
    }

    @Transactional
    public EmpresaDto findById(UUID id) {
        return EmpresaDto.from(empresaRepository.findById(id));
    }

    @Transactional
    public EmpresaDto save(EmpresaDto empresaDto) {
        Empresa empresa = new Empresa();
        empresa.setNombre(empresaDto.getNombre());
        empresaRepository.save(empresa);
        return EmpresaDto.from(empresa);
    }

    @Transactional
    public List<EmpresaDto> list() {
        List<Empresa> empresas = empresaRepository.list();
        return empresas.stream().map(EmpresaDto::from).collect(Collectors.toList());
    }

    @Transactional
    public MedicamentoDto addNewMedicamento(MedicamentoDto medicamentoDto) {
        Empresa empresa = empresaRepository.findById(medicamentoDto.getEmpresa().getId());

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
                ((ComboMedicamentos) medicamento).getMedicamentos().add(medicamentoComponente);
            }
        }

        medicamento.setNombre(medicamentoDto.getNombre());
        empresa.addMedicamento(medicamento);
        // return medicamentoService.save(medicamento);
        return MedicamentoDto.from(medicamento);
    }
}
