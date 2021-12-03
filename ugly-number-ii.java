class Solution {
  public int nthUglyNumber(int n) {
    // generating factors for prime
    int[] primes = {2,3,5};
    int[] pointers = {0,0,0};
    int[] dp = new int[n];
    dp[0] = 1;

    for(int i = 1; i<n; i++) {
      int min = Integer.MAX_VALUE;
      for(int p=0; p<primes.length; p++) {
        int ptr = pointers[p];
        int prime = primes[p];
        int factor = dp[ptr] * prime;

        min = Math.min(min, factor);
      }
      dp[i] = min;

      // increment pointers
      // if two pointers have same factor then we need to make sure
      // that we increment all prime pointer with same value
      // it is possible to have 2*3 and 3*2 (prime * multiplier)
      for(int p=0; p<primes.length; p++) {
        int ptr = pointers[p];
        int prime = primes[p];
        int factor = dp[ptr] * prime;

        if (factor == min) {
          pointers[p]++;
        }
      }
    }

    // issue with return n here
    return dp[n-1];
  }
}
