package dds.miliechi.parcialpractico.apis;

public class CalculadoraInternaIncompatibleBMI {

    double calcularConOtroSignature(double pesoEnKg, double alturaEnCentimetros, Integer edad, Character sexo) {
        // Imaginamos que en el sistema se implemento un algoritmo cualquiera que devuelve
        // el BMI calculado manualmente.
        double alturaEnMetros = alturaEnCentimetros / 100;

        if (edad >= 18) {
            return sexo.equals('M')
                    ? pesoEnKg / Math.pow(alturaEnMetros, 2)
                    : pesoEnKg / Math.pow(alturaEnMetros - 5, 2);
        }
        return sexo.equals('M')
                ? (pesoEnKg - 5) / Math.pow(alturaEnMetros - 5, 2)
                : (pesoEnKg - 10) / Math.pow(alturaEnMetros - 5, 2);
    }

}
