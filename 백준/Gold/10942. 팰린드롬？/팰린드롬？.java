import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder out = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] a = new int[N + 1];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) a[i] = Integer.parseInt(st.nextToken());

        // DP: dp[s][e] = [s..e]가 팰린드롬인가?
        boolean[][] dp = new boolean[N + 1][N + 1];

        // 길이 1
        for (int i = 1; i <= N; i++) dp[i][i] = true;

        // 길이 2
        for (int i = 1; i < N; i++) {
            if (a[i] == a[i + 1]) dp[i][i + 1] = true;
        }

        // 길이 3 이상
        for (int len = 3; len <= N; len++) {
            for (int s = 1; s + len - 1 <= N; s++) {
                int e = s + len - 1;
                if (a[s] == a[e] && dp[s + 1][e - 1]) dp[s][e] = true;
            }
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int s = Integer.parseInt(st.nextToken());
            int e = Integer.parseInt(st.nextToken());
            out.append(dp[s][e] ? 1 : 0).append('\n');
        }
        System.out.print(out);
    }
}