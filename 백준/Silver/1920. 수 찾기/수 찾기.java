import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] targetArr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            targetArr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(targetArr);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int j = Integer.parseInt(st.nextToken());
            if (binarySearch(targetArr, j)) {
                sb.append("1\n");
            } else {
                sb.append("0\n");
            }
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static boolean binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2; // 중간 인덱스 계산
            if (arr[mid] == target) {
                return true; // 대상 발견
            } else if (arr[mid] < target) {
                left = mid + 1; // 오른쪽 절반 탐색
            } else {
                right = mid - 1; // 왼쪽 절반 탐색
            }
        }
        return false; // 대상 없음
    }
}