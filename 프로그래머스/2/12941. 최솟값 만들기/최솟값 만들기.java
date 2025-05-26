/**
* 1. 최소값과 최대값을 계속해서 더해서 누적해가는 식으로 작성하자.
*/
import java.util.*;
import java.util.stream.*;

class Solution {
    static int N, result;
    public int solution(int []A, int []B) {
        N = A.length;
        Arrays.sort(A);
        Arrays.sort(B);
       
        for(int i = 0; i< N; i++){
            result += (A[i] * B[N - i -1]);
        }
        
        return result;
    }
}