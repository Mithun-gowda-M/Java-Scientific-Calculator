package calculator.advanced;

/**
 * Eqn class provides advanced analytical and algebraic equation solvers:
 * 1. Linear Equations (1 variable): ax + b = 0
 * 2. Quadratic Equations: ax^2 + bx + c = 0 (Real & Complex Conjugate roots)
 * 3. Cubic Equations: ax^3 + bx^2 + cx + d = 0 (Cardano's method)
 * 4. System of 2 Linear Equations (2 variables) via Cramer's Rule
 * 5. System of 3 Linear Equations (3 variables) via 3x3 Cramer's Rule
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor private, result structures immutable with final fields.
 * - Static & Final: Class is final, methods are static, mathematical formulations are immutable.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Eqn {

    private static final double EPSILON = 1e-12;

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Eqn() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // ==========================================
    // 1. LINEAR EQUATION SOLVER
    // ==========================================

    public static double solveLinear(final double a, final double b) {
        if (Math.abs(a) < EPSILON) {
            if (Math.abs(b) < EPSILON) {
                throw new ArithmeticException("Infinite solutions (0x + 0 = 0).");
            } else {
                throw new ArithmeticException("No solution exists (0x + " + b + " = 0 is impossible).");
            }
        }
        return -b / a;
    }

    // Overload for backward compatibility
    public static double solve(final double a, final double b) {
        return solveLinear(a, b);
    }

    // ==========================================
    // 2. QUADRATIC EQUATION SOLVER
    // ==========================================

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
            } else if (Math.abs(root1Real - root2Real) < EPSILON) {
                return String.format("Single repeated real root: x = %.4f", root1Real);
            } else {
                return String.format("Root 1 = %.4f\nRoot 2 = %.4f (Real Distinct Roots)", root1Real, root2Real);
            }
        }
    }

    public static QuadraticResult solveQuadratic(final double a, final double b, final double c) {
        if (Math.abs(a) < EPSILON) {
            throw new ArithmeticException("Not a quadratic equation: coefficient 'a' cannot be zero.");
        }

        final double discriminant = (b * b) - (4.0 * a * c);

        if (discriminant > EPSILON) {
            final double sqrtD = Math.sqrt(discriminant);
            final double r1 = (-b + sqrtD) / (2.0 * a);
            final double r2 = (-b - sqrtD) / (2.0 * a);
            return new QuadraticResult(r1, 0, r2, 0, false);
        } else if (Math.abs(discriminant) <= EPSILON) {
            final double r = -b / (2.0 * a);
            return new QuadraticResult(r, 0, r, 0, false);
        } else {
            final double realPart = -b / (2.0 * a);
            final double imagPart = Math.sqrt(-discriminant) / (2.0 * a);
            return new QuadraticResult(realPart, imagPart, realPart, -imagPart, true);
        }
    }

    // ==========================================
    // 3. CUBIC EQUATION SOLVER (Cardano's Method)
    // ==========================================

    public static final class CubicResult {
        public final double r1;
        public final double r2Real, r2Imag;
        public final double r3Real, r3Imag;
        public final boolean allReal;

        public CubicResult(double r1, double r2Real, double r2Imag, double r3Real, double r3Imag, boolean allReal) {
            this.r1 = r1;
            this.r2Real = r2Real;
            this.r2Imag = r2Imag;
            this.r3Real = r3Real;
            this.r3Imag = r3Imag;
            this.allReal = allReal;
        }

        @Override
        public String toString() {
            if (allReal) {
                return String.format("Root 1 = %.4f\nRoot 2 = %.4f\nRoot 3 = %.4f", r1, r2Real, r3Real);
            } else {
                return String.format("Root 1 = %.4f (Real)\nRoot 2 = %.4f + %.4fi\nRoot 3 = %.4f - %.4fi", 
                        r1, r2Real, Math.abs(r2Imag), r3Real, Math.abs(r3Imag));
            }
        }
    }

    public static CubicResult solveCubic(final double a, final double b, final double c, final double d) {
        if (Math.abs(a) < EPSILON) {
            throw new ArithmeticException("Coefficient 'a' cannot be zero for cubic equation.");
        }

        // Normalize: x^3 + a1*x^2 + a2*x + a3 = 0
        final double a1 = b / a;
        final double a2 = c / a;
        final double a3 = d / a;

        // Substitute x = t - a1/3 to form depressed cubic: t^3 + pt + q = 0
        final double p = a2 - (a1 * a1) / 3.0;
        final double q = (2.0 * a1 * a1 * a1) / 27.0 - (a1 * a2) / 3.0 + a3;
        final double delta = (q * q) / 4.0 + (p * p * p) / 27.0;
        final double offset = -a1 / 3.0;

        if (delta > EPSILON) {
            // One real root, two complex conjugate roots
            final double u = Math.cbrt(-q / 2.0 + Math.sqrt(delta));
            final double v = Math.cbrt(-q / 2.0 - Math.sqrt(delta));
            final double t1 = u + v;
            final double realPart = -(u + v) / 2.0 + offset;
            final double imagPart = (Math.sqrt(3.0) / 2.0) * (u - v);

            return new CubicResult(t1 + offset, realPart, imagPart, realPart, -imagPart, false);
        } else if (Math.abs(delta) <= EPSILON) {
            // All roots real, at least two are equal
            final double u = Math.cbrt(-q / 2.0);
            final double r1 = 2.0 * u + offset;
            final double r2 = -u + offset;
            return new CubicResult(r1, r2, 0, r2, 0, true);
        } else {
            // Three distinct real roots (casus irreducibilis)
            final double r = Math.sqrt(-(p * p * p) / 27.0);
            final double phi = Math.acos(-q / (2.0 * r));
            final double m = 2.0 * Math.cbrt(r);

            final double r1 = m * Math.cos(phi / 3.0) + offset;
            final double r2 = m * Math.cos((phi + 2.0 * Math.PI) / 3.0) + offset;
            final double r3 = m * Math.cos((phi + 4.0 * Math.PI) / 3.0) + offset;
            return new CubicResult(r1, r2, 0, r3, 0, true);
        }
    }

    // ==========================================
    // 4. 2-VARIABLE LINEAR SYSTEM SOLVER (Cramer)
    // ==========================================

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

    public static System2Result solveSystem2(final double a1, final double b1, final double c1,
                                             final double a2, final double b2, final double c2) {
        final double d = (a1 * b2) - (a2 * b1);
        if (Math.abs(d) < EPSILON) {
            final double dx = (c1 * b2) - (c2 * b1);
            if (Math.abs(dx) < EPSILON) {
                throw new ArithmeticException("Infinite solutions exist (dependent coincident lines).");
            } else {
                throw new ArithmeticException("No unique solution exists (parallel inconsistent lines).");
            }
        }
        final double x = ((c1 * b2) - (c2 * b1)) / d;
        final double y = ((a1 * c2) - (a2 * c1)) / d;
        return new System2Result(x, y);
    }

    // ==========================================
    // 5. 3-VARIABLE LINEAR SYSTEM SOLVER (Cramer)
    // ==========================================

    public static final class System3Result {
        public final double x, y, z;

        public System3Result(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return String.format("x = %.4f, y = %.4f, z = %.4f", x, y, z);
        }
    }

    public static System3Result solveSystem3(final double[][] a, final double[] d) {
        if (a.length != 3 || a[0].length != 3 || d.length != 3) {
            throw new IllegalArgumentException("System 3 requires a 3x3 coefficient matrix and 3 constants.");
        }

        final double detA = Matrix.determinant(a);
        if (Math.abs(detA) < EPSILON) {
            throw new ArithmeticException("Determinant is zero: System does not have a unique solution.");
        }

        // Replace col 0 with d
        final double[][] ax = replaceColumn(a, d, 0);
        final double detX = Matrix.determinant(ax);

        // Replace col 1 with d
        final double[][] ay = replaceColumn(a, d, 1);
        final double detY = Matrix.determinant(ay);

        // Replace col 2 with d
        final double[][] az = replaceColumn(a, d, 2);
        final double detZ = Matrix.determinant(az);

        return new System3Result(detX / detA, detY / detA, detZ / detA);
    }

    private static double[][] replaceColumn(final double[][] src, final double[] col, final int colIdx) {
        final double[][] copy = new double[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                copy[i][j] = (j == colIdx) ? col[i] : src[i][j];
            }
        }
        return copy;
    }
}
