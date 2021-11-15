class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    var result = new ArrayList<Integer>();
    largestDivisibleSubset(nums, 0, new ArrayList<Integer>(), result);
    return result;
  }
  
  void largestDivisibleSubset(int[] nums, int start, List<Integer> perm, List<Integer> result) {
    int n = perm.size();
    if (n > 1) {
      // check if last 2 elements are ok
      int a = perm.get(n-1);
      int b = perm.get(n-2);
      if (a % b != 0) return;
    }
    
    if (perm.size() > result.size()) {
      result.clear();
      result.addAll(perm);
    }
    
    for(int i = start; i < nums.length; i++) {
      perm.add(nums[i]);
      largestDivisibleSubset(nums, i+1, perm, result);
      perm.remove(perm.size()-1);
    }
  }
}
