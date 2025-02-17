import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - (int) Math.pow(j, 2)] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}

