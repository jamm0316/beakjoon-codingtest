import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력 받기
        int n = scanner.nextInt();
        int[] wine = new int[n];
        for (int i = 0; i < n; i++) {
            wine[i] = scanner.nextInt();
        }

        // DP 배열 초기화
        int[] dp = new int[n];
        
        if (n == 1) {
            System.out.println(wine[0]);
            return;
        }

        dp[0] = wine[0];
        if (n > 1) {
            dp[1] = wine[0] + wine[1];
        }
        if (n > 2) {
            dp[2] = Math.max(dp[1], Math.max(wine[0] + wine[2], wine[1] + wine[2]));
        }

        // 점화식을 사용하여 DP 계산
        for (int i = 3; i < n; i++) {
            dp[i] = Math.max(dp[i - 1],
                     Math.max(dp[i - 2] + wine[i], dp[i - 3] + wine[i - 1] + wine[i]));
        }

        // 최댓값 출력
        System.out.println(dp[n - 1]);

        scanner.close();
    }
}