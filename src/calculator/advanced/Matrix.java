package calculator.advanced;

/**
 * Matrix class provides static matrix operations including addition, 
 * subtraction, multiplication, scalar multiplication, transpose, and determinants.
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, validation methods are private.
 * - Static & Final: Class is final, methods are static, matrix dimensions validated using final parameters.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Matrix {

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Matrix() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // Matrix Addition: C = A + B
    public static double[][] add(final double[][] a, final double[][] b) {
        validateSameDimensions(a, b);
        final int rows = a.length;
        final int cols = a[0].length;
        final double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] + b[i][j];
            }
        }
        return result;
    }

    // Matrix Subtraction: C = A - B
    public static double[][] subtract(final double[][] a, final double[][] b) {
        validateSameDimensions(a, b);
        final int rows = a.length;
        final int cols = a[0].length;
        final double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] - b[i][j];
            }
        }
        return result;
    }

    // Matrix Multiplication: C = A * B
    public static double[][] multiply(final double[][] a, final double[][] b) {
        validateMatrix(a);
        validateMatrix(b);

        final int aRows = a.length;
        final int aCols = a[0].length;
        final int bRows = b.length;
        final int bCols = b[0].length;

        if (aCols != bRows) {
            throw new IllegalArgumentException("Matrix multiplication dimension mismatch: "
                    + "Columns of Matrix A (" + aCols + ") must equal Rows of Matrix B (" + bRows + ").");
        }

        final double[][] result = new double[aRows][bCols];
        for (int i = 0; i < aRows; i++) {
            for (int j = 0; j < bCols; j++) {
                double sum = 0.0;
                for (int k = 0; k < aCols; k++) {
                    sum += a[i][k] * b[k][j];
                }
                result[i][j] = sum;
            }
        }
        return result;
    }

    // Scalar Multiplication: C = scalar * A
    public static double[][] scalarMultiply(final double[][] a, final double scalar) {
        validateMatrix(a);
        final int rows = a.length;
        final int cols = a[0].length;
        final double[][] result = new double[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[i][j] = a[i][j] * scalar;
            }
        }
        return result;
    }

    // Matrix Transpose: A^T
    public static double[][] transpose(final double[][] a) {
        validateMatrix(a);
        final int rows = a.length;
        final int cols = a[0].length;
        final double[][] result = new double[cols][rows];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                result[j][i] = a[i][j];
            }
        }
        return result;
    }

    // 2x2 Determinant
    public static double determinant2x2(final double[][] a) {
        validateSquareMatrix(a, 2);
        return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);
    }

    // 3x3 Determinant
    public static double determinant3x3(final double[][] a) {
        validateSquareMatrix(a, 3);
        return a[0][0] * (a[1][1] * a[2][2] - a[1][2] * a[2][1])
             - a[0][1] * (a[1][0] * a[2][2] - a[1][2] * a[2][0])
             + a[0][2] * (a[1][0] * a[2][1] - a[1][1] * a[2][0]);
    }

    // Formatted Matrix Printing
    public static void printMatrix(final double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("[ Empty Matrix ]");
            return;
        }
        for (double[] row : matrix) {
            System.out.print("[ ");
            for (int j = 0; j < row.length; j++) {
                System.out.printf("%8.2f ", row[j]);
            }
            System.out.println("]");
        }
    }

    // Private helper methods for Access Control & Validation
    private static void validateMatrix(final double[][] m) {
        if (m == null || m.length == 0 || m[0] == null || m[0].length == 0) {
            throw new IllegalArgumentException("Matrix cannot be null or empty.");
        }
    }

    private static void validateSameDimensions(final double[][] a, final double[][] b) {
        validateMatrix(a);
        validateMatrix(b);
        if (a.length != b.length || a[0].length != b[0].length) {
            throw new IllegalArgumentException("Matrix dimensions must match. Matrix A: "
                    + a.length + "x" + a[0].length + ", Matrix B: " + b.length + "x" + b[0].length);
        }
    }

    private static void validateSquareMatrix(final double[][] a, final int expectedSize) {
        validateMatrix(a);
        if (a.length != expectedSize || a[0].length != expectedSize) {
            throw new IllegalArgumentException("Matrix must be of size " + expectedSize + "x" + expectedSize);
        }
    }
}

