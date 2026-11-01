package calculator.advanced;

/**
 * Matrix class provides comprehensive matrix algebra operations supporting
 * arbitrary NxM and square NxN dimensions, including addition, subtraction,
 * multiplication, transpose, trace, recursive determinants, and matrix inversion.
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, internal subroutines private.
 * - Static & Final: Class is final, methods are static, inputs protected with final modifiers.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Matrix {

    private static final double EPSILON = 1e-12;

    // Private constructor: Enforces non-instantiability utility pattern (Access Control)
    private Matrix() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    // Identity Matrix Generator
    public static double[][] identity(final int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Matrix size must be greater than zero.");
        }
        final double[][] id = new double[size][size];
        for (int i = 0; i < size; i++) {
            id[i][i] = 1.0;
        }
        return id;
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
            throw new IllegalArgumentException("Dimension mismatch: Columns of Matrix A (" 
                    + aCols + ") must equal Rows of Matrix B (" + bRows + ").");
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

    // Matrix Trace: Sum of diagonal elements
    public static double trace(final double[][] a) {
        validateSquareMatrix(a);
        double sum = 0.0;
        for (int i = 0; i < a.length; i++) {
            sum += a[i][i];
        }
        return sum;
    }

    // Determinant for arbitrary NxN square matrix using recursive Laplace expansion
    public static double determinant(final double[][] a) {
        validateSquareMatrix(a);
        final int n = a.length;

        if (n == 1) {
            return a[0][0];
        }
        if (n == 2) {
            return (a[0][0] * a[1][1]) - (a[0][1] * a[1][0]);
        }

        double det = 0.0;
        for (int col = 0; col < n; col++) {
            final double sign = (col % 2 == 0) ? 1.0 : -1.0;
            final double[][] minor = getSubmatrix(a, 0, col);
            det += sign * a[0][col] * determinant(minor);
        }
        return det;
    }

    // Matrix Inversion: A^(-1) = (1 / det(A)) * adj(A)
    public static double[][] inverse(final double[][] a) {
        validateSquareMatrix(a);
        final double det = determinant(a);
        if (Math.abs(det) < EPSILON) {
            throw new ArithmeticException("Matrix is singular (determinant is zero) and cannot be inverted.");
        }

        final int n = a.length;
        if (n == 1) {
            return new double[][]{{1.0 / a[0][0]}};
        }

        final double[][] cofactors = new double[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                final double sign = ((i + j) % 2 == 0) ? 1.0 : -1.0;
                cofactors[i][j] = sign * determinant(getSubmatrix(a, i, j));
            }
        }

        // Adjugate is the transpose of the cofactor matrix
        final double[][] adjugate = transpose(cofactors);
        return scalarMultiply(adjugate, 1.0 / det);
    }

    // Pretty Printer
    public static void printMatrix(final double[][] matrix) {
        if (matrix == null || matrix.length == 0) {
            System.out.println("[ Empty Matrix ]");
            return;
        }
        for (double[] row : matrix) {
            System.out.print("[ ");
            for (int j = 0; j < row.length; j++) {
                System.out.printf("%8.3f ", row[j]);
            }
            System.out.println("]");
        }
    }

    // Helper: extracts submatrix excluding specified row and col
    private static double[][] getSubmatrix(final double[][] matrix, final int excludeRow, final int excludeCol) {
        final int n = matrix.length;
        final double[][] sub = new double[n - 1][n - 1];
        int r = 0;

        for (int i = 0; i < n; i++) {
            if (i == excludeRow) continue;
            int c = 0;
            for (int j = 0; j < n; j++) {
                if (j == excludeCol) continue;
                sub[r][c] = matrix[i][j];
                c++;
            }
            r++;
        }
        return sub;
    }

    // Validation Subroutines
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

    private static void validateSquareMatrix(final double[][] a) {
        validateMatrix(a);
        if (a.length != a[0].length) {
            throw new IllegalArgumentException("Matrix must be square. Received: " + a.length + "x" + a[0].length);
        }
    }
}
