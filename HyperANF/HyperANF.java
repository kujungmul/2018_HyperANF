import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
* Author: Sabina Hult
* Implementation of HyperANF from pseudo-code
*/

public class HyperANF {
  public static int calculateMedianDistance(Graph g) {
    long n = g.vertices().size();
    // if I have to optimaze space usage, I'll try with arrays
    // but will have to do something about the input integers then
    Map<Integer, HLL> counters = new HashMap<>();

    // for every v in V, initialize counter, add v + counter to counters
    for(Integer v : g.vertices()) {
      HLL cv = new HLL();
      cv.add(v);
      counters.put(v, cv);
    }

    int distance = 0;
    long reach = 0;
    long threshold = (n * n) / 2;

    while(reach < threshold) {
      reach = 0;
      // collection for new counters
      Map<Integer, HLL> newCounters = new HashMap<>();

      // create a new counter for each vertex, add v's current counter
      for(Integer v : g.vertices()) {
        HLL mv = new HLL();
        mv.merge(counters.get(v));

        // for each neighbor of v, merge neighbours counter into v's counter
        for(Integer w : g.neighbors(v)) mv.merge(counters.get(w));

        // estimate size of new set (how many nodes can be reached), update reach
        reach = reach + mv.estimateSize();
        newCounters.put(v, mv);
      }

      // replace old counters with new counters, increment distance
      counters = newCounters;
      distance++;
    }

    return distance;
  }

  public static void main(String[] args) {
    Graph g = new Graph();
    Scanner sc = new Scanner(System.in);

    while(sc.hasNext()) {
      String[] line = sc.nextLine().split(" ");
      g.add(Integer.parseInt(line[0]), Integer.parseInt(line[1]));
    }

    int medianDist = calculateMedianDistance(g);
    System.out.println(medianDist);
  }
}
