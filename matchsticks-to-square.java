class Solution {
  
  public boolean makesquare(int[] matchsticks) {
    int n = matchsticks.length;
    int sum = 0;
    for(int len: matchsticks) sum += len;
    if (sum%4 != 0) return false;
    int targetSum = sum/4;
    return makesquare(matchsticks, 0, targetSum, targetSum, targetSum, targetSum);
  }
  
  boolean makesquare(int[] matchsticks, int i, int u, int d, int l, int r) {
    int n = matchsticks.length;
    if (u<0 || d<0 || l<0 || r<0) return false;
    if (i==n) {
      return u == d && d == l && l == r;
    }
    
    int len = matchsticks[i];
    boolean up = makesquare(matchsticks,i+1,u-len,d,l,r);
    if (up) return true;
    
    boolean down = makesquare(matchsticks,i+1,u,d-len,l,r);
    if (down) return true;
    
    boolean left = makesquare(matchsticks,i+1,u,d,l-len,r);
    if (left) return true;

    boolean right = makesquare(matchsticks,i+1,u,d,l,r-len);
    if (right) return true;
    
    return false; 
  }
}
