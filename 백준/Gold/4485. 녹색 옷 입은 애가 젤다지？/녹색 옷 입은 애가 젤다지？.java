import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int INF = Integer.MAX_VALUE;
    static int STAGE = 0;
    static class Link {
        int x, y, rupee;

        Link(int x, int y, int rupee) {
            this.x = x;
            this.y = y;
            this.rupee = rupee;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            if (N == 0) {
                break;
            }
            STAGE++;
            map = new int[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }

            int[][] dist = dijkstra();
            sb.append("Problem ").append(STAGE).append(": ").append(dist[N - 1][N - 1]).append("\n");
        }
        System.out.println(sb);
    }

    private static int[][] dijkstra() {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) {
            Arrays.fill(dist[i], INF);
        }
        dist[0][0] = map[0][0];

        PriorityQueue<Link> pq = new PriorityQueue<>((a, b) -> a.rupee - b.rupee);
        pq.offer(new Link(0, 0, dist[0][0]));

        while (!pq.isEmpty()) {
            Link cur = pq.poll();
            int x = cur.x, y = cur.y;
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i], ny = y + dy[i];
                if (nx < 0 || N <= nx || ny < 0 || N <= ny) continue;
                if (dist[nx][ny] > dist[x][y] + map[nx][ny]) {
                    dist[nx][ny] = dist[x][y] + map[nx][ny];
                    pq.offer(new Link(nx, ny, dist[nx][ny]));
                }
            }
        }
        return dist;
    }
}
