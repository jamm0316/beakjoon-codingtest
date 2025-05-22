import java.io.*;
import java.util.*;

public class P30805 {
    static int[] A, B;
    static int N, M;
    static List<Integer> result = new ArrayList<>();
    static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        M = sc.nextInt();
        B = new int[M];
        for (int i = 0; i < M; i++) B[i] = sc.nextInt();

        dfs(0, 0, new ArrayList<>());

        System.out.println(result.size());
        if (!result.isEmpty()) {
            for (int num : result) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static void dfs(int ai, int bi, List<Integer> path) {
        if (found) return;

        // 뒤에서부터 큰 수부터 붙이기
        for (int num = 100; num >= 1; num--) {
            int nextA = findNextIndex(A, ai, num);
            int nextB = findNextIndex(B, bi, num);

            if (nextA != -1 && nextB != -1) {
                List<Integer> nextPath = new ArrayList<>(path);
                nextPath.add(num);
                dfs(nextA + 1, nextB + 1, nextPath);
                if (found) return;
            }
        }

        // 공통 수열을 하나 찾았다면 저장
        if (!path.isEmpty() && !found) {
            result = path;
            found = true;
        }
    }

    static int findNextIndex(int[] arr, int start, int target) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
}
