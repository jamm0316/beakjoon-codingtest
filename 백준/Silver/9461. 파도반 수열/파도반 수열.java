import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] queries = new int[N];
        int maxN = 0;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = Integer.parseInt(st.nextToken());
            if (queries[i] > maxN) {
                maxN = queries[i];
            }
        }

        long[] dp = new long[Math.max(101, maxN + 1)];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i <= maxN; i++) {
            dp[i] = dp[i - 3] + dp[i - 2];
        }

        for (int query : queries) {
            sb.append(dp[query]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
