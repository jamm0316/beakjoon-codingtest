import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = Integer.MAX_VALUE;
        int right = Integer.MIN_VALUE;
        for (int cost : arr) {
            left = Math.min(left, cost);
            right = Math.max(right, cost);
        }

        int answer = right;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (canAccept(arr, N, K, mid)) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }
        System.out.println(answer);
    }

    private static boolean canAccept(int[] arr, int N, int K, int maxCost) {
        int lastAccepted = -1;

        for (int i = 0; i < N; i++) {
            if (i - lastAccepted == K) {
                boolean found = false;
                for (int j = lastAccepted + 1; j <= i; j++) {
                    if (arr[j] <= maxCost) {
                        lastAccepted = j;
                        found = true;
                        break;
                    }
                }
                if (!found) return false;
            }
        }
        if (N - lastAccepted > K) return false;
        
        return true;
    }
}
