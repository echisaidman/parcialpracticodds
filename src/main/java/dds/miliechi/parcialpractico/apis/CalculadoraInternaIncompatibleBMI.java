package dds.miliechi.parcialpractico.apis;

public class CalculadoraInternaIncompatibleBMI {

    /**
     * Metodo "heredado" del sistema que no se conforma a la signature de la interfaz CalculadoraBMI
     *
     * @param peso   El peso en kilogramos
     * @param altura La altura en centimetros
     */
    double calcularConOtroSignature(double peso, double altura, Integer edad, Character sexo) {
        // Imaginamos que en el sistema se implemento un algoritmo cualquiera que devuelve
        // el BMI calculado manualmente.
        if (sexo == null) {
            sexo = 'M';
        }
        if (edad == null) {
            edad = 25;
        }

        if (edad > 18) {
            return sexo == 'M'
                    ? altura * peso
                    : altura * (peso - 5);
        }
        return sexo == 'M'
                ? (altura - 5) * (peso - 5)
                : (altura - 5) * (peso - 10);
    }

}
