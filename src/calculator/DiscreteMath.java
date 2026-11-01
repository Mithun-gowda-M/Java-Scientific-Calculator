package calculator;

import java.util.ArrayList;
import java.util.List;

/**
 * DiscreteMath provides number theoretic and combinatorial calculations:
 * - GCD (Greatest Common Divisor) and LCM (Least Common Multiple)
 * - Primality Testing and Prime Factorization
 * - Permutations nPr and Combinations nCr
 * - Fibonacci numbers
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, encapsulation of algorithms.
 * - Static & Final: Class is final, methods are static, inputs protected with final modifiers.
 * 
 * Contributed by: Mithun Gowda M (@Mithun-gowda-M)
 */
public final class DiscreteMath {

    private DiscreteMath() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static long gcd(long a, long b) {
        a = Math.abs(a);
        b = Math.abs(b);
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static long lcm(long a, long b) {
        if (a == 0 || b == 0) return 0;
        return Math.abs(a * (b / gcd(a, b)));
    }

    public static boolean isPrime(final long n) {
        if (n <= 1) return false;
        if (n <= 3) return true;
        if (n % 2 == 0 || n % 3 == 0) return false;
        for (long i = 5; i * i <= n; i += 6) {
            if (n % i == 0 || n % (i + 2) == 0) return false;
        }
        return true;
    }

    public static List<Long> primeFactors(long n) {
        final List<Long> factors = new ArrayList<>();
        if (n <= 1) return factors;

        while (n % 2 == 0) {
            factors.add(2L);
            n /= 2;
        }
        for (long i = 3; i * i <= n; i += 2) {
            while (n % i == 0) {
                factors.add(i);
                n /= i;
            }
        }
        if (n > 2) {
            factors.add(n);
        }
        return factors;
    }

    public static long permutations(final int n, final int r) {
        if (n < 0 || r < 0 || r > n) {
            throw new IllegalArgumentException("nPr domain error: require 0 <= r <= n.");
        }
        long result = 1;
        for (int i = 0; i < r; i++) {
            result *= (n - i);
        }
        return result;
    }

    public static long combinations(final int n, final int r) {
        if (n < 0 || r < 0 || r > n) {
            throw new IllegalArgumentException("nCr domain error: require 0 <= r <= n.");
        }
        final int k = Math.min(r, n - r);
        long result = 1;
        for (int i = 1; i <= k; i++) {
            result = result * (n - i + 1) / i;
        }
        return result;
    }

    public static long fibonacci(final int n) {
        if (n < 0) {
            throw new IllegalArgumentException("Fibonacci index must be non-negative.");
        }
        if (n == 0) return 0;
        if (n == 1) return 1;

        long prev = 0;
        long curr = 1;
        for (int i = 2; i <= n; i++) {
            long next = prev + curr;
            prev = curr;
            curr = next;
        }
        return curr;
    }
}
