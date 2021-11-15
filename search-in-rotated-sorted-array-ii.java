class Solution {
  public boolean search(int[] nums, int target) {
    int n = nums.length;
    int l = 0, h = n - 1;
    
    while (l <= h) {
      int mid = l + (h - l)/2;
      // found target
      if(target == nums[mid]) return true;
      
      if(nums[mid] == nums[h]) h--;
      // right side is sorted
      else if (nums[mid] < nums[h]) {
        // in right side range which is sorted
        if (target > nums[mid] && target <= nums[h]) {
          l = mid + 1;
        } else h = mid - 1;
      // left side is sorted  
      } else if (nums[mid] > nums[l]) {
        // in left side range which is sorted
        if (target >= nums[l] && target < nums[mid]) {
          h = mid - 1;
        } else l = mid + 1;
      // that means that left is sorted
      } else if (nums[mid] > nums[h]) {
        // target bigger than mid therefore its in the right side
        if (target > nums[mid]) h = mid - 1;
        else l = mid + 1;
      } else System.out.println("Unexpected scenario");
    }
    
    return false;
  }
}
