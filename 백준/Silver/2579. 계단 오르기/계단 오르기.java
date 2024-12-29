import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // 입력: 계단의 개수
        int n = scanner.nextInt();
        int[] scores = new int[n + 1];
        int[] dp = new int[n + 1];

        // 입력: 계단 점수
        for (int i = 1; i <= n; i++) {
            scores[i] = scanner.nextInt();
        }

        // 초기값 설정
        if (n == 1) {
            System.out.println(scores[1]);
            return;
        }

        dp[1] = scores[1];
        if (n >= 2) {
            dp[2] = scores[1] + scores[2];
        }

        // DP 계산
        for (int i = 3; i <= n; i++) {
            dp[i] = Math.max(dp[i - 2], dp[i - 3] + scores[i - 1]) + scores[i];
        }

        // 결과 출력
        System.out.println(dp[n]);
    }
}