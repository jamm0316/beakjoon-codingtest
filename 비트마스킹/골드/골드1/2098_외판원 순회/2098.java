import java.util.Scanner;

public class Main {

    static final int INF = 1_000_000_000;
    static int n;
    static int[][] w; 
    static int[][] dp;
        
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        
        n = input.nextInt();
        w = new int[n][n];
        dp = new int[1 << n][n];
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                w[i][j] = input.nextInt();
            }
        }
        
        for (int i = 0; i < (1 << n); i++) {
            for (int j = 0; j < n; j++) {
                dp[i][j] = -1;
            }
        }
        
        System.out.println(tsp(1, 0));
        
    }
    static int tsp(int visited, int currentCity) {
        if (visited == (1 << n) - 1) {
            return w[currentCity][0] == 0 ? INF : w[currentCity][0];
        }
        
        if (dp[visited][currentCity] != -1) {
            return dp[visited][currentCity];
        }
        
        dp[visited][currentCity] = INF;
        
        for (int nextCity = 0; nextCity < n; nextCity++) {
            if ((visited & (1 << nextCity)) == 0 && w[currentCity][nextCity] != 0) {
                int cost = w[currentCity][nextCity] + tsp(visited | (1 << nextCity), nextCity);
                dp[visited][currentCity] = Math.min(dp[visited][currentCity], cost);
            }
        }
        
        return dp[visited][currentCity];
    }
}
