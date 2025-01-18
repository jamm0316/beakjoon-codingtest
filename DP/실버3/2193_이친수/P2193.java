import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[N + 1];

        if (N <= 1) {
            System.out.println(N);
        } else {
            dp[1] = 1;
            for (int i = 2; i <= N; i++) {
                dp[i] = dp[i - 2] + dp[i - 1];
            }
            System.out.println(dp[N]);
        }
    }
}
