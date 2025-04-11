import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import SortMethods.SortingMethodTimer;

import static SortMethods.SortingMethodTimer.*;
import static SortMethods.SortingMethod.*;

public class TestSortingAlgorithms {
    public static void main(String[] args) {
        //int[] lysie = new int[10_000_000];
        ArrayList<Integer> lys = new ArrayList<>();
        long beginTime = System.currentTimeMillis();
        // Assign array
        try {
            FileReader fr = new FileReader("random_number2.txt");
            BufferedReader br = new BufferedReader(fr);
            String line = br.readLine();
            String[] numbersString = line.split(" ");
            for (String string : numbersString) {
                lys.add(Integer.parseInt(string));
            }
            // for (int i = 0; i < lysie.length; i++) {
                //     lysie[i] = Integer.parseInt(line);
                //     line = br.readLine();
                //     if (line == null) {
                    //         br.close();
                    //         break;
                    //     }
                    // }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        int[] lysie = lys.stream().mapToInt(Integer::intValue).toArray();
        long endTime = System.currentTimeMillis();

        System.out.println((endTime - beginTime ) + "ms\n");
        //clone array to avoid referencing already sorted array
        int[] test1 = lysie.clone(), test2 = lysie.clone(), test3 = lysie.clone(), 
              test4 = lysie.clone(), test5 = lysie.clone(), test6 = lysie.clone();


        //timeMethod(BUBBLE, test1, true);
        //System.out.println();
        //timeMethod(SELECTION, test2, true);
        //System.out.println();
        //timeMethod(INSERTION, test3, false);
        //System.out.println();
        timeMethod(SHELL, test4, true);
        System.out.println();
        timeMethod(MERGE, test5);
        System.out.println();
        timeMethod(QUICK, test6);

    }
}


