import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] dp, coinSet;
    static int INF = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        TreeSet<Integer> coinSet = new TreeSet<>();
        for (int i = 0; i < N; i++) {
            int coin = Integer.parseInt(br.readLine());
            if (coin <= K) coinSet.add(coin);
        }

        dp = new int[K + 1];
        Arrays.fill(dp, INF);
        dp[0] = 0;

        for (int coin : coinSet) {  //각 동전에 대해
            for (int target = coin; target <= K; target++) {  //목표 금액(target)을 순회
                if (dp[target - coin] != INF) {  //이전 금액(target - coin)을 만들 수 있다면
                    dp[target] = Math.min(dp[target], dp[target - coin] + 1);
                }
            }
        }
        System.out.println(dp[K] == INF ? -1 : dp[K]);
    }
}
