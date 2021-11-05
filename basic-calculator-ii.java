// possibly the worst code I have ever written
class Solution {
    public int calculate(String s) {
        Stack<String> stack = new Stack<>();
        int opIdx = -1;
        
        
        boolean highPriority = false;
        for(int i=0; i<s.length(); i++) {
            String c = s.charAt(i) + "";
            if ("*/+-".contains(c)) {
                String num = s.substring(opIdx + 1, i).trim();
                
                stack.add(num);
                if (highPriority) {
                    compute(stack, false);
                }
                stack.add(c);
                
                if ("*/".contains(c)) {
                    highPriority = true;
                } else {
                    highPriority = false;
                }
                
                opIdx = i;
            }
            
            if (i == s.length() - 1) {
                String num = s.substring(opIdx + 1).trim();
                stack.add(num);
                if (highPriority) compute(stack, false);
            }
            //System.out.println(stack);
        }
        Stack<String> reverse = reverseStack(stack);
        //System.out.println(reverse);
        while(reverse.size() >= 3) {
            compute(reverse, true);
        }
        
        return Integer.parseInt(reverse.pop());
    }
    
    Stack<String> reverseStack(Stack<String> stack) {
        Stack<String> reverse = new Stack<>();
        while(!stack.empty()) {
            reverse.push(stack.pop());
        }
        return reverse;
    }
    
    void compute(Stack<String> stack, boolean reverse) {
        int b = Integer.parseInt(stack.pop());
        String op = stack.pop();
        int a = Integer.parseInt(stack.pop());
        int result = reverse ? eval(b, op, a) : eval(a, op, b);
        //if (reverse) System.out.println(b + op + a + "=" + result);
        //else System.out.println(a + op + b + "=" + result);
        stack.add(result + "");
    }
    
    int eval(int a, String op, int b) {
        return switch(op) {
                case "+" -> a + b;
                case "-" -> a - b;
                case "*" -> a * b;
                case "/" -> a / b;
                default -> 0;
        };
    }
}
