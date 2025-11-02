import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        long[] arr = new long[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }

        // 1. 정렬
        Arrays.sort(arr);

        // 2. 투 포인터 초기화
        int left = 0;
        int right = N - 1;

        long bestSumAbs = Long.MAX_VALUE; // 지금까지 본 합 중 |합|이 가장 작은 값
        long ans1 = 0;
        long ans2 = 0;

        // 3. 두 포인터로 탐색
        while (left < right) {
            long sum = arr[left] + arr[right];
            long absSum = Math.abs(sum);

            // 더 좋은(0에 가까운) 값이면 갱신
            if (absSum < bestSumAbs) {
                bestSumAbs = absSum;
                ans1 = arr[left];
                ans2 = arr[right];

                // 합이 0이면 이것보다 더 좋은 건 없음
                if (sum == 0) {
                    break;
                }
            }

            // 합이 너무 크면(right가 너무 큼) 오른쪽 포인터를 줄이고,
            // 합이 너무 작으면(왼쪽이 너무 작음) 왼쪽 포인터를 키운다.
            if (sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        // 4. 오름차순 출력
        if (ans1 < ans2) {
            System.out.println(ans1 + " " + ans2);
        } else {
            System.out.println(ans2 + " " + ans1);
        }
    }
}