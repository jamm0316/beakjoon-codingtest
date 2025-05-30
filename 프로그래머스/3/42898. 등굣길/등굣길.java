import java.util.*;

class Solution {
    static int row, col;
    static int[][] dp;
    static int MOD = 1_000_000_007;
    
    public int solution(int m, int n, int[][] puddles) {
        row = n;
        col = m;
        dp = new int[row][col];
        
        for (int[] puddle : puddles) {
            dp[puddle[1] - 1][puddle[0] - 1] = -1;
        }
        
        dp[0][0] = 1;
        
        for (int x = 0; x < row; x++) {
            for (int y = 0; y < col; y++) {
                if (dp[x][y] == -1) {
                    dp[x][y] = 0;
                    continue;
                }
                
                if (x > 0) dp[x][y] += dp[x - 1][y] % MOD;
                if (y > 0) dp[x][y] += dp[x][y - 1] % MOD;
                
                dp[x][y] %= MOD;
            }
        }
        
        return dp[row - 1][col - 1];
    }
}