import java.io.*;
import java.util.*;

/**
* Author: Sabina Hult
*/

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // don't know if this map is a good idea for the input...
        Map<Integer, List<Integer>> cols = new HashMap<>();

        int c = 0;
        while (sc.hasNextLine()) {
            String line = sc.nextLine();
            if(line.isEmpty()) break;

            String[] vals = line.split("\\s+");

            List<Integer> ints = new ArrayList<>();
            for(String s : vals) {
                if(!s.isEmpty())
                    ints.add(Integer.parseInt(s));
            }

            cols.put(c, ints);
            c++;
        }

        sc.close();


        // first HLL counter
        HLL first = new HLL();
        List<Integer> fNums = cols.get(0);
        for (Integer i : fNums) first.add(i);
        double firE = first.estimateSize();

        // second HLL counter
        HLL second = new HLL();
        List<Integer> sNums = cols.get(1);
        for (Integer j : sNums) second.add(j);

        // maybe merge should return a new HLL object, but let's see if this even works first...
        first.merge(second);
        double mergedE = first.estimateSize();


        // threshold
        double t = firE * 1.5;

        if(mergedE > t) System.out.println("almost disjoint");
        else System.out.println("almost same");

    }
}
