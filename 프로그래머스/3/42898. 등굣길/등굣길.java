/*
dp[i][j] = row[i - 1] + col[i - 1]
*/

import java.util.*;

class Solution {
    static int MOD = 1_000_000_007;
    public int solution(int m, int n, int[][] puddles) {
        int row = n;
        int col = m;
        int[][] dp = new int[row][col];
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        dp[0][0] = 1;
        
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (dp[i][j] == -1) {
                    dp[i][j] = 0;
                    continue;
                }
                if (0 < i) dp[i][j] += dp[i - 1][j] % MOD;
                if (0 < j) dp[i][j] += dp[i][j - 1] % MOD;
                dp[i][j] %= MOD;
            }
        }
        
        return dp[n - 1][m - 1];
    }
}