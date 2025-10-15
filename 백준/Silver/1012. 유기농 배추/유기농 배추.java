import java.io.*;
import java.util.*;

public class Main {
    static int T, N, M, K;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            K = Integer.parseInt(st.nextToken());
            map = new int[N][M];
            int count = 0;
            Queue<int[]> queue = new LinkedList<>();
            for (int i = 0; i < K; i++) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken()), y = Integer.parseInt(st.nextToken());
                map[x][y] = 1;
                queue.offer(new int[]{x, y});
            }

            while (!queue.isEmpty()) {
                int[] cur = queue.poll();
                int x = cur[0], y = cur[1];
                if (map[x][y] == 1) {
                    map[x][y] = -1;
                    dfs(x, y);
                    count++;
                }
            }
            sb.append(count).append("\n");
        }
        System.out.println(sb);
    }

    static private void dfs(int x, int y) {
        if (map[x][y] == 0) {
            return;
        }

        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            if (nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
            if (map[nx][ny] == 0 || map[nx][ny] == -1) continue;
            map[nx][ny] = -1;
            dfs(nx, ny);
        }
    }

    static private void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int i = cur[0], j = cur[1];
            for (int k = 0; k < 4; k++) {
                int nx = i + dx[k];
                int ny = j + dy[k];
                if (nx < 0 || N <= nx || ny < 0 || M <= ny) continue;
                if (map[nx][ny] == 0 || map[nx][ny] == -1) continue;
                map[nx][ny] = -1;
                queue.offer(new int[]{nx, ny});
            }
        }
    }
}
