package dp;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxN = 11;
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[maxN + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < N; i++) {
            System.out.println(dp[Integer.parseInt(
                    new StringTokenizer(br.readLine()).nextToken())]);
        }
    }
}
