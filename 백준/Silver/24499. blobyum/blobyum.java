import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int max = 0;

        for (int i = 0; i < K; i++) {
            max += arr[i];
        }

        int now = max;
        for (int i = K; i < N * 2; i++) {
            now -= arr[(i - K) % N];
            now += arr[i % N];
            max = Math.max(now, max);
        }

        System.out.println(max);
    }
}
