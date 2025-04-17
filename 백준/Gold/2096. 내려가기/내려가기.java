import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, maxDp[0], 0, 3);
        System.arraycopy(input, 0, minDp[0], 0, 3);

        for (int i = 1; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cur = i % 2, prev = (i - 1) % 2;

            maxDp[cur][0] = Math.max(maxDp[prev][0], maxDp[prev][1]) + input[0];
            maxDp[cur][1] = Math.max(Math.max(maxDp[prev][0], maxDp[prev][1]), maxDp[prev][2]) + input[1];
            maxDp[cur][2] = Math.max(maxDp[prev][1], maxDp[prev][2]) + input[2];

            minDp[cur][0] = Math.min(minDp[prev][0], minDp[prev][1]) + input[0];
            minDp[cur][1] = Math.min(Math.min(minDp[prev][0], minDp[prev][1]), minDp[prev][2]) + input[1];
            minDp[cur][2] = Math.min(minDp[prev][1], minDp[prev][2]) + input[2];
        }
        int last = (N - 1) % 2;
        int max = Arrays.stream(maxDp[last]).max().getAsInt();
        int min = Arrays.stream(minDp[last]).min().getAsInt();
        bw.write(max + " " + min);
        bw.close();
        br.close();
    }
}
