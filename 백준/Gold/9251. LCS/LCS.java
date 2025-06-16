import java.io.*;

class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String origin = br.readLine();
        String test = br.readLine();
        
        int result = findLCS(origin, test);
        System.out.println(result);
    }
    
    private static int findLCS(String origin, String test) {
        int N = origin.length();
        int M = test.length();
        
        int[][] dp = new int[N + 1][M + 1];
        
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                if (origin.charAt(i - 1) == test.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        
        return dp[N][M];
    }
}