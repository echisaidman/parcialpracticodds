package dds.miliechi.parcialpractico.laboratorio;

import dds.miliechi.parcialpractico.medicamento.Medicamento;
import dds.miliechi.parcialpractico.medicamento.MedicamentoDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class LaboratorioDto {
    private long id;
    private String nombre;
    private List<MedicamentoDto> medicamentosDtos = new ArrayList<>();

    public static LaboratorioDto from(Laboratorio laboratorio) {
        LaboratorioDto laboratorioDto = new LaboratorioDto();
        laboratorioDto.setId(laboratorio.getId());
        laboratorioDto.setNombre(laboratorio.getNombre());
        for (Medicamento medicamento : laboratorio.getMedicamentos()) {
            MedicamentoDto medicamentoDto = MedicamentoDto.from(medicamento);
            laboratorioDto.getMedicamentosDtos().add(medicamentoDto);
        }
        return laboratorioDto;
    }
}
