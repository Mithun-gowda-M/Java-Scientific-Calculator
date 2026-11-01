package calculator.advanced;

/**
 * Trig class provides comprehensive trigonometric, reciprocal, 
 * inverse, and hyperbolic operations.
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private to prevent instantiation.
 * - Static & Final: Class is final (cannot be subclassed), methods and constants are static final.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Trig {

    // Mathematical constants (Static & Final)
    public static final double PI = Math.PI;
    public static final double E = Math.E;
    private static final double EPSILON = 1e-12;

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Trig() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // Angle conversion helpers
    public static double toRadians(final double degrees) {
        return Math.toRadians(degrees);
    }

    public static double toDegrees(final double radians) {
        return Math.toDegrees(radians);
    }

    // Standard Trigonometric Functions (input in degrees)
    public static double sin(final double degrees) {
        return Math.sin(Math.toRadians(degrees));
    }

    public static double cos(final double degrees) {
        return Math.cos(Math.toRadians(degrees));
    }

    public static double tan(final double degrees) {
        final double rad = Math.toRadians(degrees);
        if (Math.abs(Math.cos(rad)) < EPSILON) {
            throw new ArithmeticException("Tangent is undefined for " + degrees + " degrees (cosine equals zero).");
        }
        return Math.tan(rad);
    }

    // Reciprocal Trigonometric Functions
    public static double cosec(final double degrees) {
        final double s = sin(degrees);
        if (Math.abs(s) < EPSILON) {
            throw new ArithmeticException("Cosecant is undefined for " + degrees + " degrees (sine equals zero).");
        }
        return 1.0 / s;
    }

    public static double sec(final double degrees) {
        final double c = cos(degrees);
        if (Math.abs(c) < EPSILON) {
            throw new ArithmeticException("Secant is undefined for " + degrees + " degrees (cosine equals zero).");
        }
        return 1.0 / c;
    }

    public static double cot(final double degrees) {
        final double s = sin(degrees);
        if (Math.abs(s) < EPSILON) {
            throw new ArithmeticException("Cotangent is undefined for " + degrees + " degrees (sine equals zero).");
        }
        return cos(degrees) / s;
    }

    // Inverse Trigonometric Functions (output in degrees)
    public static double asin(final double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("asin domain error: value must be between -1 and 1. Received: " + value);
        }
        return Math.toDegrees(Math.asin(value));
    }

    public static double acos(final double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("acos domain error: value must be between -1 and 1. Received: " + value);
        }
        return Math.toDegrees(Math.acos(value));
    }

    public static double atan(final double value) {
        return Math.toDegrees(Math.atan(value));
    }

    // Hyperbolic Functions
    public static double sinh(final double x) {
        return Math.sinh(x);
    }

    public static double cosh(final double x) {
        return Math.cosh(x);
    }

    public static double tanh(final double x) {
        return Math.tanh(x);
    }
}

