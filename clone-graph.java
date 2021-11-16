/*
// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;
    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }
    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}
*/

class Solution {
  public Node cloneGraph(Node node) {
    if (node == null) return node;
    var map = new HashMap<Integer, Node>();
    return dfs(node, map);
  }
  
  
  Node dfs(Node node, Map<Integer, Node> cloned) {
    int val = node.val;
    cloned.putIfAbsent(val, new Node(val));
    
    Node clone = cloned.get(val);
    
    for(Node n: node.neighbors) {
      // this will avoid infinte loop by copying the 
      // same nodes children over and over
      if (cloned.containsKey(n.val)) {
        clone.neighbors.add(cloned.get(n.val));
      } else {
        clone.neighbors.add(dfs(n, cloned));
      }
    }
    return clone;
  }
}
