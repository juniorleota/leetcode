class Solution {
  public int findMaxForm(String[] strs, int zeroAmount, int oneAmount) {
    int n = strs.length;
    Metadata[] meta = new Metadata[n];
    for(int i = 0; i < n; i++) {
      meta[i] = new Metadata(strs[i]);
    }
    return findMaxForm(meta, zeroAmount, oneAmount, 0, new HashMap<>());
  }
  
  int findMaxForm(Metadata[] strs, int zeroAmount, int oneAmount, int i, Map<Key,Integer> mem) {
    int n = strs.length;
    if(i >= n) return 0;
    // it is possible to have "0000" which means event if oneAmount=0, we still need to keep searching
    if (zeroAmount == 0 && oneAmount == 0) return 0;
    Key k = new Key(i, zeroAmount, oneAmount);
    if(mem.containsKey(k)) return mem.get(k);

    Metadata s = strs[i];
    int newZeroAmount = zeroAmount - s.zeroes;
    int newOneAmount = oneAmount - s.ones;
    int includeS = 0;
    // to avoid adding 1 if S is not possible to be included
    // this could be avoided if we add it to the base case and return the
    // amount at the end
    if (newZeroAmount >= 0 && newOneAmount>=0) {
     includeS = 1 + findMaxForm(strs, newZeroAmount, newOneAmount, i+1, mem);
    }

    int excludeS = findMaxForm(strs, zeroAmount, oneAmount, i+1, mem);
    
    int res = Math.max(includeS, excludeS);
    mem.put(k, res);
    return res;
  }
  
  class Metadata {
    int zeroes;
    int ones;
    
    public Metadata(String s) {
      this.zeroes = 0;
      this.ones = 0;
      
      for(char c: s.toCharArray()) {
        if (c == '0') zeroes++;
        if (c == '1') ones++;
      }
    }
  }
  
  // 10x slower compared to a 3d array but its 10x more readable
  class Key {
    int i;
    int m;
    int n;
    
    public Key(int i, int m, int n){
      this.i = i;
      this.m = m;
      this.n = n;
    }
    
    @Override
    public boolean equals(Object other) {
      Key o = (Key)other;
      return o.i == i &&
        o.m == m &&
        o.n == n;
    }
    
    @Override
    public int hashCode() {
      int hash = 17;
      hash = hash*31 + i;
      hash = hash*31 + m;
      hash = hash*31 + n;
      return hash;
    }
  }
}
