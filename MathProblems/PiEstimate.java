import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;

public class PiEstimate {
    public static void main(String[] args) {
        System.out.println("pi is eqaul to: ");
        System.out.println(pieee(BigInteger.ZERO, new BigInteger("1000"), BigDecimal.ZERO, false).toString());
    }

    public static BigDecimal pieee(BigInteger currentIter, BigInteger totalIterations, BigDecimal currentVal, boolean currentSign) {

        if (totalIterations.compareTo(BigInteger.ONE) <= 0) {
            return currentVal.multiply(new BigDecimal(4));
        }
        if (currentIter.compareTo(BigInteger.ONE) == 0) {
            return pieee(currentIter.add(BigInteger.ONE), totalIterations.subtract(BigInteger.ONE), currentVal.add(BigDecimal.ONE), currentSign);
        }
        if (currentIter.mod(BigInteger.TWO).compareTo(BigInteger.ZERO) == 0) {
            return pieee(currentIter.add(BigInteger.ONE), totalIterations.subtract(BigInteger.ONE), currentVal, currentSign);
        } else if(currentSign) {
            return pieee(currentIter.add(BigInteger.ONE), totalIterations.subtract(BigInteger.ONE), currentVal.add(BigDecimal.ONE.divide(new BigDecimal(currentIter), 1000, RoundingMode.HALF_UP)), !currentSign);
        } else {
            return pieee(currentIter.add(BigInteger.ONE), totalIterations.subtract(BigInteger.ONE), currentVal.subtract(BigDecimal.ONE.divide(new BigDecimal(currentIter), 1000, RoundingMode.HALF_UP)), !currentSign);
        }
    }
}
