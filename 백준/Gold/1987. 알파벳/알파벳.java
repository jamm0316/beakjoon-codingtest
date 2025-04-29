import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x;
        int y;
        int count;

        Node(int x, int y, int count) {
            this.x = x;
            this.y = y;
            this.count = count;
        }

        @Override
        public String toString() {
            return "Node{x: " + x + ", y: " + y + ", count: " + count + "}";
        }
    }

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static int N, M;
    static boolean[] visited;
    static char[][] graph;
    static int maxCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        visited = new boolean[26];
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new char[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        visited[graph[0][0] - 'A'] = true;
        backtrack(new Node(0, 0, 1));
        System.out.println(maxCount);
    }

    static void backtrack(Node node) {
        maxCount = Math.max(maxCount, node.count);

        visited[graph[node.x][node.y] - 'A'] = true;

        for (int i = 0; i < 4; i++) {
            int nx = node.x + dx[i];
            int ny = node.y + dy[i];

            if (0 <= nx && nx < N && 0 <= ny && ny < M
                    && !isVisited(nx, ny)) {
                visited[graph[nx][ny] - 'A'] = true;
                backtrack(new Node(nx, ny, node.count + 1));
                visited[graph[nx][ny] - 'A'] = false;
            }
        }
    }

    static boolean isVisited(int x, int y) {
        return visited[graph[x][y] - 'A'];
    }
}
