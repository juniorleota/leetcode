class Solution {
    public int calculate(String s) {
        return calculate(0, s).result;
    }
    
    Result calculate(int start, String s) {
        int num = 0, lastI = 0;
        Stack<Integer> stack = new Stack<>();
        // store lastOperation so we know if we want to invert the new number
        char lastOp = ' ';
        for(int i = start; i < s.length(); i++) {
            char c = s.charAt(i);
            boolean digit = Character.isDigit(c);
            boolean lastElem = i == s.length() - 1;

            if (digit) {
                num = num * 10 + c-'0';
            }
            
            if ((!digit && c != ' ') || lastElem) {
                if (c == '(') {
                    Result res = calculate(i+1, s);
                    num = res.result;
                    i = res.i;
                    // if you use continue here, it will fail if the last char is ')'
                }
                
                // add before potentially breaking out of loop
                if (lastOp == '-') stack.add(num * -1);
                else stack.add(num);
                
                if (c == ')') {
                    lastI = i;
                    break;
                }
                
                if (c == '+' || c == '-') {
                    lastOp = c;
                }
                
                num = 0;
            }
        }
        int result = 0;
        for(int x: stack) result += x;
        
        return new Result(lastI, result);
    }
    
    class Result {
        int i,result;
        
        public Result(int i, int result) {
            this.i = i;
            this.result = result;
        }
    }
}
