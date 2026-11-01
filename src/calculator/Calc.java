package calculator;

import java.util.Scanner;
import calculator.advanced.Trig;
import calculator.advanced.Matrix;
import calculator.advanced.Eqn;

/**
 * Calc is the main driver class for the Scientific Calculator application.
 * 
 * Requisites demonstrated:
 * - Access Control:
 *     * public class Calc (entry point)
 *     * private static final fields for scanner, app constants, and calculation history
 *     * private static helper methods encapsulating menu logic, input parsing, and mathematical subroutines
 * - Static & Final:
 *     * final class constants (APP_TITLE, VERSION, MAX_HISTORY)
 *     * static methods for all controller functions and mathematical operations
 *     * final parameters enforcing immutability in input processing
 * 
 * Activity Contribution Split:
 * - Mithun Gowda M: Core Calculator Architecture, Basic Arithmetic, Powers, Roots, 
 *                   Factorials, Logarithms, Calculation History, and Access Control design.
 * - Rakshitha R (@rakshitha-205): Advanced Calculator Suite (Trig, Matrix, and Eqn solvers).
 */
public class Calc {

    // Final Application Constants (Static & Final)
    public static final String APP_TITLE = "JAVA SCIENTIFIC CALCULATOR";
    public static final String VERSION = "2.0";
    private static final int MAX_HISTORY = 30;

    // Static variables for encapsulated state (Access Control)
    private static final Scanner sc = new Scanner(System.in);
    private static final String[] history = new String[MAX_HISTORY];
    private static int historyCount = 0;

    public static void main(String[] args) {
        int choice = -1;

        System.out.println("=================================================");
        System.out.println("      " + APP_TITLE + " (v" + VERSION + ")");
        System.out.println("   Developed by: Mithun Gowda M & Rakshitha R");
        System.out.println("=================================================");

        do {
            printMainMenu();
            choice = readInt("Enter your choice: ");

            switch (choice) {
                case 1:
                    handleBasicArithmetic();
                    break;
                case 2:
                    handleScientificMath();
                    break;
                case 3:
                    handleTrigonometry();
                    break;
                case 4:
                    handleMatrixOperations();
                    break;
                case 5:
                    handleEquationSolvers();
                    break;
                case 6:
                    displayHistory();
                    break;
                case 7:
                    displayProjectInfo();
                    break;
                case 8:
                    System.out.println("\nThank you for using " + APP_TITLE + ". Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice! Please select an option between 1 and 8.");
            }
        } while (choice != 8);

        sc.close();
    }

    // ==========================================
    // MENU DISPLAY (Encapsulated Access Control)
    // ==========================================

    private static void printMainMenu() {
        System.out.println("\n---------------- MAIN MENU ----------------");
        System.out.println("1. Basic Arithmetic (+, -, *, /, %)");
        System.out.println("2. Scientific Math (Power, Root, Log, Fact, Abs)");
        System.out.println("3. Trigonometry (sin, cos, tan, inv, hyp)");
        System.out.println("4. Matrix Operations (+, -, *, Transpose, Det)");
        System.out.println("5. Equation Solvers (Linear, Quadratic, 2x2 System)");
        System.out.println("6. Calculation History & Memory");
        System.out.println("7. About Project & Team Division");
        System.out.println("8. Exit");
        System.out.println("-------------------------------------------");
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
        final int subChoice = readInt("Select operation (1-5): ");

        if (subChoice < 1 || subChoice > 5) {
            System.out.println("Invalid arithmetic choice.");
            return;
        }

        final double a = readDouble("Enter first number: ");
        final double b = readDouble("Enter second number: ");
        double result = 0.0;
        String operationStr = "";

        switch (subChoice) {
            case 1:
                result = a + b;
                operationStr = a + " + " + b + " = " + result;
                break;
            case 2:
                result = a - b;
                operationStr = a + " - " + b + " = " + result;
                break;
            case 3:
                result = a * b;
                operationStr = a + " * " + b + " = " + result;
                break;
            case 4:
                if (b == 0.0) {
                    System.out.println("Error: Cannot divide by zero.");
                    return;
                }
                result = a / b;
                operationStr = a + " / " + b + " = " + result;
                break;
            case 5:
                if (b == 0.0) {
                    System.out.println("Error: Modulo by zero is undefined.");
                    return;
                }
                result = a % b;
                operationStr = a + " % " + b + " = " + result;
                break;
        }

        System.out.println("Result = " + result);
        logHistory(operationStr);
    }

    // ==========================================
    // MODULE 2: SCIENTIFIC MATH (Mithun)
    // ==========================================

    private static void handleScientificMath() {
        System.out.println("\n--- SCIENTIFIC MATH EXTENSIONS ---");
        System.out.println("1. Power (x^y)");
        System.out.println("2. Square Root (sqrt(x))");
        System.out.println("3. Cube Root (cbrt(x))");
        System.out.println("4. Factorial (n!)");
        System.out.println("5. Common Logarithm (log10 x)");
        System.out.println("6. Natural Logarithm (ln x)");
        System.out.println("7. Absolute Value (|x|)");
        final int subChoice = readInt("Select operation (1-7): ");

        double result = 0.0;
        String logRecord = "";

        switch (subChoice) {
            case 1:
                final double base = readDouble("Enter base (x): ");
                final double exp = readDouble("Enter exponent (y): ");
                result = Math.pow(base, exp);
                logRecord = base + " ^ " + exp + " = " + result;
                break;

            case 2:
                final double valSqrt = readDouble("Enter number (x >= 0): ");
                if (valSqrt < 0) {
                    System.out.println("Error: Square root of a negative number is imaginary.");
                    return;
                }
                result = Math.sqrt(valSqrt);
                logRecord = "sqrt(" + valSqrt + ") = " + result;
                break;

            case 3:
                final double valCbrt = readDouble("Enter number: ");
                result = Math.cbrt(valCbrt);
                logRecord = "cbrt(" + valCbrt + ") = " + result;
                break;

            case 4:
                final int n = readInt("Enter non-negative integer (n): ");
                if (n < 0) {
                    System.out.println("Error: Factorial is not defined for negative numbers.");
                    return;
                }
                if (n > 20) {
                    System.out.println("Note: Values above 20! exceed standard 64-bit integer range.");
                }
                long fact = 1;
                for (int i = 2; i <= n; i++) {
                    fact *= i;
                }
                System.out.println(n + "! = " + fact);
                logHistory(n + "! = " + fact);
                return;

            case 5:
                final double valLog = readDouble("Enter number (x > 0): ");
                if (valLog <= 0) {
                    System.out.println("Error: Logarithm undefined for non-positive numbers.");
                    return;
                }
                result = Math.log10(valLog);
                logRecord = "log10(" + valLog + ") = " + result;
                break;

            case 6:
                final double valLn = readDouble("Enter number (x > 0): ");
                if (valLn <= 0) {
                    System.out.println("Error: Natural log undefined for non-positive numbers.");
                    return;
                }
                result = Math.log(valLn);
                logRecord = "ln(" + valLn + ") = " + result;
                break;

            case 7:
                final double valAbs = readDouble("Enter number: ");
                result = Math.abs(valAbs);
                logRecord = "|" + valAbs + "| = " + result;
                break;

            default:
                System.out.println("Invalid choice.");
                return;
        }

        System.out.println("Result = " + result);
        logHistory(logRecord);
    }

    // ==========================================
    // MODULE 3: TRIGONOMETRY (Rakshitha)
    // ==========================================

    private static void handleTrigonometry() {
        System.out.println("\n--- TRIGONOMETRY SUITE ---");
        System.out.println("1. Standard Trig Overview (sin, cos, tan for an angle)");
        System.out.println("2. Reciprocal Trig (cosec, sec, cot)");
        System.out.println("3. Inverse Trig (asin, acos, atan)");
        System.out.println("4. Hyperbolic Functions (sinh, cosh, tanh)");
        final int subChoice = readInt("Select option (1-4): ");

        switch (subChoice) {
            case 1:
                final double angle = readDouble("Enter angle in degrees: ");
                System.out.println("sin(" + angle + " deg) = " + Trig.sin(angle));
                System.out.println("cos(" + angle + " deg) = " + Trig.cos(angle));
                try {
                    System.out.println("tan(" + angle + " deg) = " + Trig.tan(angle));
                } catch (ArithmeticException e) {
                    System.out.println("tan(" + angle + " deg) = " + e.getMessage());
                }
                logHistory("Trig overview for " + angle + " deg");
                break;

            case 2:
                final double recAngle = readDouble("Enter angle in degrees: ");
                try {
                    System.out.println("cosec(" + recAngle + " deg) = " + Trig.cosec(recAngle));
                } catch (ArithmeticException e) {
                    System.out.println("cosec: " + e.getMessage());
                }
                try {
                    System.out.println("sec(" + recAngle + " deg) = " + Trig.sec(recAngle));
                } catch (ArithmeticException e) {
                    System.out.println("sec: " + e.getMessage());
                }
                try {
                    System.out.println("cot(" + recAngle + " deg) = " + Trig.cot(recAngle));
                } catch (ArithmeticException e) {
                    System.out.println("cot: " + e.getMessage());
                }
                logHistory("Reciprocal trig for " + recAngle + " deg");
                break;

            case 3:
                System.out.println("Inverse Trigonometric (output in degrees):");
                final double val = readDouble("Enter ratio value (-1 to 1 for asin/acos): ");
                try {
                    System.out.println("asin(" + val + ") = " + Trig.asin(val) + " deg");
                } catch (IllegalArgumentException e) {
                    System.out.println("asin: " + e.getMessage());
                }
                try {
                    System.out.println("acos(" + val + ") = " + Trig.acos(val) + " deg");
                } catch (IllegalArgumentException e) {
                    System.out.println("acos: " + e.getMessage());
                }
                System.out.println("atan(" + val + ") = " + Trig.atan(val) + " deg");
                logHistory("Inverse trig for value " + val);
                break;

            case 4:
                final double hVal = readDouble("Enter x value: ");
                System.out.println("sinh(" + hVal + ") = " + Trig.sinh(hVal));
                System.out.println("cosh(" + hVal + ") = " + Trig.cosh(hVal));
                System.out.println("tanh(" + hVal + ") = " + Trig.tanh(hVal));
                logHistory("Hyperbolic trig for " + hVal);
                break;

            default:
                System.out.println("Invalid trigonometry choice.");
        }
    }

    // ==========================================
    // MODULE 4: MATRIX OPERATIONS (Rakshitha)
    // ==========================================

    private static void handleMatrixOperations() {
        System.out.println("\n--- MATRIX OPERATIONS ---");
        System.out.println("1. Matrix Addition (2x2)");
        System.out.println("2. Matrix Subtraction (2x2)");
        System.out.println("3. Matrix Multiplication (2x2)");
        System.out.println("4. Matrix Transpose (2x2)");
        System.out.println("5. Matrix Determinant (2x2 and 3x3)");
        final int subChoice = readInt("Select operation (1-5): ");

        switch (subChoice) {
            case 1: {
                double[][] m1 = new double[2][2];
                double[][] m2 = new double[2][2];
                System.out.println("Enter first 2x2 matrix (4 numbers):");
                readMatrix2x2(m1);
                System.out.println("Enter second 2x2 matrix (4 numbers):");
                readMatrix2x2(m2);

                double[][] sum = Matrix.add(m1, m2);
                System.out.println("Matrix Addition Result:");
                Matrix.printMatrix(sum);
                logHistory("2x2 Matrix Addition executed");
                break;
            }
            case 2: {
                double[][] m1 = new double[2][2];
                double[][] m2 = new double[2][2];
                System.out.println("Enter first 2x2 matrix (4 numbers):");
                readMatrix2x2(m1);
                System.out.println("Enter second 2x2 matrix (4 numbers):");
                readMatrix2x2(m2);

                double[][] diff = Matrix.subtract(m1, m2);
                System.out.println("Matrix Subtraction Result:");
                Matrix.printMatrix(diff);
                logHistory("2x2 Matrix Subtraction executed");
                break;
            }
            case 3: {
                double[][] m1 = new double[2][2];
                double[][] m2 = new double[2][2];
                System.out.println("Enter first 2x2 matrix (4 numbers):");
                readMatrix2x2(m1);
                System.out.println("Enter second 2x2 matrix (4 numbers):");
                readMatrix2x2(m2);

                double[][] prod = Matrix.multiply(m1, m2);
                System.out.println("Matrix Multiplication Result:");
                Matrix.printMatrix(prod);
                logHistory("2x2 Matrix Multiplication executed");
                break;
            }
            case 4: {
                double[][] m = new double[2][2];
                System.out.println("Enter 2x2 matrix to transpose (4 numbers):");
                readMatrix2x2(m);

                double[][] trans = Matrix.transpose(m);
                System.out.println("Transpose Result:");
                Matrix.printMatrix(trans);
                logHistory("2x2 Matrix Transpose executed");
                break;
            }
            case 5: {
                final int dim = readInt("Enter matrix dimension (2 for 2x2, 3 for 3x3): ");
                if (dim == 2) {
                    double[][] m = new double[2][2];
                    System.out.println("Enter 2x2 matrix (4 numbers):");
                    readMatrix2x2(m);
                    double det = Matrix.determinant2x2(m);
                    System.out.println("Determinant |M| = " + det);
                    logHistory("2x2 Determinant |M| = " + det);
                } else if (dim == 3) {
                    double[][] m = new double[3][3];
                    System.out.println("Enter 3x3 matrix (9 numbers row by row):");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            m[i][j] = readDouble("Element [" + i + "][" + j + "]: ");
                        }
                    }
                    double det = Matrix.determinant3x3(m);
                    System.out.println("Determinant |M| = " + det);
                    logHistory("3x3 Determinant |M| = " + det);
                } else {
                    System.out.println("Unsupported dimension for determinant.");
                }
                break;
            }
            default:
                System.out.println("Invalid matrix operation choice.");
        }
    }

    private static void readMatrix2x2(double[][] matrix) {
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                matrix[i][j] = readDouble("Element [" + i + "][" + j + "]: ");
            }
        }
    }

    // ==========================================
    // MODULE 5: EQUATION SOLVERS (Rakshitha)
    // ==========================================

    private static void handleEquationSolvers() {
        System.out.println("\n--- EQUATION SOLVERS ---");
        System.out.println("1. Linear Equation in 1 Variable (ax + b = 0)");
        System.out.println("2. Quadratic Equation (ax^2 + bx + c = 0)");
        System.out.println("3. System of 2 Linear Equations (Cramer's Rule)");
        final int subChoice = readInt("Select solver (1-3): ");

        switch (subChoice) {
            case 1: {
                System.out.println("Solving ax + b = 0:");
                final double a = readDouble("Enter coefficient a: ");
                final double b = readDouble("Enter constant b: ");
                try {
                    final double root = Eqn.solve(a, b);
                    System.out.println("Solution: x = " + root);
                    logHistory(a + "x + " + b + " = 0  =>  x = " + root);
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 2: {
                System.out.println("Solving ax^2 + bx + c = 0:");
                final double a = readDouble("Enter coefficient a: ");
                final double b = readDouble("Enter coefficient b: ");
                final double c = readDouble("Enter constant c: ");
                try {
                    Eqn.QuadraticResult result = Eqn.solveQuadratic(a, b, c);
                    System.out.println("Quadratic Equation Roots:");
                    System.out.println(result);
                    logHistory("Solved " + a + "x^2 + " + b + "x + " + c + " = 0");
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            case 3: {
                System.out.println("Solving 2-Variable System:");
                System.out.println("  Equation 1: a1*x + b1*y = c1");
                System.out.println("  Equation 2: a2*x + b2*y = c2");
                final double a1 = readDouble("Enter a1: ");
                final double b1 = readDouble("Enter b1: ");
                final double c1 = readDouble("Enter c1: ");
                final double a2 = readDouble("Enter a2: ");
                final double b2 = readDouble("Enter b2: ");
                final double c2 = readDouble("Enter c2: ");

                try {
                    Eqn.System2Result sysRes = Eqn.solveSystem2(a1, b1, c1, a2, b2, c2);
                    System.out.println("Unique Solution: " + sysRes);
                    logHistory("System solution: " + sysRes);
                } catch (ArithmeticException e) {
                    System.out.println("Error: " + e.getMessage());
                }
                break;
            }
            default:
                System.out.println("Invalid equation solver choice.");
        }
    }

    // ==========================================
    // MODULE 6: CALCULATION HISTORY (Mithun)
    // ==========================================

    private static void logHistory(final String entry) {
        if (entry == null || entry.trim().isEmpty()) {
            return;
        }
        if (historyCount < MAX_HISTORY) {
            history[historyCount++] = entry;
        } else {
            // Shift history buffer when capacity reached
            for (int i = 1; i < MAX_HISTORY; i++) {
                history[i - 1] = history[i];
            }
            history[MAX_HISTORY - 1] = entry;
        }
    }

    private static void displayHistory() {
        System.out.println("\n--- CALCULATION HISTORY ---");
        if (historyCount == 0) {
            System.out.println("No calculations recorded yet.");
            return;
        }
        for (int i = 0; i < historyCount; i++) {
            System.out.printf("[%02d] %s%n", (i + 1), history[i]);
        }
        System.out.println("1. Clear History");
        System.out.println("2. Return to Main Menu");
        int opt = readInt("Select option: ");
        if (opt == 1) {
            historyCount = 0;
            System.out.println("Calculation history cleared.");
        }
    }

    // ==========================================
    // MODULE 7: ABOUT & ACTIVITY DIVISION
    // ==========================================

    private static void displayProjectInfo() {
        System.out.println("\n=================================================");
        System.out.println("          PROJECT & ACTIVITY DETAILS             ");
        System.out.println("=================================================");
        System.out.println("Project: " + APP_TITLE);
        System.out.println("Version: " + VERSION);
        System.out.println("\nActivity Division Breakdown:");
        System.out.println("1. Mithun Gowda M:");
        System.out.println("   - Core Package: 'calculator'");
        System.out.println("   - Class: Calc.java (Driver & UI)");
        System.out.println("   - Basic Arithmetic (+, -, *, /, %)");
        System.out.println("   - Scientific Extensions (Powers, Roots, Factorials, Logs)");
        System.out.println("   - History Buffer & Encapsulated Access Control");
        System.out.println("\n2. Rakshitha R (@rakshitha-205):");
        System.out.println("   - Advanced Package: 'calculator.advanced'");
        System.out.println("   - Trig.java (Trig, Reciprocal, Inverse, Hyperbolic, PI, E)");
        System.out.println("   - Matrix.java (Add, Sub, Mul, Transpose, Determinants)");
        System.out.println("   - Eqn.java (Linear, Quadratic with complex roots, Cramer's System)");
        System.out.println("\nDemonstrated Requisites:");
        System.out.println(" - Access Control: public, private constructors/helpers, package scoping");
        System.out.println(" - Static & Final: final utility classes, static mathematical methods, final constants");
        System.out.println("=================================================");
    }

    // ==========================================
    // INPUT HELPERS (Robust Error Handling)
    // ==========================================

    private static int readInt(final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                final String line = sc.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter an integer.");
            }
        }
    }

    private static double readDouble(final String prompt) {
        while (true) {
            System.out.print(prompt);
            try {
                final String line = sc.nextLine();
                return Double.parseDouble(line.trim());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a valid decimal number.");
            }
        }
    }
}
