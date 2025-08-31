import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = INF;

        for (int start = 0; start < 3; start++) {
            int[][] dp = new int[N][3];

            //1. 시작색 설정
            for (int c = 0; c < 3; c++) {
                dp[0][c] = (c == start) ? cost[0][c] : INF;
            }

            //2. 1 ~ N번 째 누적 cost 갱신
            for (int i = 1; i < N; i++) {
                dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            //3. N번째가 시작 색깔과 같지 않을 경우 answer 갱신
            for (int end = 0; end < 3; end++) {
                if (end == start) continue;
                answer = Math.min(answer, dp[N - 1][end]);
            }
        }

        System.out.println(answer);
    }
}
