class Solution {
  public boolean isMatch(String s, String p) {
    Boolean[][] mem = new Boolean[s.length()][p.length()];
     
    return isMatch(s.toCharArray(), p.toCharArray(), 0, 0, mem);
  }
  
  boolean isMatch(char[] s, char[] p, int si, int pi, Boolean[][] mem) {
    int sn = s.length;
    int pn = p.length;
    
    if(pi == pn) return si == sn;
    if(si < sn && mem[si][pi] != null) return mem[si][pi];
    
    char charP = p[pi];
    boolean result = false;
    boolean nextCharWildCard = pi<pn-1 && p[pi+1] == '*';
    if (nextCharWildCard) {
      // deal with ""/"a*"
      if(isMatch(s, p, si, pi+2, mem)){
        result = true;
      } else {

        int i = si;
        while(i<sn && charsMatch(s[i], charP)) {
          // increment first since we want to reach i == sn
          i++;
          if(isMatch(s, p, i, pi+2, mem)) {
            result = true;
            break;
          }
        }
      }
    } else if (si < sn && charsMatch(s[si], charP)) {
      result = isMatch(s, p, si+1, pi+1, mem);
    }
     
    if (si < sn) mem[si][pi] = result; 
    return result;
  }
  
  boolean charsMatch(char s, char p) {
    if (p == '.') return true;
    return s == p;
  }
}
