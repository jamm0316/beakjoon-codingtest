import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] A = br.readLine().toCharArray();
        char[] B = br.readLine().toCharArray();
        int n = A.length, m = B.length;

        int[][] dp = new int[n + 1][m + 1];

        // 1) DP 채우기: LCS 길이
        for (int i = 1; i <= n; i++) {
            char a = A[i - 1];
            int[] dpi = dp[i];
            int[] dpi_1 = dp[i - 1];
            for (int j = 1; j <= m; j++) {
                if (a == B[j - 1]) {
                    dpi[j] = dpi_1[j - 1] + 1;
                } else {
                    dpi[j] = (dpi_1[j] >= dpi[j - 1]) ? dpi_1[j] : dpi[j - 1];
                }
            }
        }

        int lcsLen = dp[n][m];
        StringBuilder sb = new StringBuilder(lcsLen);

        // 2) 역추적: 실제 LCS 복원
        int i = n, j = m;
        while (i > 0 && j > 0) {
            if (A[i - 1] == B[j - 1]) {
                sb.append(A[i - 1]);
                i--; j--;
            } else {
                // 위/왼쪽 중 dp값이 큰 쪽으로 이동
                if (dp[i - 1][j] >= dp[i][j - 1]) i--;
                else j--;
            }
        }

        sb.reverse();

        // 출력
        String lcs = sb.toString();
        StringBuilder out = new StringBuilder();
        out.append(lcsLen).append('\n');
        if (lcsLen > 0) out.append(lcs);
        System.out.print(out);
    }
}