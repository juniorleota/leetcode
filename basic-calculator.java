/**
* Need major refactoring but works very quickly.
**/
class Solution {
    public int calculate(String s) {
        return calculate(s.trim(), -1).result;
    }
    
    RecursiveResult calculate(String s, int parenI) {
        Stack<Integer> stack = new Stack<>();
        int num = 0;
        // random char
        char lastOp = '^';
        int lastI = 0;
        for(int i=parenI + 1; i<s.length(); i++) {
            char c = s.charAt(i);
            
            if(Character.isDigit(c)) {
                num = num * 10 + (c-'0');
            }
            
            if (c == '(') {
                RecursiveResult recursiveResult = calculate(s, i);
                i = recursiveResult.i;
                num = recursiveResult.result;
            }
            
            if((!Character.isDigit(c) && c != ' ') || i == s.length() - 1) {
                if (lastOp == '-') stack.push(num * -1);
                else stack.push(num);
                
                if (c == ')') {
                    lastI = i;
                    break;
                }
                
                num = 0;
                if (c == '+' || c == '-') {
                    lastOp = c;
                }
                //System.out.println(c + " " + stack);
            }
            
            lastI = i;
        }
        int result = 0;
        for(int x: stack) result += x;
        return new RecursiveResult(result, lastI);
    }
    
    class RecursiveResult {
        int result,i;
        public RecursiveResult(int result, int i) {
            this.result = result;
            this.i = i;
        }
    }
}
