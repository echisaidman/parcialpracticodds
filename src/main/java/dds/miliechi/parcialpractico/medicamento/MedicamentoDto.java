package dds.miliechi.parcialpractico.medicamento;

import dds.miliechi.parcialpractico.dtos.IdTextPair;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class MedicamentoDto {

    private long id;
    private String nombre;
    private IdTextPair laboratorio;
    private List<IdTextPair> medicamentos; // Los medicamentos del combo (en caso que este medicamento sea un Combo)
    private Double precio;

    public static MedicamentoDto from(Medicamento medicamento) {
        MedicamentoDto medicamentoDto = new MedicamentoDto();
        medicamentoDto.setId(medicamento.getId());
        medicamentoDto.setNombre(medicamento.getNombre());
        medicamentoDto.setLaboratorio(
                new IdTextPair(medicamento.getLaboratorio().getId(), medicamento.getLaboratorio().getNombre())
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
