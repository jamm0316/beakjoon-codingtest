import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String str = br.readLine();
            for (int j = 0; j < M; j++) {
                board[i][j] = str.charAt(j);
            }
        }

        int maxSize = Math.min(N, M);
        int answer = 1;

        for (int len = maxSize; len >= 1; len--) {
            for (int i = 0; i + len - 1 < N; i++) {
                for (int j = 0; j + len - 1 < M; j++) {
                    int x1 = i;
                    int y1 = j;
                    int x2 = i;
                    int y2 = j + len -1;
                    int x3 = i + len -1;
                    int y3 = j;
                    int x4 = i + len -1;
                    int y4 = j + len -1;

                    char c = board[x1][y1];

                    if (c == board[x2][y2] &&
                            c == board[x3][y3] &&
                            c == board[x4][y4]) {
                        answer = len * len;
                        System.out.println(answer);
                        return;
                    }
                }
            }
        }
        System.out.println(answer);
    }
}
