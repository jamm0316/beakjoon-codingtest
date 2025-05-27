import java.util.*;

class Solution {
    static int N;
    static List<Integer> arr;
    
    public int[] solution(int brown, int yellow) {
        N = brown + yellow;
        arr = new ArrayList<>();
        
        for (int i = N; i > 0; i--) {
            if (N % i == 0) {
                arr.add(N / i);
            }
        }
        
        for (int i = 0; i < arr.size() / 2 + 1; i++) {
            int height = arr.get(i);
            int width = arr.get(arr.size() - i - 1);
            if ((width - 2) * (height -2) == yellow) {
                return new int[]{width, height};
            }
        }
        return null;
    }
}