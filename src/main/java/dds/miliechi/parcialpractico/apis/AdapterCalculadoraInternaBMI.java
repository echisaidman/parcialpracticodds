package dds.miliechi.parcialpractico.apis;

public class AdapterCalculadoraInternaBMI implements CalculadoraBMI {
    @Override
    public double calcular(double altura, double peso) {
        double alturaEnCentimetros = altura * 100;
        return new CalculadoraInternaIncompatibleBMI().calcularConOtroSignature(peso, alturaEnCentimetros, null, null);
    }
}
