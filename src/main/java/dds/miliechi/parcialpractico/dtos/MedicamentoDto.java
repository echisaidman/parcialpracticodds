package dds.miliechi.parcialpractico.dtos;

import dds.miliechi.parcialpractico.entities.ComboMedicamentos;
import dds.miliechi.parcialpractico.entities.Medicamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MedicamentoDto {

    private UUID id;
    private String nombre;
    private IdTextPair empresa;
    private List<IdTextPair> medicamentos; // Los medicamentos del combo (en caso que este medicamento sea un Combo)
    private Double precio;

    public static MedicamentoDto from(Medicamento medicamento) {
        MedicamentoDto medicamentoDto = new MedicamentoDto();
        medicamentoDto.setId(medicamento.getId());
        medicamentoDto.setNombre(medicamento.getNombre());
        medicamentoDto.setEmpresa(
                new IdTextPair(medicamento.getEmpresa().getId(), medicamento.getEmpresa().getNombre())
        );
        medicamentoDto.setPrecio(medicamento.getPrecio());

        if (medicamento instanceof ComboMedicamentos) {
            ComboMedicamentos combo = (ComboMedicamentos) medicamento;
            medicamentoDto.setMedicamentos(
                    combo.getMedicamentos().stream()
                            .map(m -> new IdTextPair(m.getId(), m.getNombre()))
                            .collect(Collectors.toList())
            );
        }

        return medicamentoDto;
    }
}
