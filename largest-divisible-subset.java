class Solution {
  public List<Integer> largestDivisibleSubset(int[] nums) {
    Arrays.sort(nums);
    var result = new ArrayList<Integer>();
    var cache = new HashMap<Integer, Integer>();
    largestDivisibleSubset(nums, 0, new ArrayList<Integer>(), cache, result);
    return result;
  }
  
  void largestDivisibleSubset(int[] nums, int start, List<Integer> perm, Map<Integer, Integer> cache, List<Integer> result) {
    int n = perm.size();
    if (n > 1) {
      // check if last 2 elements are ok
      int a = perm.get(n-1);
      int b = perm.get(n-2);
      if (a % b != 0) return;
      
      int maxLength = cache.getOrDefault(a, 0);
      // this permutation is smaller therefore we can ignore
      if (perm.size() < maxLength) return;
      cache.put(a, perm.size());
    }

    //System.out.println(perm);
    if (perm.size() > result.size()) {
      result.clear();
      result.addAll(perm);
    }
    
    for(int i = start; i < nums.length ; i++) {
      perm.add(nums[i]);
      largestDivisibleSubset(nums, i+1, perm, cache, result);
      perm.remove(perm.size()-1);
    }
  }
}
