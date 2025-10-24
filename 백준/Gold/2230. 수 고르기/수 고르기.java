import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(arr);

        int left = 0, right = 0;
        int minCount = Integer.MAX_VALUE;
        while (left < N && right < N) {
            if (arr[right] - arr[left] >= M) {
                minCount = Math.min(minCount, arr[right] - arr[left]);
                left++;
            }
            else {
                right++;
            }
        }
        System.out.println(minCount);
    }
}
