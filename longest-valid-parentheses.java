class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int longestParen = 0;
        int lCount = 0, rCount = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            //System.out.println(c + " " + stack);
            if (c == '(') {
                lCount ++;
                stack.add(0);
            }
            
            if (c == ')') {
                rCount++;
                boolean valid = lCount >= rCount;
                if (!valid) {
                    longestParen = Math.max(longestParen, getMaxElement(stack));
                    rCount = 0;
                    lCount = 0;
                    continue;
                }
                
                int subLength = 0;
                int ptr = -1;
                while(ptr != 0) {
                    ptr = stack.pop();
                    subLength += ptr; 
                }
                // link connected valid parenthesis
                if (!stack.empty() && stack.peek() != 0) subLength += stack.pop();
                stack.add(subLength + 2);
            }
        }
        if (!stack.empty()) longestParen = Math.max(longestParen, getMaxElement(stack));
        return longestParen;
    }
    
    int getMaxElement(Stack<Integer> stack) {
        int result = 0;
        while(!stack.empty()) result = Math.max(result, stack.pop());
        
        return result;
    }
}
