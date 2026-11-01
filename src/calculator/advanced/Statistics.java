package calculator.advanced;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Statistics class provides descriptive and inferential statistical calculations,
 * including central tendencies, dispersion, and simple linear regression analysis.
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor private, helper subroutines private.
 * - Static & Final: Class is final, methods are static, data inputs protected with final modifiers.
 * 
 * Contributed by: Rakshitha R (@rakshitha-205)
 */
public final class Statistics {

    private Statistics() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static double mean(final double[] data) {
        validateData(data);
        double sum = 0.0;
        for (double v : data) {
            sum += v;
        }
        return sum / data.length;
    }

    public static double median(final double[] data) {
        validateData(data);
        final double[] sorted = data.clone();
        Arrays.sort(sorted);
        final int n = sorted.length;
        if (n % 2 == 0) {
            return (sorted[n / 2 - 1] + sorted[n / 2]) / 2.0;
        } else {
            return sorted[n / 2];
        }
    }

    public static double mode(final double[] data) {
        validateData(data);
        final Map<Double, Integer> freq = new HashMap<>();
        for (double d : data) {
            freq.put(d, freq.getOrDefault(d, 0) + 1);
        }

        double maxElem = data[0];
        int maxCount = 0;
        for (Map.Entry<Double, Integer> entry : freq.entrySet()) {
            if (entry.getValue() > maxCount) {
                maxCount = entry.getValue();
                maxElem = entry.getKey();
            }
        }
        return maxElem;
    }

    public static double variance(final double[] data, final boolean isSample) {
        validateData(data);
        if (isSample && data.length < 2) {
            throw new IllegalArgumentException("Sample variance requires at least 2 data points.");
        }
        final double avg = mean(data);
        double sumSq = 0.0;
        for (double d : data) {
            final double diff = d - avg;
            sumSq += diff * diff;
        }
        return sumSq / (isSample ? (data.length - 1) : data.length);
    }

    public static double standardDeviation(final double[] data, final boolean isSample) {
        return Math.sqrt(variance(data, isSample));
    }

    public static double min(final double[] data) {
        validateData(data);
        double m = data[0];
        for (double d : data) {
            if (d < m) m = d;
        }
        return m;
    }

    public static double max(final double[] data) {
        validateData(data);
        double m = data[0];
        for (double d : data) {
            if (d > m) m = d;
        }
        return m;
    }

    public static double range(final double[] data) {
        return max(data) - min(data);
    }

    // Linear Regression Result Container
    public static final class RegressionResult {
        public final double slope;
        public final double intercept;
        public final double rSquared;

        public RegressionResult(double slope, double intercept, double rSquared) {
            this.slope = slope;
            this.intercept = intercept;
            this.rSquared = rSquared;
        }

        @Override
        public String toString() {
            return String.format("Best Fit Line: y = %.4fx + %.4f (R^2 = %.4f)", slope, intercept, rSquared);
        }
    }

    public static RegressionResult linearRegression(final double[] x, final double[] y) {
        if (x == null || y == null || x.length != y.length || x.length < 2) {
            throw new IllegalArgumentException("Regression requires equal sized arrays with at least 2 points.");
        }
        final int n = x.length;
        final double xBar = mean(x);
        final double yBar = mean(y);

        double xx = 0.0, yy = 0.0, xy = 0.0;
        for (int i = 0; i < n; i++) {
            final double xDiff = x[i] - xBar;
            final double yDiff = y[i] - yBar;
            xx += xDiff * xDiff;
            yy += yDiff * yDiff;
            xy += xDiff * yDiff;
        }

        if (xx == 0.0) {
            throw new ArithmeticException("Cannot compute regression: all x values are identical.");
        }

        final double slope = xy / xx;
        final double intercept = yBar - (slope * xBar);
        final double rSquared = (yy != 0.0) ? (xy * xy) / (xx * yy) : 1.0;

        return new RegressionResult(slope, intercept, rSquared);
    }

    private static void validateData(final double[] data) {
        if (data == null || data.length == 0) {
            throw new IllegalArgumentException("Data array cannot be null or empty.");
        }
    }
}
