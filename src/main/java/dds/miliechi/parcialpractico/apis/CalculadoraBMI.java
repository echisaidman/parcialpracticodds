package dds.miliechi.parcialpractico.apis;

import java.io.IOException;

public interface CalculadoraBMI {
    double calcular(double alturaEnMetros, double pesoEnKg) throws IOException;
}
