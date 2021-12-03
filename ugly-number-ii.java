class Solution {
  public int nthUglyNumber(int n) {
    // generating factors for prime
    int[] primes = {2,3,5};    
    int[] pointers = {0,0,0};
    int[] dp = new int[n+1];
    dp[0] = 1;
    
    int k = 0;
    for(int i = 1; i<n; i++) {
      int min = Integer.MAX_VALUE;
      int minPtr = 0;
      for(int p=0; p<primes.length; p++) {
        int ptr = pointers[p];
        int prime = primes[p];
        int factor = dp[ptr] * prime;
        
        if(factor < min) {
          min = factor;
          minPtr = p;
        }
      }
      // System.out.println(Arrays.toString(pointers));
      // System.out.println(min);
      dp[i] = min;
      
      // increment pointers
      // if two pointers have same factor then we need to make sure
      // that we increment all prime pointer with same value
      for(int p=0; p<primes.length; p++) {
        int ptr = pointers[p];
        int prime = primes[p];
        int factor = dp[ptr] * prime;
        
        if (factor == min) {
          pointers[p]++;
        }
      }
    }
    return dp[n-1];
  }
  
  
}
