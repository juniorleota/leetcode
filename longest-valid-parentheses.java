class Solution {
    public int longestValidParentheses(String s) {
        Stack<Integer> stack = new Stack<>();
        int maxLength = 0;
        int lCount = 0, rCount = 0;
        for(int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '(') {
                lCount ++;
                stack.add(0);
            } else if (c == ')') {
                rCount++;
                boolean valid = lCount >= rCount;
                if (!valid) {
                    stack.clear();
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
                int newValidLength = subLength + 2;
                stack.add(newValidLength);
                maxLength = Math.max(maxLength, newValidLength);
            }
        }
        return maxLength;
    }
}
