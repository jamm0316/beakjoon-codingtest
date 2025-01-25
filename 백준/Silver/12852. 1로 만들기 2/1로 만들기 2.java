import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        int[] prev = new int[N + 1];

        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            prev[i] = i - 1;

            if (i % 2 == 0 && dp[i / 2] + 1 < dp[i]) {
                dp[i] = dp[i / 2] + 1;
                prev[i] = i / 2;
            }

            if (i % 3 == 0 && dp[i / 3] + 1 < dp[i]) {
                dp[i] = dp[i / 3] + 1;
                prev[i] = i / 3;
            }
        }

        bw.write(String.valueOf(dp[N]));
        bw.newLine();

        List<Integer> path = new ArrayList<>();
        while (N > 0) {
            path.add(N);
            N = prev[N];
        }

        for (int i = 0; i < path.size(); i++) {
            bw.write(String.valueOf(path.get(i)));
            if (i < path.size() - 1) bw.write(" ");
        }

        bw.close();
        br.close();
    }
}
