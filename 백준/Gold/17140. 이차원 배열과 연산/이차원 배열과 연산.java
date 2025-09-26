import java.util.*;
import java.io.*;

public class Main {
    static final int MAX = 100;
    static int[][] A = new int[MAX][MAX];
    static int R = 3, C = 3;
    static int tr, tc, tk;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        tr = r - 1; tc = c - 1; tk = k;

        for (int i = 0; i < 3; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < 3; j++) {
                A[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        int ans = solve();
        System.out.println(ans);
    }

    private static int solve() {
        for (int t = 0; t <= 100; t++) {
            if (inRange(tr, tc) && A[tr][tc] == tk) return t;

            if (t == 100) break;

            if (R >= C) doROp();
            else doCOp();
        }
        return -1;
    }

    static boolean inRange(int r, int c) {
        return 0 <= r && r < R && 0 <= c && c < C;
    }

    //R 연산: 모든 행에 대해 수행
    static void doROp() {
        int newC = 0;
        for (int i = 0; i < R; i++) {
            int[] cnt = new int[101];
            for (int j = 0; j < C; j++) {
                int v = A[i][j];
                if (v != 0) cnt[v]++;
            }

            List<int[]> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (cnt[num] > 0) pairs.add(new int[]{num, cnt[num]});
            }

            pairs.sort((p1, p2) -> {
                if (p1[1] != p2[1]) return Integer.compare(p1[1], p2[1]);
                return Integer.compare(p1[0], p2[0]);
            });

            int idx = 0;
            for (int[] p : pairs) {
                if (idx >= MAX) break;
                A[i][idx++] = p[0];
                if (idx >= MAX) break;
                A[i][idx++] = p[1];
            }

            for (int j = idx; j < MAX; j++) {
                A[i][j] = 0;
            }

            newC = Math.max(newC, idx);
        }
        C = Math.min(newC, MAX);
    }

    private static void doCOp() {
        int newR = 0;
        for (int j = 0; j < C; j++) {
            int[] cnt = new int[101];
            for (int i = 0; i < R; i++) {
                int v = A[i][j];
                if (v != 0) cnt[v]++;
            }

            List<int[]> pairs = new ArrayList<>();
            for (int num = 1; num <= 100; num++) {
                if (cnt[num] > 0) pairs.add(new int[]{num, cnt[num]});
            }

            pairs.sort((p1, p2) -> {
                if (p1[1] != p2[1]) return Integer.compare(p1[1], p2[1]);
                return Integer.compare(p1[0], p2[0]);
            });

            int idx = 0;
            for (int[] p : pairs) {
                if (idx >= MAX) break;
                A[idx++][j] = p[0];
                if (idx >= MAX) break;
                A[idx++][j] = p[1];
            }

            for (int i = idx; i < MAX; i++) A[i][j] = 0;

            newR = Math.max(newR, idx);
        }
        R = Math.min(newR, MAX);
    }
}
