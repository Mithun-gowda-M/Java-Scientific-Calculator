package calculator.advanced;

/**
 * Trig class provides an advanced suite of trigonometric, inverse, hyperbolic,
 * and numerical calculus functions (numerical differentiation and integration).
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, helper methods private.
 * - Static & Final: Class is final, methods and mathematical constants are static final.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Trig {

    // Mathematical Constants
    public static final double PI = Math.PI;
    public static final double E = Math.E;
    private static final double EPSILON = 1e-12;

    // Angle mode state
    public enum AngleMode {
        DEGREES, RADIANS
    }

    private static AngleMode currentMode = AngleMode.DEGREES;

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Trig() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static AngleMode getAngleMode() {
        return currentMode;
    }

    public static void setAngleMode(final AngleMode mode) {
        if (mode != null) {
            currentMode = mode;
        }
    }

    // Angle conversion helpers
    public static double toRadians(final double angle) {
        return currentMode == AngleMode.DEGREES ? Math.toRadians(angle) : angle;
    }

    public static double fromRadians(final double rad) {
        return currentMode == AngleMode.DEGREES ? Math.toDegrees(rad) : rad;
    }

    // Standard Trigonometric Functions
    public static double sin(final double angle) {
        return Math.sin(toRadians(angle));
    }

    public static double cos(final double angle) {
        return Math.cos(toRadians(angle));
    }

    public static double tan(final double angle) {
        final double rad = toRadians(angle);
        if (Math.abs(Math.cos(rad)) < EPSILON) {
            throw new ArithmeticException("Tangent is undefined for angle " + angle + " " + currentMode + " (cos is zero).");
        }
        return Math.tan(rad);
    }

    // Reciprocal Trigonometric Functions
    public static double cosec(final double angle) {
        final double s = sin(angle);
        if (Math.abs(s) < EPSILON) {
            throw new ArithmeticException("Cosecant is undefined for angle " + angle + " " + currentMode + " (sin is zero).");
        }
        return 1.0 / s;
    }

    public static double sec(final double angle) {
        final double c = cos(angle);
        if (Math.abs(c) < EPSILON) {
            throw new ArithmeticException("Secant is undefined for angle " + angle + " " + currentMode + " (cos is zero).");
        }
        return 1.0 / c;
    }

    public static double cot(final double angle) {
        final double s = sin(angle);
        if (Math.abs(s) < EPSILON) {
            throw new ArithmeticException("Cotangent is undefined for angle " + angle + " " + currentMode + " (sin is zero).");
        }
        return cos(angle) / s;
    }

    // Inverse Trigonometric Functions (output respects current AngleMode)
    public static double asin(final double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("asin domain error: value must be in range [-1, 1]. Received: " + value);
        }
        final double rad = Math.asin(value);
        return currentMode == AngleMode.DEGREES ? Math.toDegrees(rad) : rad;
    }

    public static double acos(final double value) {
        if (value < -1.0 || value > 1.0) {
            throw new IllegalArgumentException("acos domain error: value must be in range [-1, 1]. Received: " + value);
        }
        final double rad = Math.acos(value);
        return currentMode == AngleMode.DEGREES ? Math.toDegrees(rad) : rad;
    }

    public static double atan(final double value) {
        final double rad = Math.atan(value);
        return currentMode == AngleMode.DEGREES ? Math.toDegrees(rad) : rad;
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

    public static double asinh(final double x) {
        // asinh(x) = ln(x + sqrt(x^2 + 1))
        return Math.log(x + Math.sqrt(x * x + 1.0));
    }

    public static double acosh(final double x) {
        if (x < 1.0) {
            throw new IllegalArgumentException("acosh domain error: x must be >= 1. Received: " + x);
        }
        // acosh(x) = ln(x + sqrt(x^2 - 1))
        return Math.log(x + Math.sqrt(x * x - 1.0));
    }

    public static double atanh(final double x) {
        if (x <= -1.0 || x >= 1.0) {
            throw new IllegalArgumentException("atanh domain error: x must be in range (-1, 1). Received: " + x);
        }
        // atanh(x) = 0.5 * ln((1 + x) / (1 - x))
        return 0.5 * Math.log((1.0 + x) / (1.0 - x));
    }

    // ==========================================
    // NUMERICAL CALCULUS EXTENSIONS
    // ==========================================

    @FunctionalInterface
    public interface SingleVariableFunction {
        double apply(double x);
    }

    /**
     * Computes the numerical derivative f'(x) using Central Difference Method.
     * f'(x) = [f(x + h) - f(x - h)] / (2h)
     */
    public static double derivative(final SingleVariableFunction f, final double x) {
        final double h = 1e-6;
        return (f.apply(x + h) - f.apply(x - h)) / (2.0 * h);
    }

    /**
     * Computes the numerical definite integral of f(x) from a to b using Simpson's 1/3 Rule.
     */
    public static double definiteIntegral(final SingleVariableFunction f, final double a, final double b, final int intervals) {
        final int n = (intervals % 2 == 0) ? intervals : intervals + 1; // n must be even
        final double h = (b - a) / n;
        double sum = f.apply(a) + f.apply(b);

        for (int i = 1; i < n; i++) {
            final double x = a + i * h;
            sum += (i % 2 == 0 ? 2.0 : 4.0) * f.apply(x);
        }
        return (h / 3.0) * sum;
    }
}
