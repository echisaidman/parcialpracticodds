package dds.miliechi.parcialpractico.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CalcularBMIResponse {
    private final double bmi;

    public CalcularBMIResponse(double bmi) {
        this.bmi = bmi;
    }
}
