import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] map;
    static int[][] fireDist, jhDist;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        fireDist = new int[N][M];
        jhDist = new int[N][M];

        for (int i = 0; i < N; i++) {
            Arrays.fill(fireDist[i], -1);
            Arrays.fill(jhDist[i], -1);
        }

        Deque<int[]> fire = new ArrayDeque<>();

        int sx = -1, sy = -1;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char cur = line.charAt(j);
                map[i][j] = cur;
                if (cur == 'J') {
                    sx = i;
                    sy = j;
                    jhDist[i][j] = 0;
                } else if (cur == 'F') {
                    fire.offer(new int[]{i, j});
                    fireDist[i][j] = 0;
                }
            }
        }

        while (!fire.isEmpty()) {
            int[] cur = fire.poll();
            int x = cur[0];
            int y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;
                if (map[nx][ny] == '#') continue;
                if (fireDist[nx][ny] != -1) continue;

                fireDist[nx][ny] = fireDist[x][y] + 1;
                fire.offer(new int[]{nx, ny});
            }
        }
        Deque<int[]> dq = new ArrayDeque<>();
        dq.offer(new int[]{sx, sy});

        if (isEdge(sx, sy)) {
            System.out.println(1);
            return;
        }

        while (!dq.isEmpty()) {
            int[] cur = dq.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (!inRange(nx, ny)) continue;
                if (map[nx][ny] == '#') continue;
                if (jhDist[nx][ny] != -1) continue;

                int nextTime = jhDist[x][y] + 1;

                if (fireDist[nx][ny] != -1 && fireDist[nx][ny] <= nextTime) continue;

                jhDist[nx][ny] = nextTime;
                dq.offer(new int[]{nx, ny});

                if (isEdge(nx, ny)) {
                    System.out.println(nextTime + 1);
                    return;
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }

    static private boolean inRange(int x, int y) {
        return 0 <= x && x < N && 0 <= y && y < M;
    }

    static private boolean isEdge(int x, int y) {
        return x == 0 || y == 0 || x == N - 1 || y == M - 1;
    }
}
