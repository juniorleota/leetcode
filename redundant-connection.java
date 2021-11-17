class Solution {
  // the main concept of this solution is to find a cycle
  // in a undirected graph which is cyclic by nature
  // there we need to make sure that the backward
  // edges are removed for each visit temporarily
  public int[] findRedundantConnection(int[][] edges) {
    int n = edges.length;
    Node[] nodes = new Node[n];
    for(int i = 0; i < n; i++) {
      nodes[i] = new Node(i+1);
    }
    
    for(int[] edge: edges) {
      Node a = nodes[edge[0]-1];
      Node b = nodes[edge[1]-1];
      
      a.edges.add(b);
      b.edges.add(a);
      if (hasCycle(a, null)) {
        return new int[] {a.id, b.id};
      }
    }
    
    return null;
  }
  // dont allow to go back
  boolean hasCycle(Node n, Node parent) {
    if (n.seen) return true;
    
    n.seen = true;
    for(Node e: n.edges) {
      boolean notParent = parent == null || e.id != parent.id;
      if (notParent && hasCycle(e, n)) return true;
    }
    
    n.seen = false;
    return false;
  }
}

class Node {
  int id;
  boolean seen;
  List<Node> edges;
  
  public Node(int id) {
    this.id = id;
    this.seen = false;
    this.edges = new ArrayList<>();
  }
}
