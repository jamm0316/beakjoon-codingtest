import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y;
        char type;

        Node(int x, int y, char type) {
            this.x = x;
            this.y = y;
            this.type = type;
        }

        @Override
        public String toString() {
            return "{x: " + x + ", y: " + y  + ", type: " + type + "}";
        }
    }
    static List<List<Node>> map = new ArrayList<>();
    static int[][] dp;
    static int N, M, result;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static Queue<Node> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        dp = new int[N][M];

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map.get(i).add(new Node(i, j, input.charAt(j)));
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map.get(i).get(j).type == 'L') {
                    init();
                    result = Math.max(result, bfs(i, j));
                }
            }
        }
        System.out.println(result);
    }

    private static int bfs(int x, int y) {
        int maxDistance = 0;
        queue.offer(map.get(x).get(y));
        dp[x][y] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M && map.get(nx).get(ny).type == 'L'
                && dp[nx][ny] == -1) {
                    dp[nx][ny] = dp[now.x][now.y] + 1;
                    maxDistance = Math.max(maxDistance, dp[nx][ny]);
                    queue.offer(map.get(nx).get(ny));
                }
            }
        }
        return maxDistance;
    }

    private static void init() {
        for (int i = 0; i < N; i++) {
            Arrays.fill(dp[i], -1);
        }
    }
}
