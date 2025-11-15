import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int C = Integer.parseInt(br.readLine()); // 테스트 케이스 개수
        int[] Ns = new int[C];
        int maxN = 0;

        // 1. 입력 모두 읽으면서 최대 N 찾기
        for (int i = 0; i < C; i++) {
            int n = Integer.parseInt(br.readLine());
            Ns[i] = n;
            if (n > maxN) maxN = n;
        }

        // 2. maxN 까지 phi 배열 전처리 (오일러 피 함수)
        int[] phi = new int[maxN + 1];
        for (int i = 1; i <= maxN; i++) {
            phi[i] = i; // 초기값: phi(n) = n
        }

        // 에라토스테네스 스타일의 phi 전처리
        for (int p = 2; p <= maxN; p++) {
            if (phi[p] == p) { // p가 소수인 경우
                for (int j = p; j <= maxN; j += p) {
                    phi[j] -= phi[j] / p;
                }
            }
        }

        // 3. dp[n] = 0 <= x,y <= n 에서 보이는 점의 개수 (0,0 제외)
        int[] dp = new int[maxN + 1];
        if (maxN >= 1) {
            dp[1] = 3; // (1,0), (0,1), (1,1)
            for (int n = 2; n <= maxN; n++) {
                dp[n] = dp[n - 1] + 2 * phi[n];
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int n : Ns) {
            sb.append(dp[n]).append('\n');
        }

        System.out.print(sb);
    }
}