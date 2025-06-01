/*
1. A수 보다 큰 수 들 중 가장 작은 수를 선택
*/
import java.util.*;

class Solution {
    static int score;
    public int solution(int[] A, int[] B) {
        Arrays.sort(A);
        Arrays.sort(B);
        
        int aIdx = 0;
        int bIdx = 0;
        
        while (aIdx < A.length && bIdx < B.length) {
            if (B[bIdx] > A[aIdx]) {
                score++;
                aIdx++;
            }
            bIdx++;
        }
        return score;
    }
}