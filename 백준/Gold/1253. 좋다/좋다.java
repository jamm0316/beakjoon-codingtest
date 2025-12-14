import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());

        long[] arr = new long[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        int goodCount = 0;

        // 각 i를 target으로 잡고, 나머지에서 두 수 합으로 만들 수 있는지 확인
        for (int i = 0; i < N; i++) {
            long target = arr[i];
            int left = 0;
            int right = N - 1;

            boolean isGood = false;

            while (left < right) {
                // target 자기 자신을 재료로 쓰면 안 되므로 스킵
                if (left == i) {
                    left++;
                    continue;
                }
                if (right == i) {
                    right--;
                    continue;
                }

                long sum = arr[left] + arr[right];

                if (sum == target) {
                    isGood = true;
                    break;
                } else if (sum < target) {
                    left++;
                } else {
                    right--;
                }
            }

            if (isGood) goodCount++;
        }

        System.out.println(goodCount);
    }
}