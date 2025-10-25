import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        arr = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        M = Integer.parseInt(br.readLine());
        int[][] query = new int[M][2];
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < M; i++) {
            query[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int start = query[i][0] - 1, end = query[i][1] - 1;
            boolean isTrue = true;
            for (int j = 0; j < end - start; j++) {
                if (arr[start + j] != arr[end - j]) {
                    isTrue = false;
                    sb.append(0).append('\n');
                    break;
                }
            }
            if (isTrue) {
                sb.append(1).append('\n');
            }
        }
        System.out.println(sb);
    }
}
