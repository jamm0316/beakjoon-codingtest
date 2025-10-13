import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map, dp;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = 0;
        for (int x = 0; x < N; x++) {
            for (int y = 0; y < N; y++) {
                ans = Math.max(ans, dfs(x, y));
            }
        }
        System.out.println(ans);
    }

    private static int dfs(int x, int y) {
        if (dp[x][y] != 0) return dp[x][y];

        int best = 1;
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || N <= nx || ny < 0 || N <= ny ) continue;
            if (map[nx][ny] > map[x][y]) {
                best = Math.max(best, 1 + dfs(nx, ny));
            }
        }
        dp[x][y] = best;
        return best;
    }
}
