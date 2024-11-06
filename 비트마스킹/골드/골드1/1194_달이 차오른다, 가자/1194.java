import java.io.*;
import java.util.*;

public class Main {

    static class Position {
        int x, y, keys;

        public Position(int x, int y, int keys) {
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }

    static int N, M;
    static char[][] maze;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        maze = new char[N][M];
        visited = new boolean[N][M][64]; // 64 = 2^6, 6개의 열쇠 상태 관리

        Position start = null;

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '0') {
                    start = new Position(i, j, 0);
                    maze[i][j] = '.';
                }
            }
        }

        System.out.println(bfs(start));
    }

    static int bfs(Position start) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y][0] = true;
        int moves = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position cur = queue.poll();

                if (maze[cur.x][cur.y] == '1') {
                    return moves;
                }

                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    int keys = cur.keys;

                    if (nx < 0 || ny < 0 || nx >= N || ny >= M || visited[nx][ny][keys]) continue;

                    char cell = maze[nx][ny];

                    if (cell == '#') continue;

                    if (cell >= 'a' && cell <= 'f') {
                        keys |= (1 << (cell - 'a'));
                    }

                    if (cell >= 'A' && cell <= 'F') {
                        if ((keys & (1 << (cell - 'A'))) == 0) continue;
                    }

                    if (!visited[nx][ny][keys]) {
                        visited[nx][ny][keys] = true;
                        queue.offer(new Position(nx, ny, keys));
                    }
                }
            }
            moves++;
        }
        return -1;
    }
}
