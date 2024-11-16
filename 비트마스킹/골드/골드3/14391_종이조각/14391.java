import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] paperMap = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String token = st.nextToken();
            for (int j = 0; j < M; j++) {
                paperMap[i][j] = Character.getNumericValue(token.charAt(j));
            }
        }

        System.out.println(BruteForceFindMaxScore(N, M, paperMap));
    }

    public static int BruteForceFindMaxScore(int N, int M, int[][] paperMap) {
        int maxSum = 0;

        for (int bitmask = 0; bitmask < (1 << (N * M)); bitmask++) {
            int currentSum = 0;

            for (int i = 0; i < N; i++) {
                int rowSum = 0;
                for (int j = 0; j < M; j++) {
                    int index = i * M + j;
                    if ((bitmask & (1 << index)) != 0) {
                        rowSum = rowSum * 10 + paperMap[i][j];
                    } else {
                        currentSum += rowSum;
                        rowSum = 0;
                    }
                }
                currentSum += rowSum;
            }

            for (int j = 0; j < M; j++) {
                int colSum = 0;
                for (int i = 0; i < N; i++) {
                    int index = i * M + j;
                    if ((bitmask & (1 << index)) == 0) {
                        colSum = colSum * 10 + paperMap[i][j];
                    } else {
                        currentSum += colSum;
                        colSum = 0;
                    }
                }
                currentSum += colSum;
            }
            maxSum = Math.max(maxSum, currentSum);
        }
        return maxSum;
    }
}
