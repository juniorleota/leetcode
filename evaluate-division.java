class Solution {
  public static int WHITE = 0;
  public static int GREY = 1;
  public static int BLACK = 2;
  
  public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
    int n = values.length;
    var nodes = new HashMap<String, Node>();
    var weights = new HashMap<String, Double>();
    
    // build graph
    for(int i = 0; i < n; i++) {
      List<String> eq = equations.get(i);
      String a = eq.get(0);
      String b = eq.get(1);
      
      nodes.putIfAbsent(a, new Node(a));
      nodes.putIfAbsent(b, new Node(b));
      
      Node nA = nodes.get(a);
      Node nB = nodes.get(b);
      
      nA.edges.add(nB);
      nB.edges.add(nA);
      
      double val = values[i];
      weights.put(a + b, val);
      weights.put(b + a, 1/val);
    }
    
    double[] result = new double[queries.size()];
    for(int i=0; i<queries.size(); i++) {
      List<String> q = queries.get(i);
      String a = q.get(0);
      String b = q.get(1);
      double res = bfs(a, b, nodes, weights);
      result[i] = res;
      //System.out.println(res);
    }
    
    return result;
  }
  
  double bfs(String start, String end, Map<String, Node> nodes, Map<String, Double> weights) {
    if (!nodes.containsKey(start) || !nodes.containsKey(end)) return -1;
    if (start.equals(end)) return 1;
    
    Node sNode = nodes.get(start);
    List<Node> initPath = List.of(sNode);
    
    Queue<List<Node>> queue = new ArrayDeque<>();
    queue.add(initPath);
    var seen = new HashSet<String>();

    while(!queue.isEmpty()) {
      List<Node> path = queue.poll();
      Node n = path.get(path.size() - 1);
      seen.add(n.id);
      
      for(Node child: n.edges) {
        if (!seen.contains(child.id)) {
          List<Node> newPath = new ArrayList<>(path);
          newPath.add(child);
          
          if (child.id.equals(end)) {
            double product = 1;
            
            for(int i = 1; i < newPath.size(); i++) {
              String a = newPath.get(i-1).id;
              String b = newPath.get(i).id;
              String key = a + b;
              if (!weights.containsKey(key)) return -1;
              
              double val = weights.get(key);
              product *= val;
            }
            return product;
          }
          
          queue.offer(newPath);
        }
      }
    }
    
    return -1;
  }
}

class Node {
  String id;
  List<Node> edges;
  
  public Node(String id) {
    this.id = id;
    this.edges = new ArrayList<>();
  }
}
