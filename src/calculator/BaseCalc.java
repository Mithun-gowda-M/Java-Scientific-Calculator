package calculator;

/**
 * BaseCalc provides programmer calculator features:
 * - Conversions between Decimal, Binary, Octal, and Hexadecimal
 * - Bitwise operations (AND, OR, XOR, NOT, Shifts, Hamming Weight)
 * 
 * Requisites demonstrated:
 * - Access Control: Class is public, constructor is private, encapsulation of bitwise parsing.
 * - Static & Final: Class is final, methods are static, arguments are final.
 * 
 * Contributed by: Mithun Gowda M (@Mithun-gowda-M)
 */
public final class BaseCalc {

    private BaseCalc() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated.");
    }

    public static String toBinary(final long value) {
        return "0b" + Long.toBinaryString(value);
    }

    public static String toOctal(final long value) {
        return "0o" + Long.toOctalString(value);
    }

    public static String toHex(final long value) {
        return "0x" + Long.toHexString(value).toUpperCase();
    }

    public static long parseBinary(final String binStr) {
        String clean = binStr.trim().toLowerCase();
        if (clean.startsWith("0b")) {
            clean = clean.substring(2);
        }
        return Long.parseLong(clean, 2);
    }

    public static long parseHex(final String hexStr) {
        String clean = hexStr.trim().toLowerCase();
        if (clean.startsWith("0x")) {
            clean = clean.substring(2);
        }
        return Long.parseLong(clean, 16);
    }

    public static long parseOctal(final String octStr) {
        String clean = octStr.trim().toLowerCase();
        if (clean.startsWith("0o")) {
            clean = clean.substring(2);
        }
        return Long.parseLong(clean, 8);
    }

    // Bitwise Operations
    public static long bitwiseAnd(final long a, final long b) {
        return a & b;
    }

    public static long bitwiseOr(final long a, final long b) {
        return a | b;
    }

    public static long bitwiseXor(final long a, final long b) {
        return a ^ b;
    }

    public static long bitwiseNot(final long a) {
        return ~a;
    }

    public static long shiftLeft(final long a, final int bits) {
        return a << bits;
    }

    public static long shiftRight(final long a, final int bits) {
        return a >> bits;
    }

    public static int bitCount(final long a) {
        return Long.bitCount(a);
    }
}
