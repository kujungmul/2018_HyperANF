  import java.util.*;

  /**
  * Provided graph-implementation
  */

  public class Graph {
      HashMap<Integer, HashSet<Integer>> g = new HashMap<Integer, HashSet<Integer>>();

      public void add(Integer u, Integer v) {
          g.putIfAbsent(u, new HashSet<>());
          g.putIfAbsent(v, new HashSet<>());
          g.get(u).add(v);
          g.get(v).add(u);
          //System.out.println("Added edge " + u + " to " + v);
      }

      public HashSet<Integer> neighbors(Integer u) {
          return g.get(u);
      }

      public Set<Integer> vertices() {
          return g.keySet();
      }
  }
