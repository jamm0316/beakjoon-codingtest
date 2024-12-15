import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            arr[i] = Integer.parseInt(st.nextToken());;
        }

        Arrays.sort(arr);

        int result = findMaxDistance(N, C, arr);
        System.out.println(result);
    }

    private static int findMaxDistance(int N, int C, int[] arr) {
        int low = 1;
        int high = arr[N - 1] - arr[0];
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;
            if (canPlace(arr, mid, C)) {
                result = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return result;
    }

    private static boolean canPlace(int[] arr, int distance, int routers) {
        int count = 1;
        int lastPosition = arr[0];

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] - lastPosition >= distance) {
                count++;
                lastPosition = arr[i];
                if (count == routers) {
                    return true;
                }
            }
        }
        return false;
    }
}
