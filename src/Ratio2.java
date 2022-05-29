public class Ratio2 implements Comparable<Ratio2> {
    private static Ratio2 zero = new Ratio2(0, 1);

    private int num;   // the numerator
    private int den;   // the denominator
    public Ratio2(int numerator, int denominator) {

        if (denominator == 0) {
            throw new ArithmeticException("denominator is zero");
        }

        int g = gcd(numerator, denominator);
        num = numerator   / g;
        den = denominator / g;
        if (den < 0) { den = -den; num = -num; }
    }

    public int numerator()   { return num; }
    public int denominator() { return den; }

    public double toDouble() {
        return (double) num / den;
    }

    public String toString() {
        if (den == 1) return num + "";
        else          return num + "/" + den;
    }

    public int compareTo(Ratio2 b) {
        Ratio2 a = this;
        int lhs = a.num * b.den;
        int rhs = a.den * b.num;
        if (lhs < rhs) return -1;
        if (lhs > rhs) return +1;
        return 0;
    }

    public boolean equals(Object y) {
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;
        Ratio2 b = (Ratio2) y;
        return compareTo(b) == 0;
    }

    public int hashCode() {
        return this.toString().hashCode();
    }


    public static Ratio2 mediant(Ratio2 r, Ratio2 s) {
        return new Ratio2(r.num + s.num, r.den + s.den);
    }

    private static int gcd(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        if (0 == n) return m;
        else return gcd(n, m % n);
    }

    private static int lcm(int m, int n) {
        if (m < 0) m = -m;
        if (n < 0) n = -n;
        return m * (n / gcd(m, n));
    }

    public Ratio2 times(Ratio2 b) {
        Ratio2 a = this;

        Ratio2 c = new Ratio2(a.num, b.den);
        Ratio2 d = new Ratio2(b.num, a.den);
        return new Ratio2(c.num * d.num, c.den * d.den);
    }


    public Ratio2 plus(Ratio2 b) {
        Ratio2 a = this;

        if (a.compareTo(zero) == 0) return b;
        if (b.compareTo(zero) == 0) return a;

        int f = gcd(a.num, b.num);
        int g = gcd(a.den, b.den);

        Ratio2 s = new Ratio2((a.num / f) * (b.den / g) + (b.num / f) * (a.den / g),
                lcm(a.den, b.den));

        s.num *= f;
        return s;
    }

    public Ratio2 negate() {
        return new Ratio2(-num, den);
    }

    public Ratio2 abs() {
        if (num >= 0) return this;
        else return negate();
    }

    public Ratio2 minus(Ratio2 b) {
        Ratio2 a = this;
        return a.plus(b.negate());
    }

    public Ratio2 reciprocal() { return new Ratio2(den, num);  }

    public Ratio2 divides(Ratio2 b) {
        Ratio2 a = this;
        return a.times(b.reciprocal());
    }
}
