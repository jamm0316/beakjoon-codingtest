import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int M = Integer.parseInt(br.readLine());
        int count = 0;
        Arrays.sort(arr);

        int left = 0, right = N - 1;
        int sum = arr[left] + arr[right];
        while (left < right) {
            if (sum == M) {
                count++;
                sum -= arr[left];
                sum += arr[++left];
            } else if (sum > M) {
                sum -= arr[right];
                sum += arr[--right];
            } else if (sum < M) {
                sum -= arr[left];
                sum += arr[++left];
            }
        }
        System.out.println(count);
    }
}
