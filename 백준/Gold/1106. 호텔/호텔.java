import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int C = Integer.parseInt(st.nextToken());
        int N = Integer.parseInt(st.nextToken());

        int[][] city = new int[N][2];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            city[i][0] = Integer.parseInt(st.nextToken());
            city[i][1] = Integer.parseInt(st.nextToken());
        }

        int maxCustomer = C + 100;
        int[] dp = new int[maxCustomer + 1];  //j명을 확보할 떄 최소 비용
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;

        for (int i = 0; i < N; i++) {
            int cost = city[i][0];
            int customer = city[i][1];
            for (int j = customer; j <= maxCustomer; j++) {
                if (dp[j - customer] != Integer.MAX_VALUE) {
                    //현재 고객 수 j를 확보하기 위한 최소 비용 갱신
                    dp[j] = Math.min(dp[j], dp[j - customer] + cost);
                }
            }
        }

        int result = Integer.MAX_VALUE;
        for (int i = C; i <= maxCustomer; i++) {
            result = Math.min(result, dp[i]);
        }

        System.out.println(result);
    }
}
