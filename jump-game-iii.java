class Solution {
  public boolean canReach(int[] arr, int start) {
    boolean[] seen = new boolean[arr.length];
    return canReach(arr, start, new HashMap<>(), seen);
  }
  
  public boolean canReach(int[] arr, int start, Map<Integer, Boolean> mem, boolean[] seen) {
    int n = arr.length;
    boolean outOfBounds = start < 0 || start >= n;
    if (outOfBounds) return false;
   
    boolean hasCycle = seen[start] && !mem.containsKey(start);
    if (hasCycle) return false;
    
    seen[start] = true;
    
    if (mem.containsKey(start)) return mem.get(start);
    
    // reached zero
    boolean reachedZero = arr[start] == 0;
    if (reachedZero) return true;
    
    // how to deal with a cycle
    
    int jumpFoward = start + arr[start];
    int jumpBack = start - arr[start];
    
    boolean canReachZero = canReach(arr, jumpFoward, mem, seen)
      || canReach (arr, jumpBack, mem, seen);
    mem.put(start, canReachZero);
    return canReachZero;
  }
}
