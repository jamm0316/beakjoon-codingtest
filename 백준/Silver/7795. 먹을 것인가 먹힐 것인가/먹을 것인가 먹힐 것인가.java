import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine()); // 테스트 케이스 개수

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken()); // A 배열 크기
            int M = Integer.parseInt(st.nextToken()); // B 배열 크기

            int[] A = new int[N];
            int[] B = new int[M];

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                A[i] = Integer.parseInt(st.nextToken());
            }

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                B[i] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(A);
            Arrays.sort(B);

            int count = 0;

            for (int i = 0; i < N; i++) {
                count += lowerBound(B, A[i]);
            }

            sb.append(count).append("\n");
        }

        System.out.print(sb);
    }

    private static int lowerBound(int[] arr, int value) {
        int left = 0, right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < value) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }
}