import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] wine = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = wine[0];
        if (N > 1) {
            dp[1] = wine[0] + wine[1];
        }

        if (N > 2) {
            dp[2] = Math.max(dp[1], Math.max(wine[2] + dp[0], wine[1] + wine[2]));
        }

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(wine[i] + dp[i - 2], wine[i] + wine[i - 1] + dp[i - 3]));
        }

        System.out.println(dp[N - 1]);
    }
}
