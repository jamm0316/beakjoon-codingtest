import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int[] sequence = Arrays.stream(br.readLine().split(" "))
				        .mapToInt(Integer::parseInt)
				        .toArray();
       
       int maxSum = sequence[0];
       int currentSum = sequence[0];
       
       for (int i = 1; i < N; i++) {
           currentSum = Math.max(currentSum + sequence[i], sequence[i]);
           maxSum = Math.max(maxSum, currentSum);
       }
       
       System.out.println(maxSum);
    }
}
