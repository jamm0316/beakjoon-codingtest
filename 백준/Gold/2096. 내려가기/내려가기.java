import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        int[][] dpMax = new int[2][3];
        int[][] dpMin = new int[2][3];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 3; i++) {
            int token = Integer.parseInt(st.nextToken());
            dpMax[0][i] = token;
            dpMin[0][i] = token;
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int prev = (i + 1) % 2;
            int now = (prev + 1) % 2;
            for (int j = 0; j < 3; j++) {
                int token = Integer.parseInt(st.nextToken());
                if (j == 0) {
                    dpMax[now][j] = token + Math.max(dpMax[prev][0], dpMax[prev][1]);
                    dpMin[now][j] = token + Math.min(dpMin[prev][0], dpMin[prev][1]);
                } else if (j == 2) {
                    dpMax[now][j] = token + Math.max(dpMax[prev][1], dpMax[prev][2]);
                    dpMin[now][j] = token + Math.min(dpMin[prev][1], dpMin[prev][2]);
                } else {
                    dpMax[now][j] = token + Math.max(Math.max(dpMax[prev][j - 1], dpMax[prev][j]), dpMax[prev][j + 1]);
                    dpMin[now][j] = token + Math.min(Math.min(dpMin[prev][j - 1], dpMin[prev][j]), dpMin[prev][j + 1]);
                }
            }
        }
        int max = Arrays.stream(dpMax[(N + 1) % 2]).max().getAsInt();
        int min = Arrays.stream(dpMin[(N + 1) % 2]).min().getAsInt();

        System.out.println(sb.append(max).append(" ").append(min));
    }
}
