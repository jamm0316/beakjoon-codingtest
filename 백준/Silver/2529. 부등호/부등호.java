import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static String[] signs;
    static boolean[] visited = new boolean[10];
    static String maxResult = "";
    static String minResult = "";
    static boolean foundMax = false;
    static boolean foundMin = false;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        signs = br.readLine().split(" ");

        // 최대값 먼저 찾기
        dfs(new StringBuilder(), 0, true);

        // visited 초기화
        Arrays.fill(visited, false);

        // 최소값 찾기
        dfs(new StringBuilder(), 0, false);

        System.out.println(maxResult);
        System.out.println(minResult);
    }

    private static void dfs(StringBuilder num, int depth, boolean isMaxSearch) {
        if (isMaxSearch && foundMax) return; // 최대값 찾았으면 종료
        if (!isMaxSearch && foundMin) return; // 최소값 찾았으면 종료

        if (depth == N + 1) {
            String result = num.toString();
            if (isMaxSearch) {
                maxResult = result;
                foundMax = true;
            } else {
                minResult = result;
                foundMin = true;
            }
            return;
        }

        if (isMaxSearch) {
            // 최대값 찾기: 큰 숫자부터
            for (int i = 9; i >= 0; i--) {
                if (!visited[i]) {
                    if (depth == 0 || check(num.charAt(depth - 1) - '0', i, signs[depth - 1])) {
                        visited[i] = true;
                        num.append(i);
                        dfs(num, depth + 1, true);
                        num.deleteCharAt(num.length() - 1);
                        visited[i] = false;
                    }
                }
            }
        } else {
            // 최소값 찾기: 작은 숫자부터
            for (int i = 0; i <= 9; i++) {
                if (!visited[i]) {
                    if (depth == 0 || check(num.charAt(depth - 1) - '0', i, signs[depth - 1])) {
                        visited[i] = true;
                        num.append(i);
                        dfs(num, depth + 1, false);
                        num.deleteCharAt(num.length() - 1);
                        visited[i] = false;
                    }
                }
            }
        }
    }

    private static boolean check(int a, int b, String sign) {
        return sign.equals("<") ? a < b : a > b;
    }
}