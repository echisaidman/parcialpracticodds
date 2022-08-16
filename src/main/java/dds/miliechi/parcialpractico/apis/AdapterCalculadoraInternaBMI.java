package dds.miliechi.parcialpractico.apis;

public class AdapterCalculadoraInternaBMI implements CalculadoraBMI {
    @Override
    public double calcular(double alturaEnMetros, double pesoEnKg) {
        double alturaEnCentimetros = alturaEnMetros * 100;
        // Valores por defecto que en algun futuro ideal el sistema guardaria para cada usuario
        int edadPorDefecto = 18;
        char sexoPorDefecto = 'M';
        return new CalculadoraInternaIncompatibleBMI().calcularConOtroSignature(pesoEnKg, alturaEnCentimetros, edadPorDefecto, sexoPorDefecto);
    }
}
