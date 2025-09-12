import java.io.*;
import java.util.*;

public class Main {
    static int N, L;
    static int[][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }        }

        int answer = 0;

        //1. 행 검사
        for (int r = 0; r < N; r++) {
            int[] line = new int[N];
            for (int c = 0; c < N; c++) line[c] = map[r][c];
            if (canPass(line)) answer++;
        }

        //2. 열 검사
        for (int c = 0; c < N; c++) {
            int[] line = new int[N];
            for (int r = 0; r < N; r++) line[r] = map[r][c];
            if (canPass(line)) answer++;
        }

        System.out.println(answer);
    }

    private static boolean canPass(int[] line) {
        boolean[] used = new boolean[N];

        for (int i = 0; i < N - 1; i++) {
            int curr = line[i];
            int next = line[i + 1];
            int diff = next - curr;

            if (diff == 0) continue;

            // 오르막 (현재보다 다음 1이 높음) -> 뒤 L칸 검사
            if (diff == 1) {
                for (int k = 0; k < L; k++) {
                    int idx = i - k;
                    if (idx < 0) return false;
                    if (line[idx] != curr) return false;
                    if (used[idx]) return false;
                    used[idx] = true;
                }
            } else if (diff == -1) {
                for (int k = 1; k <= L; k++) {
                    int idx = i + k;
                    if (idx >= N) return false;
                    if (line[idx] != next) return false;
                    if (used[idx]) return false;
                    used[idx] = true;
                }
            } else {
                return false;
            }
        }
        return true;
    }
}
