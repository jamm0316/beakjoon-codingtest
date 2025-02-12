import java.util.*;
import java.io.*;

public class Main {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    static int n;
    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            n = Integer.parseInt(st.nextToken());

            int[][] dp = new int[2][n + 1];
            int[][] stickerBoard = createStickerBoard(n);
            dp[0][1] = stickerBoard[0][1];
            dp[1][1] = stickerBoard[1][1];
            //System.out.println("stickerBoard = " + Arrays.deepToString(stickerBoard));

            for (int j = 2; j <= n; j++) {
                dp[0][j] = stickerBoard[0][j] + Math.max(dp[1][j - 1], dp[1][j - 2]);
                dp[1][j] = stickerBoard[1][j] + Math.max(dp[0][j - 1], dp[0][j - 2]);
            }

            bw.write(String.valueOf(Math.max(dp[0][n], dp[1][n])));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static int[][] createStickerBoard(int n) throws IOException {
        int[][] stickerBoard = new int[2][n + 1];
        for (int i = 0; i < 2; i++) {
            int[] temp = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            System.arraycopy(temp, 0, stickerBoard[i], 1, n);
        }
        return stickerBoard;
    }
}
