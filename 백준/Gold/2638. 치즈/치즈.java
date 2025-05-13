import java.io.*;
import java.util.*;

public class Main {
    static int N, M, totalCheese, totalTime;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        visited = new boolean[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 1) {
                    totalCheese++;
                }
            }
        }
        while (totalCheese > 0) {
            markOuterAir();
            removeCheese();
            totalTime++;
        }
        System.out.println(totalTime);
    }

    private static void markOuterAir() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(visited[i], false);
        }
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{0, 0});
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }

    private static void removeCheese() {
        List<int[]> toRemove = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 1) {
                    int contact = 0;
                    for (int k = 0; k < 4; k++) {
                        int nx = i + dx[k];
                        int ny = j + dy[k];
                        if (0 <= nx && nx < N && 0 <= ny && ny < M && visited[nx][ny]) {
                            contact++;
                        }
                    }
                    if (contact >= 2) {
                        toRemove.add(new int[]{i, j});
                    }
                }
            }
        }
        for (int[] pos : toRemove) {
            map[pos[0]][pos[1]] = 0;
            totalCheese--;
        }
    }
}
