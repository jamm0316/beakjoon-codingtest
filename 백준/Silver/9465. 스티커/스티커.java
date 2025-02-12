import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        while (T-- > 0) {
            int n = Integer.parseInt(br.readLine()); // 스티커 열의 개수
            int[][] sticker = new int[2][n + 1];
            int[][] dp = new int[2][n + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= n; j++) {
                    sticker[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            // 초기 값 설정
            dp[0][1] = sticker[0][1];
            dp[1][1] = sticker[1][1];

            // DP 점화식 적용
            for (int j = 2; j <= n; j++) {
                dp[0][j] = sticker[0][j] + Math.max(dp[1][j - 1], dp[1][j - 2]);
                dp[1][j] = sticker[1][j] + Math.max(dp[0][j - 1], dp[0][j - 2]);
            }

            // 두 경우 중 최대 값 선택
            sb.append(Math.max(dp[0][n], dp[1][n])).append("\n");
        }
        
        System.out.print(sb);
    }
}