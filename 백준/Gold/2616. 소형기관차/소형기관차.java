import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt(); // 객차 수
        int[] passengers = new int[n + 1]; // 1-indexed
        for (int i = 1; i <= n; i++) {
            passengers[i] = sc.nextInt();
        }
        int maxTrain = sc.nextInt(); // 한 대의 소형 기관차가 끌 수 있는 최대 객차 수

        // 누적합 배열
        int[] prefixSum = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            prefixSum[i] = prefixSum[i - 1] + passengers[i];
        }

        // dp[i][j] : i개의 소형 기관차가 j번째 객차까지 고려했을 때 최대로 운송 가능한 손님 수
        int[][] dp = new int[4][n + 1]; // 0,1,2,3번째 기관차

        for (int i = 1; i <= 3; i++) {
            for (int j = i * maxTrain; j <= n; j++) {
                // i번째 기관차가 j-maxTrain+1 ~ j 까지 담당
                int sum = prefixSum[j] - prefixSum[j - maxTrain];
                dp[i][j] = Math.max(dp[i][j - 1], dp[i - 1][j - maxTrain] + sum);
            }
        }

        System.out.println(dp[3][n]); // 3대의 기관차로 가능한 최대 운송 인원
    }
}