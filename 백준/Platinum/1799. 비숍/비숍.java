import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] board;
    static boolean[] diag1, diag2;
    static int maxBlack = 0, maxWhite = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        diag1 = new boolean[2 * N];
        diag2 = new boolean[2 * N];

        dfs(0, 0, 0);
        dfs(0, 1, 0);

        System.out.println(maxBlack + maxWhite);
    }

    static void dfs(int idx, int color, int count) {
        if (idx >= N * N) {
            if (color == 0)
                maxBlack = Math.max(maxBlack, count);
            else
                maxWhite = Math.max(maxWhite, count);
            return;
        }

        int x = idx / N;
        int y = idx % N;

        if ((x + y) % 2 == color) {
            if (board[x][y] == 1 && !diag1[x + y] && !diag2[x - y + (N - 1)]) {
                diag1[x + y] = diag2[x - y + (N - 1)] = true;
                dfs(idx + 1, color, count + 1);
                diag1[x + y] = diag2[x - y + (N - 1)] = false;
            }
        }

        dfs(idx + 1, color, count);
    }
}