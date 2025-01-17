import java.util.*;

public class Main {
    static final int STATIC_NUMBER = 15746;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[N + 1];
        if (N <= 1) {
            System.out.println(N);
        } else {
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % STATIC_NUMBER;
            }
            System.out.println(dp[N]);
        }
    }
}
