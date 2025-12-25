import java.io.*;
import java.util.*;

public class Main {
    private static int N;
    private static int K;
    private static int[] arr;
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int right = 0;
        int maxLength = 0;
        int odd = 0;
        
        while (right < N) {
            if (arr[right] % 2 != 0) {
                odd++;
            }
            
            while (odd > K) {
                if (arr[left] % 2 != 0) {
                    odd--;
                }
                left++;
            }
            
            maxLength = Math.max(maxLength, right - left + 1 - odd);
            right++;
        }

        System.out.println(maxLength);
    }
}