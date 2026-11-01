package calculator;

/**
 * UnitConverter provides engineering conversions (Length, Temperature, Mass, Pressure)
 * and standard scientific physical constants.
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor private, constants public static final.
 * - Static & Final: Class is final, methods and constants are static final.
 * 
 * Contributed by: Mithun Gowda M (@Mithun-gowda-M)
 */
public final class UnitConverter {

    // Standard Physical & Fundamental Constants (SI Units)
    public static final double SPEED_OF_LIGHT = 299792458.0;          // m/s (c)
    public static final double PLANCK_CONSTANT = 6.62607015e-34;      // J*s (h)
    public static final double GRAVITATIONAL_CONST = 6.67430e-11;     // m^3/(kg*s^2) (G)
    public static final double AVOGADRO_NUMBER = 6.02214076e23;       // mol^-1 (N_A)
    public static final double BOLTZMANN_CONST = 1.380649e-23;        // J/K (k)
    public static final double ELECTRON_CHARGE = 1.602176634e-19;     // Coulombs (e)

    private UnitConverter() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // Temperature Conversions
    public static double celsiusToFahrenheit(final double c) {
        return (c * 9.0 / 5.0) + 32.0;
    }

    public static double fahrenheitToCelsius(final double f) {
        return (f - 32.0) * 5.0 / 9.0;
    }

    public static double celsiusToKelvin(final double c) {
        return c + 273.15;
    }

    public static double kelvinToCelsius(final double k) {
        if (k < 0) throw new IllegalArgumentException("Temperature in Kelvin cannot be negative.");
        return k - 273.15;
    }

    // Length Conversions (Base: meters)
    public static double metersToFeet(final double meters) {
        return meters * 3.28084;
    }

    public static double feetToMeters(final double feet) {
        return feet / 3.28084;
    }

    public static double kilometersToMiles(final double km) {
        return km * 0.621371;
    }

    public static double milesToKilometers(final double miles) {
        return miles / 0.621371;
    }

    public static double inchesToCentimeters(final double inches) {
        return inches * 2.54;
    }

    public static double centimetersToInches(final double cm) {
        return cm / 2.54;
    }

    // Mass Conversions
    public static double kilogramsToPounds(final double kg) {
        return kg * 2.20462;
    }

    public static double poundsToKilograms(final double lbs) {
        return lbs / 2.20462;
    }
}
