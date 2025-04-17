import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] graph = new int[N][3];
        int[][] maxDp = new int[N][3];
        int[][] minDp = new int[N][3];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        }

        for (int i = 0; i < N; i++) {
            if (i == 0) {
                maxDp[i] = graph[i];
                minDp[i] = graph[i];
            } else {
                for (int j = 0; j < 3; j++) {
                    if (j == 0) {
                        maxDp[i][j] = Math.max(maxDp[i - 1][j] + graph[i][j], maxDp[i - 1][j + 1] + graph[i][j]);
                        minDp[i][j] = Math.min(minDp[i - 1][j] + graph[i][j], minDp[i - 1][j + 1] + graph[i][j]);
                    } else if (j == 1) {
                        maxDp[i][j] = Math.max(maxDp[i - 1][j] + graph[i][j], maxDp[i - 1][j - 1] + graph[i][j]);
                        maxDp[i][j] = Math.max(maxDp[i][j], maxDp[i - 1][j + 1] + graph[i][j]);
                        minDp[i][j] = Math.min(minDp[i - 1][j] + graph[i][j], minDp[i - 1][j - 1] + graph[i][j]);
                        minDp[i][j] = Math.min(minDp[i][j], minDp[i - 1][j + 1] + graph[i][j]);
                    } else {
                        maxDp[i][j] = Math.max(maxDp[i - 1][j] + graph[i][j], maxDp[i - 1][j - 1] + graph[i][j]);
                        minDp[i][j] = Math.min(minDp[i - 1][j] + graph[i][j], minDp[i - 1][j - 1] + graph[i][j]);
                    }
                }
            }
        }
        int max = Arrays.stream(maxDp[N - 1]).max().getAsInt();
        int min = Arrays.stream(minDp[N - 1]).min().getAsInt();
        bw.write(max + " " + min);
        bw.close();
        br.close();
    }
}
