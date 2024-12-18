import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        
        int[] dp = new int[N + 1];
        dp[1] = 0; // 1은 이미 1이므로 연산 횟수 0
        
        for (int i = 2; i <= N; i++) {
            dp[i] = dp[i - 1] + 1;
            
            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 2] + 1);
            }
            
            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i / 3] + 1);
            }
        }
        
        System.out.println(dp[N]);
    }
}