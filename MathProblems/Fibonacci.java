import java.math.BigInteger;
import java.util.Scanner;

public class Fibonacci {
    
    public static void main(String[] args) {
        Scanner invoer = new Scanner(System.in);
        System.out.print("Waar moet fibo stop?");
        BigInteger stop = new BigInteger(invoer.nextLine());
        fibon(stop, BigInteger.ZERO, BigInteger.ZERO);
        //System.out.println("Golden ratio is: " + golden(stop, BigInteger.ZERO, BigInteger.ZERO));
    }

    public static BigInteger fibon(BigInteger stop, BigInteger huidige, BigInteger vorige){
        if (stop.compareTo(BigInteger.ONE) <= 0)
            return BigInteger.ZERO;
        else if (huidige.compareTo(BigInteger.TWO) <= 0)
            return fibon (stop, huidige.add(BigInteger.ONE), huidige);
        else if (huidige.compareTo(stop) <= 0)
            {
                System.out.println( "Golden ratio is: " + Double.parseDouble(huidige.toString(0)) / Double.parseDouble(vorige.toString(0)) );
            return fibon (stop, huidige.add(vorige), huidige);
            }
        return BigInteger.ZERO;

    }

}
