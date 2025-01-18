import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        //0 1 2 3 4 5 6
        //0 1 1 2 3 5 8
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