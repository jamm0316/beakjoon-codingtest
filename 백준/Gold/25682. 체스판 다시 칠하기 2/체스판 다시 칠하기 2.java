import java.io.*;
import java.util.*;

public class Main {
    static int N, M, K;
    static int[][] mismatchW, mismatchB; // (0,0) 흰색 시작, 검정 시작
    static int[][] psW, psB; // prefix sum

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        mismatchW = new int[N][M];
        mismatchB = new int[N][M];

        // mismatch 계산
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                // (i+j) 짝수 → (0,0) 색과 동일
                char expectedW = ((i + j) % 2 == 0) ? 'W' : 'B';
                char expectedB = ((i + j) % 2 == 0) ? 'B' : 'W';

                mismatchW[i][j] = (board[i][j] == expectedW) ? 0 : 1;
                mismatchB[i][j] = (board[i][j] == expectedB) ? 0 : 1;
            }
        }

        // prefix sum 구하기
        psW = buildPrefixSum(mismatchW);
        psB = buildPrefixSum(mismatchB);

        int answer = Integer.MAX_VALUE;

        for (int i = 0; i + K <= N; i++) {
            for (int j = 0; j + K <= M; j++) {
                int sumW = getArea(psW, i, j, i + K - 1, j + K - 1);
                int sumB = getArea(psB, i, j, i + K - 1, j + K - 1);
                answer = Math.min(answer, Math.min(sumW, sumB));
            }
        }

        System.out.println(answer);
    }

    static int[][] buildPrefixSum(int[][] arr) {
        int[][] ps = new int[N + 1][M + 1]; // 1-based prefix sum
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                ps[i][j] = arr[i - 1][j - 1]
                        + ps[i - 1][j] + ps[i][j - 1] - ps[i - 1][j - 1];
            }
        }
        return ps;
    }

    static int getArea(int[][] ps, int x1, int y1, int x2, int y2) {
        // 입력은 0-based, ps는 1-based
        x1++; y1++; x2++; y2++;
        return ps[x2][y2] - ps[x1 - 1][y2] - ps[x2][y1 - 1] + ps[x1 - 1][y1 - 1];
    }
}