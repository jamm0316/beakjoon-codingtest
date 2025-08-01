import java.io.*;
import java.util.*;

public class Main {
    static int N, K, B, minCount;
    static boolean[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new boolean[N + 1];

        for (int i = 1; i <= B; i++) {
            int each = Integer.parseInt(br.readLine());
            arr[each] = true;
        }
        int count = 0;
        for (int i = 1; i <= K; i++) {
            if (arr[i]) {
                count++;
            }
        }
        minCount = count;

        for (int i = 1; i <= N - K; i++) {
            boolean left = arr[i];
            boolean right = arr[K + i];
            if (left) {
                count--;
            }
            if (right) {
                count++;
            }
            minCount = Math.min(count, minCount);
        }

        System.out.println(minCount);
    }
}
