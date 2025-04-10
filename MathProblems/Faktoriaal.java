import java.math.BigInteger;

public class Faktoriaal {
    public static void main(String[] args) {
        System.out.println(Fakt(new BigInteger("500")));
    }

    public static BigInteger Fakt(BigInteger n) {
        if (n.equals(BigInteger.ZERO) || n.equals(BigInteger.ONE)) {
            return BigInteger.ONE;
        }
        return n.multiply(Fakt(n.subtract(BigInteger.ONE)));
    }
}
