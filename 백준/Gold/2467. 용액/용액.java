import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;
        int ansL = arr[left];
        int ansR = arr[right];

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                ansL = arr[left];
                ansR = arr[right];
            }

            if (sum == 0) {
                System.out.println(ansL + " " + ansR);
                return;
            } else if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        System.out.println(ansL + " " + ansR);
    }
}
