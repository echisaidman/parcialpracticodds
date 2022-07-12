package dds.miliechi.parcialpractico.dtos;

import dds.miliechi.parcialpractico.entities.Empresa;
import dds.miliechi.parcialpractico.entities.Medicamento;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class EmpresaDto {
    private UUID id;
    private String nombre;
    private List<MedicamentoDto> medicamentosDtos = new ArrayList<>();

    public static EmpresaDto from(Empresa empresa) {
        EmpresaDto empresaDto = new EmpresaDto();
        empresaDto.setId(empresa.getId());
        empresaDto.setNombre(empresa.getNombre());
        for (Medicamento medicamento : empresa.getMedicamentos()) {
            MedicamentoDto medicamentoDto = MedicamentoDto.from(medicamento);
            empresaDto.getMedicamentosDtos().add(medicamentoDto);
        }
        return empresaDto;
    }
}
