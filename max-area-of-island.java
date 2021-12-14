class Solution {
  public int maxAreaOfIsland(int[][] grid) {
    int n = grid.length;
    int m = grid[0].length;
    UnionFind uf = new UnionFind(grid);
    
    for(int i = 0; i < n; i++) {
      for(int j = 0; j < m; j++) {
        int curr = grid[i][j];
        if (curr == 1) {
          checkUp(uf, i, j);
          checkLeft(uf, i, j);
        }
      }
    }
    return uf.max;
  }
  
  void checkUp(UnionFind uf, int i, int j) {
    if (i == 0) return;
    Coord curr = uf.coords[i][j];
    Coord up = uf.coords[i-1][j];
    
    if (up != null) {
      uf.union(curr, up);
    }
  }
  
  void checkLeft(UnionFind uf, int i, int j) {
    if (j == 0) return;
    Coord curr = uf.coords[i][j];
    Coord left = uf.coords[i][j-1];

    if (left != null) {
      uf.union(curr, left);
    }
  }

  class UnionFind {
    Coord[][] coords;
    int max;
    
    public UnionFind(int[][] mat) {
      int n = mat.length;
      int m = mat[0].length;
      coords = new Coord[n][m];
      
      for(int i = 0; i < n; i++) {
        for(int j = 0; j < m; j++) {
          int curr = mat[i][j];
          if (curr == 1) {
            max = 1;
            coords[i][j] = new Coord(i, j);
          }
        }
      }
    }
    
    void union(Coord a, Coord b) {
      Coord rootA = root(a);
      Coord rootB = root(b);
      
      if (!rootA.equals(rootB)) {
        // do union;
        int size = rootB.size;
        rootB.i = rootA.i;
        rootB.j = rootA.j;
        rootA.size += rootB.size;
        rootB.size = 0;
        max = Math.max(max, rootA.size);
      }
    }
    
    Coord root(Coord curr) {
      int i = curr.i;
      int j = curr.j;
      curr = coords[i][j];
      while(curr.i != i || curr.j != j) {
        i = curr.i;
        j = curr.j;
        curr = coords[i][j];
      }
      
      return curr;
    }
  }
  
  class Coord {
    int i,j,size;
    
    public Coord(int i, int j) {
      this.i = i;
      this.j = j;
      this.size = 1;
    }
    
    @Override
    public String toString() {
      return i + "," + j +":"+ size;
    }
    
    @Override
    public boolean equals(Object other) {
      Coord o = (Coord) other;
      
      return o.i == i && o.j == j;
    }
  }
}
