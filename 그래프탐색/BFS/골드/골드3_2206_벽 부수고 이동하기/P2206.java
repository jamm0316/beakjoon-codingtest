import java.io.*;
import java.util.*;

public class P2206 {
    static int N, M;
    static int[][] graph;
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x;
        int y;
        int count;
        boolean usedCrush;

        Node(int x, int y, int count, boolean usedCrush) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.usedCrush = usedCrush;
        }

        @Override
        public String toString() {
            return "Node{x: " + x + ", y: " + y + ", count: " + count + ", usedCrush: " + usedCrush + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int maxCount = 0;
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][M];
        queue.offer(new Node(0, 0, 1, false));
        visited[0][0] = true;
        boolean isGoal = false;
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M
                        && !visited[nx][ny]) {
                    if (graph[nx][ny] == 1) {
                        if (!now.usedCrush) {
                            visited[nx][ny] = true;
                            queue.offer(new Node(nx, ny, now.count + 1, true));
                        } else {
                            continue;
                        }
                    } else if (graph[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, now.count + 1, now.usedCrush));
                    }
                }
                maxCount = Math.max(maxCount, now.count);
            }
            if (now.x == N - 1 && now.y == M - 1) {
                isGoal = true;
            }
        }
        System.out.println(isGoal ? maxCount : -1);
    }
}
