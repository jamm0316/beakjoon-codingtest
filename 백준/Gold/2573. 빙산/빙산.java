import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int year = 0;
        while (true) {
            int comp = countComponents();
            if (comp >= 2) {
                System.out.println(year);
                return;
            }
            if (comp == 0) {
                System.out.println(0);
                return;
            }
            meltOnce();
            year++;
        }
    }

    static private void meltOnce() {
        int[][] dec = new int[N][M];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 0) continue;
                int sea = 0;
                for (int k = 0; k < 4; k++) {
                    int nx = i + dx[k];
                    int ny = j + dy[k];
                    if (nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
                    if (map[nx][ny] == 0) sea++;
                }
                dec[i][j] = sea;
            }
        }

        //일괄적용
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0) {
                    map[i][j] = Math.max(0, map[i][j] - dec[i][j]);
                }
            }
        }
    }

    static private int countComponents() {
        boolean[][] visited = new boolean[N][M];
        int components = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] > 0 && !visited[i][j]) {
                    components++;
                    if (components >= 2) return components;
                    bfs(i, j, visited);
                }
            }
        }
        return components;
    }

    static private void bfs(int sx, int sy, boolean[][] visited) {
        ArrayDeque<int[]> deque = new ArrayDeque<>();
        visited[sx][sy] = true;
        deque.offer(new int[]{sx, sy});

        while (!deque.isEmpty()) {
            int[] cur = deque.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
                if (map[nx][ny] > 0 && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    deque.offer(new int[]{nx, ny});
                }
            }
        }
    }
}
