class Solution {
  public List<List<Integer>> threeSum(int[] nums) {
    if (nums.length < 3) return Collections.emptyList();
    var result = new ArrayList<List<Integer>>();
    int n = nums.length;
    
    Arrays.sort(nums);
    int maximum = nums[n - 1];
    
    var pairs = new HashSet<Pair>();
    for(int i = 0; i < n; i++) {
      for(int j = i + 1; j < n; j++) {
        int target = 0 - (nums[i] + nums[j]);
        // target doesnt exist
        if (target < nums[j] || target > maximum) continue;

        int k = binSrch(nums, target, j + 1, n - 1);
        if (k != -1) {
          pairs.add(new Pair(nums[i], nums[j], nums[k]));
        }
      }
    }
    
    pairs.forEach(x -> result.add(List.of(x.i, x.j, x.k)));
    return result;
  }
  
  int binSrch(int[] nums, int target, int low, int high) {
    while(low <= high) {
      int mid = low + (high - low)/2;
      if (nums[mid] == target) return mid;
      if (target < nums[mid]) high = mid - 1;
      else low = mid + 1;
    }
    
    return -1;
  }
  
  class Pair {
    int i, j, k;
    public Pair(int i, int j, int k) {
      int[] nums = new int[3];
      nums[0] = i;
      nums[1] = k;
      nums[2] = j;
      Arrays.sort(nums);
      this.i = nums[0];
      this.j = nums[1];
      this.k = nums[2];
    }
    
    @Override
    public boolean equals(Object other) {
      Pair o = (Pair) other;
      return this.i == o.i && this.j == o.j && this.k == o.k;
    }
    
    @Override
    public int hashCode(){
      return Objects.hash(i, j, k);
    }
  }
}
