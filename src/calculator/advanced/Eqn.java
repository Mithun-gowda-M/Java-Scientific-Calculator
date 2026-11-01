package calculator.advanced;

/**
 * Eqn class provides solvers for:
 * 1. Linear equations in 1 variable: ax + b = 0
 * 2. Quadratic equations: ax^2 + bx + c = 0 (distinct real, repeated real, and complex roots)
 * 3. System of 2 Linear equations in 2 variables (Cramer's Rule):
 *    a1*x + b1*y = c1
 *    a2*x + b2*y = c2
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, inner result classes public static.
 * - Static & Final: Class is final, methods are static, immutability with final fields and params.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Eqn {

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Eqn() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // Linear equation solver: ax + b = 0 => x = -b / a
    public static double solve(final double a, final double b) {
        if (a == 0) {
            if (b == 0) {
                throw new ArithmeticException("Infinite solutions exist (0x + 0 = 0).");
            } else {
                throw new ArithmeticException("No solution exists (0x + " + b + " = 0 is a mathematical contradiction).");
            }
        }
        return -b / a;
    }

    // Immutable container for quadratic equation results (Demonstrates final fields)
    public static final class QuadraticResult {
        public final double root1Real;
        public final double root1Imag;
        public final double root2Real;
        public final double root2Imag;
        public final boolean isComplex;

        public QuadraticResult(final double r1Real, final double r1Imag, 
                               final double r2Real, final double r2Imag, 
                               final boolean isComplex) {
            this.root1Real = r1Real;
            this.root1Imag = r1Imag;
            this.root2Real = r2Real;
            this.root2Imag = r2Imag;
            this.isComplex = isComplex;
        }

        @Override
        public String toString() {
            if (isComplex) {
                return String.format("Root 1 = %.4f + %.4fi\nRoot 2 = %.4f - %.4fi (Complex Conjugates)", 
                        root1Real, Math.abs(root1Imag), root2Real, Math.abs(root2Imag));
            } else if (Math.abs(root1Real - root2Real) < 1e-12) {
                return String.format("Single repeated real root: x = %.4f", root1Real);
            } else {
                return String.format("Root 1 = %.4f\nRoot 2 = %.4f (Real Distinct Roots)", root1Real, root2Real);
            }
        }
    }

    // Quadratic equation solver: ax^2 + bx + c = 0
    public static QuadraticResult solveQuadratic(final double a, final double b, final double c) {
        if (a == 0) {
            throw new ArithmeticException("Not a quadratic equation: coefficient 'a' cannot be zero.");
        }

        final double discriminant = (b * b) - (4.0 * a * c);

        if (discriminant > 0) {
            // Two distinct real roots
            final double sqrtD = Math.sqrt(discriminant);
            final double r1 = (-b + sqrtD) / (2.0 * a);
            final double r2 = (-b - sqrtD) / (2.0 * a);
            return new QuadraticResult(r1, 0, r2, 0, false);
        } else if (Math.abs(discriminant) < 1e-12) {
            // One real repeated root
            final double r = -b / (2.0 * a);
            return new QuadraticResult(r, 0, r, 0, false);
        } else {
            // Complex conjugate roots
            final double realPart = -b / (2.0 * a);
            final double imagPart = Math.sqrt(-discriminant) / (2.0 * a);
            return new QuadraticResult(realPart, imagPart, realPart, -imagPart, true);
        }
    }

    // Immutable container for 2-Variable Linear System results
    public static final class System2Result {
        public final double x;
        public final double y;

        public System2Result(final double x, final double y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public String toString() {
            return String.format("x = %.4f, y = %.4f", x, y);
        }
    }

    // System of 2 Linear equations using Cramer's Rule:
    // a1*x + b1*y = c1
    // a2*x + b2*y = c2
    public static System2Result solveSystem2(final double a1, final double b1, final double c1,
                                             final double a2, final double b2, final double c2) {
        final double determinant = (a1 * b2) - (a2 * b1);
        if (Math.abs(determinant) < 1e-12) {
            final double determinantX = (c1 * b2) - (c2 * b1);
            if (Math.abs(determinantX) < 1e-12) {
                throw new ArithmeticException("Infinite solutions exist (the two lines are coincident).");
            } else {
                throw new ArithmeticException("No unique solution exists (the two lines are parallel and distinct).");
            }
        }

        final double x = ((c1 * b2) - (c2 * b1)) / determinant;
        final double y = ((a1 * c2) - (a2 * c1)) / determinant;
        return new System2Result(x, y);
    }
}

