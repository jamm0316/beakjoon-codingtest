import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] cardDeck = new int[N + 1];
        int[] dp = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i <= N; i++) {
            cardDeck[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            for (int k = 1; k <= i; k++) {
                dp[i] = Math.max(dp[i], dp[i - k] + cardDeck[k]);
            }
        }
        System.out.println(dp[N]);
        br.close();
    }
}
