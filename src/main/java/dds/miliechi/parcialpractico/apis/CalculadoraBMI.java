package dds.miliechi.parcialpractico.apis;

import java.io.IOException;

public interface CalculadoraBMI {
    /**
     * @param altura La altura en metros
     * @param peso   El peso en kilogramos
     */
    double calcular(double altura, double peso) throws IOException;
}
