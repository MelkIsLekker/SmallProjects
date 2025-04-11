import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class RNG {
 public static void main(String[] args) {

    Random generator = new Random();
    int i = 0;
    try {
        FileWriter fw = new FileWriter("random_number2.txt");

        while (i < 10_000_000) {
            fw.write(generator.nextInt(1_000_000) +" ");
            i++;
        }
    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
 }   
}
