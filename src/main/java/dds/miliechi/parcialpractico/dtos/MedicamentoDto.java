package dds.miliechi.parcialpractico.dtos;

import dds.miliechi.parcialpractico.entities.Medicamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class MedicamentoDto {

    private UUID id;
    private String nombre;
    private IdTextPair empresa;

    public static MedicamentoDto from(Medicamento medicamento) {
        MedicamentoDto medicamentoDto = new MedicamentoDto();
        medicamentoDto.setId(medicamento.getId());
        medicamentoDto.setNombre(medicamento.getNombre());
        medicamentoDto.setEmpresa(new IdTextPair(medicamento.getEmpresa().getId(),
                medicamento.getEmpresa().getNombre()));
        return medicamentoDto;
    }
}
