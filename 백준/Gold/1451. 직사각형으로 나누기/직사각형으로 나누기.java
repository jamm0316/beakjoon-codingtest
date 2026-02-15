import java.io.*;
import java.util.*;

public class Main {
    static long[][] psum;
    static int N, M;

    // sum of subgrid [r1..r2][c1..c2] (0-indexed)
    static long sum(int r1, int c1, int r2, int c2) {
        return psum[r2 + 1][c2 + 1] - psum[r1][c2 + 1] - psum[r2 + 1][c1] + psum[r1][c1];
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] grid = new int[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                grid[i][j] = line.charAt(j) - '0';
            }
        }

        psum = new long[N + 1][M + 1];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < M; j++)
                psum[i + 1][j + 1] = grid[i][j] + psum[i][j + 1] + psum[i + 1][j] - psum[i][j];

        long ans = 0;

        for (int r1 = 0; r1 < N - 2; r1++)
            for (int r2 = r1 + 1; r2 < N - 1; r2++) {
                long a = sum(0, 0, r1, M - 1);
                long b = sum(r1 + 1, 0, r2, M - 1);
                long c = sum(r2 + 1, 0, N - 1, M - 1);
                ans = Math.max(ans, a * b * c);
            }

        for (int c1 = 0; c1 < M - 2; c1++)
            for (int c2 = c1 + 1; c2 < M - 1; c2++) {
                long a = sum(0, 0, N - 1, c1);
                long b = sum(0, c1 + 1, N - 1, c2);
                long c = sum(0, c2 + 1, N - 1, M - 1);
                ans = Math.max(ans, a * b * c);
            }

        for (int r = 0; r < N - 1; r++)
            for (int c = 0; c < M - 1; c++) {
                long a = sum(0, 0, r, c);
                long b = sum(0, c + 1, r, M - 1);
                long cc = sum(r + 1, 0, N - 1, M - 1);
                ans = Math.max(ans, a * b * cc);
            }

        for (int r = 0; r < N - 1; r++)
            for (int c = 0; c < M - 1; c++) {
                long a = sum(0, 0, r, M - 1);
                long b = sum(r + 1, 0, N - 1, c);
                long cc = sum(r + 1, c + 1, N - 1, M - 1);
                ans = Math.max(ans, a * b * cc);
            }

        for (int c = 0; c < M - 1; c++)
            for (int r = 0; r < N - 1; r++) {
                long a = sum(0, 0, r, c);
                long b = sum(r + 1, 0, N - 1, c);
                long cc = sum(0, c + 1, N - 1, M - 1);
                ans = Math.max(ans, a * b * cc);
            }

        for (int c = 0; c < M - 1; c++)
            for (int r = 0; r < N - 1; r++) {
                long a = sum(0, 0, N - 1, c);
                long b = sum(0, c + 1, r, M - 1);
                long cc = sum(r + 1, c + 1, N - 1, M - 1);
                ans = Math.max(ans, a * b * cc);
            }

        System.out.println(ans);
    }
}