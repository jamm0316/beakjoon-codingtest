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
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //열
        M = Integer.parseInt(st.nextToken());  //행

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(bfs());
    }

    private static int bfs() {
        int[][] dp = new int[M][N];
        for (int[] each : dp) Arrays.fill(each, Integer.MAX_VALUE);

        Deque<Position> deque = new ArrayDeque<>();
        deque.offerFirst(new Position(0, 0, 0));  //x, y, curDist
        dp[0][0] = 0;

        while (!deque.isEmpty()) {
            Position now = deque.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i], ny = now.y + dy[i];
                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    int newDist = dp[now.x][now.y] + map[nx][ny];
                    if (dp[nx][ny] > newDist) {
                        dp[nx][ny] = newDist;
                        if (map[nx][ny] == 0) {
                            deque.offerFirst(new Position(nx, ny, now.dist));
                        } else {
                            deque.offerLast(new Position(nx, ny, now.dist + 1));
                        }
                    }
                }
            }
        }
        return dp[M - 1][N - 1];
    }
}
