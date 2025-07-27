import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x, y, cost;

        Position(int x, int y, int cost) {
            this.x = x;
            this.y = y;
            this.cost = cost;
        }
    }

    static int N, M;
    static int[][] map;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  // 가로
        M = Integer.parseInt(st.nextToken());  // 세로

        map = new int[M][N];
        for (int i = 0; i < M; i++) {
            String line = br.readLine();
            for (int j = 0; j < N; j++) {
                map[i][j] = line.charAt(j) - '0';
            }
        }

        System.out.println(zeroOneBfs());
    }

    private static int zeroOneBfs() {
        int[][] dist = new int[M][N];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE);
        Deque<Position> deque = new ArrayDeque<>();

        dist[0][0] = 0;
        deque.offerFirst(new Position(0, 0, 0));

        while (!deque.isEmpty()) {
            Position now = deque.pollFirst();

            for (int d = 0; d < 4; d++) {
                int nx = now.x + dx[d], ny = now.y + dy[d];
                if (0 <= nx && nx < M && 0 <= ny && ny < N) {
                    int newCost = dist[now.x][now.y] + map[nx][ny];
                    if (dist[nx][ny] > newCost) {
                        dist[nx][ny] = newCost;
                        if (map[nx][ny] == 0) {
                            deque.offerFirst(new Position(nx, ny, newCost));
                        } else {
                            deque.offerLast(new Position(nx, ny, newCost));
                        }
                    }
                }
            }
        }

        return dist[M - 1][N - 1];
    }
}