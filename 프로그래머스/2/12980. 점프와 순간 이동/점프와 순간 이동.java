import java.util.*;

public class Solution {
    static int count;
    public int solution(int n) {
        while (n > 0) {
            if (n % 2 != 0) {
                count++;
                n -= 1;
            } else {
                n /= 2;
            }
        }
    
        return count;
    }
}