import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] prefixSum = new int[N + 1];
        int[] sequence = new int[N + 1];

        st = new StringTokenizer(br.readLine());
        for (int i = 1; i < N + 1; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
            prefixSum[i] = prefixSum[i - 1] + sequence[i];
        }

        StringBuilder result = new StringBuilder();
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            result.append(prefixSum[end] - prefixSum[start - 1]).append("\n");
        }
        System.out.print(result);
    }
}
