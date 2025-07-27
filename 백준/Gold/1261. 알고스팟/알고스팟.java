import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x, y, dist;

        Position(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }
    }

    static int N, M;
    static int[][] map;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //열
        M = Integer.parseInt(st.nextToken());  //행

        map = new int[M][N];
        visited = new boolean[M][N];
        for (int i = 0; i < M; i++) {
            map[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int minDist = 0;
        Queue<Position> queue = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        queue.offer(new Position(0, 0, 0));  //x, y, curDist
        visited[0][0] = true;

        while (!queue.isEmpty()) {
            Position now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i], ny = now.y + dy[i];
                if (now.x == M - 1 && now.y == N - 1) {
                    return now.dist;
                }

                if (0 <= nx && nx < M && 0 <= ny && ny < N && !visited[nx][ny]) {

                    visited[nx][ny] = true;
                    if (map[nx][ny] == 0) {
                        queue.offer(new Position(nx, ny, now.dist));
                    } else {
                        queue.offer(new Position(nx, ny, now.dist + 1));
                    }
                }
            }
        }
        return minDist;
    }
}
