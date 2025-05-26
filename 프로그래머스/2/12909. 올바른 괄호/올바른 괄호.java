import java.util.*;

class Solution {
    static Stack<Character> stack = new Stack<>();
    boolean solution(String s) {
        for(char c : s.toCharArray()) {
            if (c == '(') {
                stack.push('(');
            } else {
                if (!stack.isEmpty()) {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        System.out.println(stack);
        if (stack.size() == 0) {
            return true;
        }
        return false;
    }
}