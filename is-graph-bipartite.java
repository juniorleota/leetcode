class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        Node[] nodes = new Node[n];
        for(int i = 0; i < n; i++) nodes[i] = new Node(i);

        for(int i=0; i<n; i++) {
            for(int j = 0; j<graph[i].length; j++){
                Node edge = nodes[graph[i][j]];
                nodes[i].edges.add(edge);
            }
        }

        for(Node node: nodes) {
            if (node.clr == Color.WHITE) {
                if (!isBipartite(node, Color.RED, null)) return false;
            }
        }

        return true;
    }

    boolean isBipartite(Node curr, Color clr, Node prev) {
        if (curr.clr != Color.WHITE) {
            // ensures that the current node has different colors from opposite
            // to ensure that graph is bipartite
            boolean oppositePrevColors = prev != null && prev.clr != curr.clr;
            return oppositePrevColors;
        }

        curr.clr = clr;
        for(Node edge: curr.edges) {
            boolean notPrev = prev == null || prev.id != edge.id;
            Color expected = clr == Color.RED ? Color.BLACK : Color.RED;
            if(notPrev && !isBipartite(edge, expected, curr)) return false;
        }

        return true;
    }
}

class Node {
    int id;
    List<Node> edges;
    Color clr;

    public Node(int id) {
        this.id = id;
        this.edges = new ArrayList<>();
        this.clr = Color.WHITE;
    }
}

enum Color {WHITE,RED,BLACK}
