package calculator;

import java.util.Scanner;
import java.util.List;
import calculator.advanced.Trig;
import calculator.advanced.Matrix;
import calculator.advanced.Eqn;
import calculator.advanced.Statistics;

/**
 * Calc is the comprehensive interactive driver for the Java Scientific Calculator.
 * 
 * Requisites demonstrated:
 * - Access Control:
 *     * public class Calc (main entrypoint)
 *     * private static final constants and state variables (Scanner, Memory, History)
 *     * private static helper methods for module management and encapsulated parsing
 * - Static & Final:
 *     * final class constants (APP_TITLE, VERSION, MAX_HISTORY)
 *     * static pure functions and operational subroutines
 *     * final parameters preventing parameter mutation
 * 
 * Collaborative Activity Split:
 * - Mithun Gowda M (@Mithun-gowda-M):
 *     * Core Application Architecture & Interactive Engine
 *     * Live Memory Registers (MS, MR, M+, M-, MC, Ans) & History Buffer
 *     * Programmer Base Converter & Bitwise Calculator (BaseCalc.java)
 *     * Discrete Math, Combinatorics & Number Theory (DiscreteMath.java)
 *     * Unit Converter & Physical Constants (UnitConverter.java)
 * - Rakshitha R (@rakshitha-205):
 *     * Advanced Trigonometric, Hyperbolic & Calculus Suite (Trig.java)
 *     * Arbitrary NxN Matrix Algebra, Inversion & Determinants (Matrix.java)
 *     * Linear, Quadratic, Cubic & System Equation Solvers (Eqn.java)
 *     * Descriptive & Inferential Statistics & Linear Regression (Statistics.java)
 */
public class Calc {

    // Final Application Constants
    public static final String APP_TITLE = "JAVA ADVANCED SCIENTIFIC CALCULATOR";
    public static final String VERSION = "3.0-PRO";
    private static final int MAX_HISTORY = 50;

    // Encapsulated Application State
    private static final Scanner sc = new Scanner(System.in);
    private static final String[] history = new String[MAX_HISTORY];
    private static int historyCount = 0;
    private static double memoryRegister = 0.0;
    private static double lastAnswer = 0.0;

    public static void main(String[] args) {
        int choice = -1;

        System.out.println("===============================================================================");
        System.out.println("                   " + APP_TITLE + " (v" + VERSION + ")");
        System.out.println("             Collaborative Project: Mithun Gowda M & Rakshitha R");
        System.out.println("===============================================================================");

        do {
            printActiveStatusBar();
            printMainMenu();
            choice = readInt("Enter your choice (1-12): ");

            switch (choice) {
                case 1:
                    handleBasicArithmetic();
                    break;
                case 2:
                    handleScientificMath();
                    break;
                case 3:
                    handleTrigonometryAndCalculus();
                    break;
                case 4:
                    handleMatrixOperations();
                    break;
                case 5:
                    handleEquationSolvers();
                    break;
                case 6:
                    handleStatistics();
                    break;
                case 7:
                    handleProgrammerBaseCalc();
                    break;
                case 8:
                    handleDiscreteMath();
                    break;
                case 9:
                    handleUnitConversions();
                    break;
                case 10:
                    handleMemoryAndHistory();
                    break;
                case 11:
                    displayProjectDetails();
                    break;
                case 12:
                    System.out.println("\nThank you for using " + APP_TITLE + ". Goodbye!");
                    break;
                default:
                    System.out.println("Invalid selection! Please enter an integer from 1 to 12.");
            }
        } while (choice != 12);

        sc.close();
    }

    // ==========================================
    // STATUS BAR & MENUS
    // ==========================================

    private static void printActiveStatusBar() {
        System.out.println("\n-------------------------------------------------------------------------------");
        System.out.printf("[ STATUS ] Angle Mode: %-7s | Memory (M): %-8.2f | Last Ans: %-8.4f%n",
                Trig.getAngleMode(), memoryRegister, lastAnswer);
        System.out.println("-------------------------------------------------------------------------------");
    }

    private static void printMainMenu() {
        System.out.println(" 1. Basic Arithmetic (+, -, *, /, %)");
        System.out.println(" 2. Scientific Math (Powers, Roots, Logs, Abs)");
        System.out.println(" 3. Trigonometry, Hyperbolic & Calculus Suite [Rakshitha]");
        System.out.println(" 4. Matrix Algebra, Determinants & Inversion [Rakshitha]");
        System.out.println(" 5. Equation Solvers (Linear, Quadratic, Cubic, Systems) [Rakshitha]");
        System.out.println(" 6. Statistics & Linear Regression [Rakshitha]");
        System.out.println(" 7. Programmer Base Converter & Bitwise Operations [Mithun]");
        System.out.println(" 8. Discrete Math, GCD/LCM, Primes & Combinatorics [Mithun]");
        System.out.println(" 9. Unit Conversions & Physical Constants [Mithun]");
        System.out.println("10. Memory Registers (MS, MR, M+, M-, MC) & History [Mithun]");
        System.out.println("11. About Project & Collaborative Split");
        System.out.println("12. Exit");
        System.out.println("-------------------------------------------------------------------------------");
    }

    // ==========================================
    // MODULE 1: BASIC ARITHMETIC (Mithun)
    // ==========================================

    private static void handleBasicArithmetic() {
        System.out.println("\n--- BASIC ARITHMETIC ---");
        System.out.println("1. Addition (+)");
        System.out.println("2. Subtraction (-)");
        System.out.println("3. Multiplication (*)");
        System.out.println("4. Division (/)");
        System.out.println("5. Modulus / Remainder (%)");
        System.out.println("6. Back to Main Menu");
        final int op = readInt("Select operation (1-6): ");
        if (op == 6) return;

        final double a = readOperand("Enter first number (or 'Ans'): ");
        final double b = readOperand("Enter second number (or 'Ans'): ");
        double res = 0.0;
        String expr = "";

        switch (op) {
            case 1:
                res = a + b;
                expr = a + " + " + b + " = " + res;
                break;
            case 2:
                res = a - b;
                expr = a + " - " + b + " = " + res;
                break;
            case 3:
                res = a * b;
                expr = a + " * " + b + " = " + res;
                break;
            case 4:
                if (b == 0.0) {
                    System.out.println("Error: Division by zero is undefined.");
                    return;
                }
                res = a / b;
                expr = a + " / " + b + " = " + res;
                break;
            case 5:
                if (b == 0.0) {
                    System.out.println("Error: Modulo by zero is undefined.");
                    return;
                }
                res = a % b;
                expr = a + " % " + b + " = " + res;
                break;
            default:
                System.out.println("Invalid operation choice.");
                return;
        }

        setAnswer(res, expr);
    }

    // ==========================================
    // MODULE 2: SCIENTIFIC MATH (Mithun)
    // ==========================================

    private static void handleScientificMath() {
        System.out.println("\n--- SCIENTIFIC MATH ---");
        System.out.println("1. Power (x^y)");
        System.out.println("2. Square Root (sqrt(x))");
        System.out.println("3. Cube Root (cbrt(x))");
        System.out.println("4. N-th Root (x^(1/n))");
        System.out.println("5. Common Logarithm (log10 x)");
        System.out.println("6. Natural Logarithm (ln x)");
        System.out.println("7. Exponential (e^x)");
        System.out.println("8. Absolute Value (|x|)");
        System.out.println("9. Back to Main Menu");
        final int op = readInt("Select operation (1-9): ");
        if (op == 9) return;

        double res = 0.0;
        String expr = "";

        switch (op) {
            case 1:
                final double base = readOperand("Enter base (x): ");
                final double exp = readOperand("Enter exponent (y): ");
                res = Math.pow(base, exp);
                expr = base + " ^ " + exp + " = " + res;
                break;
            case 2:
                final double valSqrt = readOperand("Enter number (x >= 0): ");
                if (valSqrt < 0) {
                    System.out.println("Error: Square root of a negative number produces complex roots.");
                    return;
                }
                res = Math.sqrt(valSqrt);
                expr = "sqrt(" + valSqrt + ") = " + res;
                break;
            case 3:
                final double valCbrt = readOperand("Enter number: ");
                res = Math.cbrt(valCbrt);
                expr = "cbrt(" + valCbrt + ") = " + res;
                break;
            case 4:
                final double valNth = readOperand("Enter number (x >= 0): ");
                final double nRoot = readOperand("Enter root degree (n): ");
                if (nRoot == 0) {
                    System.out.println("Error: 0-th root is undefined.");
                    return;
                }
                res = Math.pow(valNth, 1.0 / nRoot);
                expr = nRoot + "-root(" + valNth + ") = " + res;
                break;
            case 5:
                final double valLog = readOperand("Enter number (x > 0): ");
                if (valLog <= 0) {
                    System.out.println("Error: Logarithm undefined for non-positive numbers.");
                    return;
                }
                res = Math.log10(valLog);
                expr = "log10(" + valLog + ") = " + res;
                break;
            case 6:
                final double valLn = readOperand("Enter number (x > 0): ");
                if (valLn <= 0) {
                    System.out.println("Error: Natural logarithm undefined for non-positive numbers.");
                    return;
                }
                res = Math.log(valLn);
                expr = "ln(" + valLn + ") = " + res;
                break;
            case 7:
                final double valExp = readOperand("Enter exponent (x): ");
                res = Math.exp(valExp);
                expr = "e^(" + valExp + ") = " + res;
                break;
            case 8:
                final double valAbs = readOperand("Enter number: ");
                res = Math.abs(valAbs);
                expr = "|" + valAbs + "| = " + res;
                break;
            default:
                System.out.println("Invalid scientific operation.");
                return;
        }

        setAnswer(res, expr);
    }

    // ==========================================
    // MODULE 3: TRIGONOMETRY & CALCULUS (Rakshitha)
    // ==========================================

    private static void handleTrigonometryAndCalculus() {
        System.out.println("\n--- TRIGONOMETRY, HYPERBOLIC & CALCULUS SUITE [Rakshitha] ---");
        System.out.println("1. Standard Trigonometric (sin, cos, tan)");
        System.out.println("2. Reciprocal Trigonometric (cosec, sec, cot)");
        System.out.println("3. Inverse Trigonometric (asin, acos, atan)");
        System.out.println("4. Hyperbolic Functions (sinh, cosh, tanh, asinh, acosh, atanh)");
        System.out.println("5. Toggle Angle Mode (Current: " + Trig.getAngleMode() + ")");
        System.out.println("6. Numerical Differentiation f'(x)");
        System.out.println("7. Numerical Definite Integral (Simpson's 1/3 Rule)");
        System.out.println("8. Back to Main Menu");
        final int op = readInt("Select option (1-8): ");

        switch (op) {
            case 1: {
                final double angle = readOperand("Enter angle: ");
                System.out.println("sin(" + angle + ") = " + Trig.sin(angle));
                System.out.println("cos(" + angle + ") = " + Trig.cos(angle));
                try {
                    System.out.println("tan(" + angle + ") = " + Trig.tan(angle));
                } catch (ArithmeticException e) {
                    System.out.println("tan(" + angle + "): " + e.getMessage());
                }
                setAnswer(Trig.sin(angle), "Trig sin(" + angle + ")");
                break;
            }
            case 2: {
                final double angle = readOperand("Enter angle: ");
                try {
                    System.out.println("cosec(" + angle + ") = " + Trig.cosec(angle));
                } catch (Exception e) {
                    System.out.println("cosec: " + e.getMessage());
                }
                try {
                    System.out.println("sec(" + angle + ") = " + Trig.sec(angle));
                } catch (Exception e) {
                    System.out.println("sec: " + e.getMessage());
                }
                try {
                    System.out.println("cot(" + angle + ") = " + Trig.cot(angle));
                } catch (Exception e) {
                    System.out.println("cot: " + e.getMessage());
                }
                break;
            }
            case 3: {
                final double val = readOperand("Enter ratio value (-1 to 1 for asin/acos): ");
                try {
                    System.out.println("asin(" + val + ") = " + Trig.asin(val));
                } catch (Exception e) {
                    System.out.println("asin: " + e.getMessage());
                }
                try {
                    System.out.println("acos(" + val + ") = " + Trig.acos(val));
                } catch (Exception e) {
                    System.out.println("acos: " + e.getMessage());
                }
                System.out.println("atan(" + val + ") = " + Trig.atan(val));
                break;
            }
            case 4: {
                final double x = readOperand("Enter x value: ");
                System.out.println("sinh(" + x + ") = " + Trig.sinh(x));
                System.out.println("cosh(" + x + ") = " + Trig.cosh(x));
                System.out.println("tanh(" + x + ") = " + Trig.tanh(x));
                System.out.println("asinh(" + x + ") = " + Trig.asinh(x));
                if (x >= 1.0) {
                    System.out.println("acosh(" + x + ") = " + Trig.acosh(x));
                }
                if (x > -1.0 && x < 1.0) {
                    System.out.println("atanh(" + x + ") = " + Trig.atanh(x));
                }
                break;
            }
            case 5: {
                if (Trig.getAngleMode() == Trig.AngleMode.DEGREES) {
                    Trig.setAngleMode(Trig.AngleMode.RADIANS);
                } else {
                    Trig.setAngleMode(Trig.AngleMode.DEGREES);
                }
                System.out.println("Angle mode switched to: " + Trig.getAngleMode());
                break;
            }
            case 6: {
                System.out.println("Numerical Differentiation f'(x):");
                System.out.println("Choose function to differentiate:");
                System.out.println("1. f(x) = x^2");
                System.out.println("2. f(x) = sin(x)");
                System.out.println("3. f(x) = e^x");
                System.out.println("4. f(x) = ln(x)");
                int fnChoice = readInt("Select function (1-4): ");
                final double pt = readOperand("Evaluate derivative at x = ");
                Trig.SingleVariableFunction f = getFunction(fnChoice);
                if (f != null) {
                    double d = Trig.derivative(f, pt);
                    setAnswer(d, "f'(" + pt + ") = " + d);
                }
                break;
            }
            case 7: {
                System.out.println("Numerical Definite Integration (Simpson's 1/3 Rule):");
                System.out.println("Choose function to integrate:");
                System.out.println("1. f(x) = x^2");
                System.out.println("2. f(x) = sin(x)");
                System.out.println("3. f(x) = e^x");
                System.out.println("4. f(x) = ln(x)");
                int fnChoice = readInt("Select function (1-4): ");
                final double lower = readOperand("Enter lower limit a: ");
                final double upper = readOperand("Enter upper limit b: ");
                Trig.SingleVariableFunction f = getFunction(fnChoice);
                if (f != null) {
                    double integral = Trig.definiteIntegral(f, lower, upper, 1000);
                    setAnswer(integral, "Integral [" + lower + " to " + upper + "] = " + integral);
                }
                break;
            }
            case 8:
                return;
            default:
                System.out.println("Invalid option.");
        }
    }

    private static Trig.SingleVariableFunction getFunction(int choice) {
        switch (choice) {
            case 1: return x -> x * x;
            case 2: return Math::sin;
            case 3: return Math::exp;
            case 4: return Math::log;
            default:
                System.out.println("Invalid function.");
                return null;
        }
    }

    // ==========================================
    // MODULE 4: MATRIX ALGEBRA (Rakshitha)
    // ==========================================

    private static void handleMatrixOperations() {
        System.out.println("\n--- MATRIX ALGEBRA & INVERSION [Rakshitha] ---");
        System.out.println("1. Matrix Addition (NxM)");
        System.out.println("2. Matrix Subtraction (NxM)");
        System.out.println("3. Matrix Multiplication (NxM * MxP)");
        System.out.println("4. Matrix Transpose (A^T)");
        System.out.println("5. Determinant of Square Matrix (NxN)");
        System.out.println("6. Matrix Inverse (A^-1)");
        System.out.println("7. Matrix Trace (Tr(A))");
        System.out.println("8. Back to Main Menu");
        final int op = readInt("Select operation (1-8): ");
        if (op == 8) return;

        switch (op) {
            case 1: {
                final int r = readInt("Enter number of rows: ");
                final int c = readInt("Enter number of cols: ");
                System.out.println("Matrix A:");
                double[][] a = readMatrix(r, c);
                System.out.println("Matrix B:");
                double[][] b = readMatrix(r, c);
                double[][] sum = Matrix.add(a, b);
                System.out.println("Result (A + B):");
                Matrix.printMatrix(sum);
                logHistory("Matrix Addition (" + r + "x" + c + ") executed");
                break;
            }
            case 2: {
                final int r = readInt("Enter number of rows: ");
                final int c = readInt("Enter number of cols: ");
                System.out.println("Matrix A:");
                double[][] a = readMatrix(r, c);
                System.out.println("Matrix B:");
                double[][] b = readMatrix(r, c);
                double[][] diff = Matrix.subtract(a, b);
                System.out.println("Result (A - B):");
                Matrix.printMatrix(diff);
                logHistory("Matrix Subtraction (" + r + "x" + c + ") executed");
                break;
            }
            case 3: {
                final int r1 = readInt("Matrix A rows: ");
                final int c1 = readInt("Matrix A cols: ");
                final int r2 = readInt("Matrix B rows (must equal A cols): ");
                final int c2 = readInt("Matrix B cols: ");
                if (c1 != r2) {
                    System.out.println("Dimension mismatch: Columns of A (" + c1 + ") must equal Rows of B (" + r2 + ").");
                    return;
                }
                System.out.println("Enter Matrix A:");
                double[][] a = readMatrix(r1, c1);
                System.out.println("Enter Matrix B:");
                double[][] b = readMatrix(r2, c2);
                double[][] prod = Matrix.multiply(a, b);
                System.out.println("Result (A * B):");
                Matrix.printMatrix(prod);
                logHistory("Matrix Multiplication (" + r1 + "x" + c2 + ") executed");
                break;
            }
            case 4: {
                final int r = readInt("Enter rows: ");
                final int c = readInt("Enter cols: ");
                double[][] m = readMatrix(r, c);
                double[][] trans = Matrix.transpose(m);
                System.out.println("Transpose (A^T):");
                Matrix.printMatrix(trans);
                logHistory("Matrix Transpose executed");
                break;
            }
            case 5: {
                final int n = readInt("Enter dimension of square matrix (N for NxN): ");
                double[][] m = readMatrix(n, n);
                double det = Matrix.determinant(m);
                setAnswer(det, "Determinant |M| = " + det);
                break;
            }
            case 6: {
                final int n = readInt("Enter dimension of square matrix (N for NxN): ");
                double[][] m = readMatrix(n, n);
                try {
                    double[][] inv = Matrix.inverse(m);
                    System.out.println("Inverse Matrix (A^-1):");
                    Matrix.printMatrix(inv);
                    logHistory("Matrix Inversion (" + n + "x" + n + ") executed");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 7: {
                final int n = readInt("Enter dimension of square matrix (N for NxN): ");
                double[][] m = readMatrix(n, n);
                double tr = Matrix.trace(m);
                setAnswer(tr, "Trace Tr(M) = " + tr);
                break;
            }
            default:
                System.out.println("Invalid matrix option.");
        }
    }

    private static double[][] readMatrix(int rows, int cols) {
        double[][] m = new double[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                m[i][j] = readDouble("Element [" + (i + 1) + "][" + (j + 1) + "]: ");
            }
        }
        return m;
    }

    // ==========================================
    // MODULE 5: EQUATION SOLVERS (Rakshitha)
    // ==========================================

    private static void handleEquationSolvers() {
        System.out.println("\n--- ALGEBRAIC EQUATION SOLVERS [Rakshitha] ---");
        System.out.println("1. Linear Equation in 1 Variable (ax + b = 0)");
        System.out.println("2. Quadratic Equation (ax^2 + bx + c = 0)");
        System.out.println("3. Cubic Equation (ax^3 + bx^2 + cx + d = 0)");
        System.out.println("4. 2-Variable Linear System (2x2 Cramer)");
        System.out.println("5. 3-Variable Linear System (3x3 Cramer)");
        System.out.println("6. Back to Main Menu");
        final int op = readInt("Select solver (1-6): ");
        if (op == 6) return;

        switch (op) {
            case 1: {
                final double a = readOperand("Enter coefficient a: ");
                final double b = readOperand("Enter constant b: ");
                try {
                    double root = Eqn.solveLinear(a, b);
                    setAnswer(root, "Linear root: x = " + root);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 2: {
                final double a = readOperand("Enter coefficient a: ");
                final double b = readOperand("Enter coefficient b: ");
                final double c = readOperand("Enter constant c: ");
                try {
                    Eqn.QuadraticResult res = Eqn.solveQuadratic(a, b, c);
                    System.out.println("Roots:\n" + res);
                    logHistory("Solved Quadratic: " + a + "x^2 + " + b + "x + " + c + " = 0");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 3: {
                final double a = readOperand("Enter coefficient a: ");
                final double b = readOperand("Enter coefficient b: ");
                final double c = readOperand("Enter coefficient c: ");
                final double d = readOperand("Enter constant d: ");
                try {
                    Eqn.CubicResult res = Eqn.solveCubic(a, b, c, d);
                    System.out.println("Cubic Equation Roots:\n" + res);
                    logHistory("Solved Cubic: " + a + "x^3 + " + b + "x^2 + " + c + "x + " + d + " = 0");
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 4: {
                System.out.println("Equation 1: a1*x + b1*y = c1");
                System.out.println("Equation 2: a2*x + b2*y = c2");
                final double a1 = readOperand("a1: "), b1 = readOperand("b1: "), c1 = readOperand("c1: ");
                final double a2 = readOperand("a2: "), b2 = readOperand("b2: "), c2 = readOperand("c2: ");
                try {
                    Eqn.System2Result res = Eqn.solveSystem2(a1, b1, c1, a2, b2, c2);
                    System.out.println("Solution: " + res);
                    logHistory("2-variable system solution: " + res);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 5: {
                System.out.println("System of 3 equations: a*x + b*y + c*z = d");
                double[][] coeff = new double[3][3];
                double[] consts = new double[3];
                for (int i = 0; i < 3; i++) {
                    System.out.println("Row " + (i + 1) + ":");
                    coeff[i][0] = readOperand("  a" + (i + 1) + ": ");
                    coeff[i][1] = readOperand("  b" + (i + 1) + ": ");
                    coeff[i][2] = readOperand("  c" + (i + 1) + ": ");
                    consts[i] = readOperand("  d" + (i + 1) + ": ");
                }
                try {
                    Eqn.System3Result res = Eqn.solveSystem3(coeff, consts);
                    System.out.println("Solution: " + res);
                    logHistory("3-variable system solution: " + res);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            default:
                System.out.println("Invalid solver choice.");
        }
    }

    // ==========================================
    // MODULE 6: STATISTICS (Rakshitha)
    // ==========================================

    private static void handleStatistics() {
        System.out.println("\n--- STATISTICS & LINEAR REGRESSION [Rakshitha] ---");
        System.out.println("1. Univariate Data Analysis (Mean, Median, Mode, Variance, StDev)");
        System.out.println("2. Bivariate Linear Regression (y = mx + c, R^2)");
        System.out.println("3. Back to Main Menu");
        final int op = readInt("Select option (1-3): ");

        if (op == 1) {
            final int n = readInt("Enter number of data points: ");
            if (n <= 0) return;
            double[] data = new double[n];
            for (int i = 0; i < n; i++) {
                data[i] = readOperand("Point [" + (i + 1) + "]: ");
            }
            System.out.println("\n--- STATISTICAL SUMMARY ---");
            System.out.printf("Mean:               %.4f%n", Statistics.mean(data));
            System.out.printf("Median:             %.4f%n", Statistics.median(data));
            System.out.printf("Mode:               %.4f%n", Statistics.mode(data));
            System.out.printf("Sample Variance:    %.4f%n", (n > 1 ? Statistics.variance(data, true) : 0.0));
            System.out.printf("Sample Std Dev:     %.4f%n", (n > 1 ? Statistics.standardDeviation(data, true) : 0.0));
            System.out.printf("Minimum:            %.4f%n", Statistics.min(data));
            System.out.printf("Maximum:            %.4f%n", Statistics.max(data));
            System.out.printf("Range:              %.4f%n", Statistics.range(data));
            setAnswer(Statistics.mean(data), "Statistics mean = " + Statistics.mean(data));
        } else if (op == 2) {
            final int n = readInt("Enter number of (x, y) coordinate pairs: ");
            if (n < 2) {
                System.out.println("Regression requires at least 2 points.");
                return;
            }
            double[] x = new double[n];
            double[] y = new double[n];
            for (int i = 0; i < n; i++) {
                x[i] = readOperand("x[" + (i + 1) + "]: ");
                y[i] = readOperand("y[" + (i + 1) + "]: ");
            }
            try {
                Statistics.RegressionResult reg = Statistics.linearRegression(x, y);
                System.out.println("\nLinear Regression Result:");
                System.out.println(reg);
                logHistory(reg.toString());
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }

    // ==========================================
    // MODULE 7: PROGRAMMER BASE CALC (Mithun)
    // ==========================================

    private static void handleProgrammerBaseCalc() {
        System.out.println("\n--- PROGRAMMER BASE CONVERTER & BITWISE [Mithun] ---");
        System.out.println("1. Convert Decimal to Binary, Octal, Hexadecimal");
        System.out.println("2. Convert Binary / Octal / Hex to Decimal");
        System.out.println("3. Bitwise AND, OR, XOR, NOT");
        System.out.println("4. Bitwise Left / Right Shift");
        System.out.println("5. Hamming Weight (Count Set Bits)");
        System.out.println("6. Back to Main Menu");
        final int op = readInt("Select option (1-6): ");
        if (op == 6) return;

        switch (op) {
            case 1: {
                System.out.print("Enter integer in decimal: ");
                long val = Long.parseLong(sc.nextLine().trim());
                System.out.println("Decimal: " + val);
                System.out.println("Binary:  " + BaseCalc.toBinary(val));
                System.out.println("Octal:   " + BaseCalc.toOctal(val));
                System.out.println("Hex:     " + BaseCalc.toHex(val));
                logHistory("Base conversion of " + val);
                break;
            }
            case 2: {
                System.out.println("Enter string with prefix (0b for binary, 0x for hex, 0o for octal):");
                String str = sc.nextLine().trim();
                try {
                    long dec;
                    if (str.startsWith("0b") || str.startsWith("0B")) dec = BaseCalc.parseBinary(str);
                    else if (str.startsWith("0x") || str.startsWith("0X")) dec = BaseCalc.parseHex(str);
                    else if (str.startsWith("0o") || str.startsWith("0O")) dec = BaseCalc.parseOctal(str);
                    else dec = Long.parseLong(str);
                    setAnswer(dec, str + " = " + dec + " (decimal)");
                } catch (Exception e) {
                    System.out.println("Invalid number format.");
                }
                break;
            }
            case 3: {
                System.out.print("Enter first integer: ");
                long a = Long.parseLong(sc.nextLine().trim());
                System.out.print("Enter second integer: ");
                long b = Long.parseLong(sc.nextLine().trim());
                System.out.println("a & b (AND) = " + BaseCalc.bitwiseAnd(a, b) + " (" + BaseCalc.toBinary(BaseCalc.bitwiseAnd(a, b)) + ")");
                System.out.println("a | b (OR)  = " + BaseCalc.bitwiseOr(a, b) + " (" + BaseCalc.toBinary(BaseCalc.bitwiseOr(a, b)) + ")");
                System.out.println("a ^ b (XOR) = " + BaseCalc.bitwiseXor(a, b) + " (" + BaseCalc.toBinary(BaseCalc.bitwiseXor(a, b)) + ")");
                System.out.println("~a (NOT)    = " + BaseCalc.bitwiseNot(a) + " (" + BaseCalc.toBinary(BaseCalc.bitwiseNot(a)) + ")");
                break;
            }
            case 4: {
                System.out.print("Enter integer: ");
                long a = Long.parseLong(sc.nextLine().trim());
                int shift = readInt("Enter bit shift amount: ");
                System.out.println("a << " + shift + " (Left)  = " + BaseCalc.shiftLeft(a, shift) + " (" + BaseCalc.toBinary(BaseCalc.shiftLeft(a, shift)) + ")");
                System.out.println("a >> " + shift + " (Right) = " + BaseCalc.shiftRight(a, shift) + " (" + BaseCalc.toBinary(BaseCalc.shiftRight(a, shift)) + ")");
                break;
            }
            case 5: {
                System.out.print("Enter integer: ");
                long a = Long.parseLong(sc.nextLine().trim());
                int count = BaseCalc.bitCount(a);
                System.out.println("Number of set bits (1s): " + count);
                break;
            }
        }
    }

    // ==========================================
    // MODULE 8: DISCRETE MATH (Mithun)
    // ==========================================

    private static void handleDiscreteMath() {
        System.out.println("\n--- DISCRETE MATH & COMBINATORICS [Mithun] ---");
        System.out.println("1. GCD & LCM of Two Integers");
        System.out.println("2. Primality Test & Prime Factorization");
        System.out.println("3. Permutations (nPr) & Combinations (nCr)");
        System.out.println("4. Fibonacci Number (nth term)");
        System.out.println("5. Back to Main Menu");
        final int op = readInt("Select option (1-5): ");
        if (op == 5) return;

        switch (op) {
            case 1: {
                long a = (long) readOperand("Enter first integer: ");
                long b = (long) readOperand("Enter second integer: ");
                long g = DiscreteMath.gcd(a, b);
                long l = DiscreteMath.lcm(a, b);
                System.out.println("GCD(" + a + ", " + b + ") = " + g);
                System.out.println("LCM(" + a + ", " + b + ") = " + l);
                logHistory("GCD(" + a + "," + b + ")=" + g + ", LCM=" + l);
                break;
            }
            case 2: {
                long n = (long) readOperand("Enter integer: ");
                System.out.println(n + " is prime? " + DiscreteMath.isPrime(n));
                List<Long> factors = DiscreteMath.primeFactors(n);
                System.out.println("Prime factors of " + n + ": " + factors);
                logHistory("Prime factorization of " + n + " = " + factors);
                break;
            }
            case 3: {
                int n = readInt("Enter n: ");
                int r = readInt("Enter r: ");
                try {
                    long npr = DiscreteMath.permutations(n, r);
                    long ncr = DiscreteMath.combinations(n, r);
                    System.out.println("Permutations P(" + n + ", " + r + ") = " + npr);
                    System.out.println("Combinations C(" + n + ", " + r + ") = " + ncr);
                    setAnswer(ncr, "C(" + n + "," + r + ") = " + ncr);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 4: {
                int n = readInt("Enter Fibonacci index n: ");
                try {
                    long fib = DiscreteMath.fibonacci(n);
                    setAnswer(fib, "Fibonacci(" + n + ") = " + fib);
                } catch (Exception e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
        }
    }

    // ==========================================
    // MODULE 9: UNIT CONVERSIONS & CONSTANTS (Mithun)
    // ==========================================

    private static void handleUnitConversions() {
        System.out.println("\n--- UNIT CONVERSIONS & PHYSICAL CONSTANTS [Mithun] ---");
        System.out.println("1. Temperature (Celsius <-> Fahrenheit <-> Kelvin)");
        System.out.println("2. Length (Meters <-> Feet, Kilometers <-> Miles, Inches <-> CM)");
        System.out.println("3. Mass / Weight (Kilograms <-> Pounds)");
        System.out.println("4. Standard Physics Constants (c, h, G, N_A, k, e)");
        System.out.println("5. Back to Main Menu");
        final int op = readInt("Select option (1-5): ");
        if (op == 5) return;

        switch (op) {
            case 1: {
                System.out.println("1. Celsius to Fahrenheit & Kelvin");
                System.out.println("2. Fahrenheit to Celsius");
                int t = readInt("Choice: ");
                if (t == 1) {
                    double c = readOperand("Enter Celsius: ");
                    System.out.println(c + " C = " + UnitConverter.celsiusToFahrenheit(c) + " F");
                    System.out.println(c + " C = " + UnitConverter.celsiusToKelvin(c) + " K");
                } else {
                    double f = readOperand("Enter Fahrenheit: ");
                    System.out.println(f + " F = " + UnitConverter.fahrenheitToCelsius(f) + " C");
                }
                break;
            }
            case 2: {
                System.out.println("1. Meters to Feet");
                System.out.println("2. Kilometers to Miles");
                System.out.println("3. Inches to Centimeters");
                int l = readInt("Choice: ");
                if (l == 1) {
                    double m = readOperand("Meters: ");
                    setAnswer(UnitConverter.metersToFeet(m), m + " m = " + UnitConverter.metersToFeet(m) + " ft");
                } else if (l == 2) {
                    double km = readOperand("Km: ");
                    setAnswer(UnitConverter.kilometersToMiles(km), km + " km = " + UnitConverter.kilometersToMiles(km) + " miles");
                } else if (l == 3) {
                    double in = readOperand("Inches: ");
                    setAnswer(UnitConverter.inchesToCentimeters(in), in + " in = " + UnitConverter.inchesToCentimeters(in) + " cm");
                }
                break;
            }
            case 3: {
                double kg = readOperand("Kilograms: ");
                setAnswer(UnitConverter.kilogramsToPounds(kg), kg + " kg = " + UnitConverter.kilogramsToPounds(kg) + " lbs");
                break;
            }
            case 4: {
                System.out.println("\n--- FUNDAMENTAL SCIENTIFIC CONSTANTS ---");
                System.out.println("Speed of Light (c):           " + UnitConverter.SPEED_OF_LIGHT + " m/s");
                System.out.println("Planck's Constant (h):        " + UnitConverter.PLANCK_CONSTANT + " J*s");
                System.out.println("Gravitational Constant (G):   " + UnitConverter.GRAVITATIONAL_CONST + " m^3/(kg*s^2)");
                System.out.println("Avogadro's Number (N_A):      " + UnitConverter.AVOGADRO_NUMBER + " mol^-1");
                System.out.println("Boltzmann Constant (k):       " + UnitConverter.BOLTZMANN_CONST + " J/K");
                System.out.println("Elementary Charge (e):        " + UnitConverter.ELECTRON_CHARGE + " C");
                break;
            }
        }
    }

    // ==========================================
    // MODULE 10: MEMORY & HISTORY (Mithun)
    // ==========================================

    private static void handleMemoryAndHistory() {
        System.out.println("\n--- MEMORY REGISTERS & CALCULATION HISTORY [Mithun] ---");
        System.out.printf("Current Memory Register (M): %.4f%n", memoryRegister);
        System.out.printf("Last Answer (Ans):           %.4f%n", lastAnswer);
        System.out.println("1. Memory Store (MS) -> Store Last Ans into M");
        System.out.println("2. Memory Add (M+)   -> Add Last Ans into M");
        System.out.println("3. Memory Sub (M-)   -> Subtract Last Ans from M");
        System.out.println("4. Memory Clear (MC) -> Reset M to 0");
        System.out.println("5. View Full Session Calculation Log");
        System.out.println("6. Clear Calculation Log");
        System.out.println("7. Back to Main Menu");
        final int op = readInt("Select option (1-7): ");

        switch (op) {
            case 1:
                memoryRegister = lastAnswer;
                System.out.println("Memory Register set to: " + memoryRegister);
                break;
            case 2:
                memoryRegister += lastAnswer;
                System.out.println("Memory Register updated to: " + memoryRegister);
                break;
            case 3:
                memoryRegister -= lastAnswer;
                System.out.println("Memory Register updated to: " + memoryRegister);
                break;
            case 4:
                memoryRegister = 0.0;
                System.out.println("Memory cleared (M = 0.0).");
                break;
            case 5:
                displayHistory();
                break;
            case 6:
                historyCount = 0;
                System.out.println("History log cleared.");
                break;
        }
    }

    // ==========================================
    // HELPERS & ACCESS CONTROL
    // ==========================================

    private static void setAnswer(final double result, final String record) {
        lastAnswer = result;
        System.out.println("Result = " + result);
        logHistory(record);
    }

    private static void logHistory(final String entry) {
        if (entry == null || entry.trim().isEmpty()) return;
        if (historyCount < MAX_HISTORY) {
            history[historyCount++] = entry;
        } else {
            System.arraycopy(history, 1, history, 0, MAX_HISTORY - 1);
            history[MAX_HISTORY - 1] = entry;
        }
    }

    private static void displayHistory() {
        System.out.println("\n--- CALCULATION HISTORY LOG ---");
        if (historyCount == 0) {
            System.out.println("No calculations recorded yet.");
            return;
        }
        for (int i = 0; i < historyCount; i++) {
            System.out.printf("[%02d] %s%n", (i + 1), history[i]);
        }
    }

    private static void displayProjectDetails() {
        System.out.println("\n===============================================================================");
        System.out.println("                           PROJECT & ACTIVITY DETAILS                          ");
        System.out.println("===============================================================================");
        System.out.println("Project Title: " + APP_TITLE);
        System.out.println("Version:       " + VERSION);
        System.out.println("\nCOLLABORATIVE ACTIVITY DIVISION:");
        System.out.println("1. Mithun Gowda M (@Mithun-gowda-M):");
        System.out.println("   - Core Package: 'calculator'");
        System.out.println("   - Interactive console engine with live status banner");
        System.out.println("   - Active Memory Registers (MS, MR, M+, M-, MC) & Last Answer (Ans)");
        System.out.println("   - Programmer Base Converter & Bitwise Calculator (BaseCalc.java)");
        System.out.println("   - Discrete Math, GCD/LCM, Prime Factorization, nPr/nCr (DiscreteMath.java)");
        System.out.println("   - Engineering Unit Converter & Physics Constants (UnitConverter.java)");
        System.out.println("\n2. Rakshitha R (@rakshitha-205):");
        System.out.println("   - Advanced Package: 'calculator.advanced'");
        System.out.println("   - Trigonometric, Hyperbolic & Numerical Calculus Suite (Trig.java)");
        System.out.println("   - Arbitrary NxN Matrix Algebra, Inversion & Determinants (Matrix.java)");
        System.out.println("   - Linear, Quadratic, Cubic & 2x2/3x3 System Solvers (Eqn.java)");
        System.out.println("   - Descriptive/Inferential Statistics & Linear Regression (Statistics.java)");
        System.out.println("\nDEMONSTRATED ACADEMIC REQUISITES:");
        System.out.println(" - Access Control: public API methods, private utility constructors, private state");
        System.out.println(" - Static & Final: final utility classes, static operations, final parameters");
        System.out.println("===============================================================================");
    }

    private static int readInt(final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
            }
        }
    }

    private static double readDouble(final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }

    private static double readOperand(final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                String line = sc.nextLine().trim();
                if (line.equalsIgnoreCase("ans")) {
                    return lastAnswer;
                }
                if (line.equalsIgnoreCase("m") || line.equalsIgnoreCase("mr")) {
                    return memoryRegister;
                }
                if (line.equalsIgnoreCase("pi")) {
                    return Math.PI;
                }
                if (line.equalsIgnoreCase("e")) {
                    return Math.E;
                }
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Enter a number or 'Ans', 'M', 'pi', 'e'.");
            }
        }
    }
}
