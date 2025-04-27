import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        StringBuilder sb = new StringBuilder();
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public String toString() {
            sb.append("Node{pos: [").append(x).append(", ").append(y)
                    .append("], dist: ").append(dist).append("}").append('\n')
                    .append(visited);
            return sb.toString();
        }
    }

    static int R, C;
    static char[][] graph;
    static boolean[] visited = new boolean[26];
    static int maxDist = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        //DFS탐색
        visited[graph[0][0] - 'A'] = true;
        recursive(new Node(0, 0, 1));

        System.out.println(maxDist);
    }

    private static void recursive(Node now) {
        maxDist = Math.max(maxDist, now.dist);

        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];

            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                int idx = graph[nx][ny] - 'A';
                if (!visited[idx]) {
                    visited[idx] = true;
                    recursive(new Node(nx, ny, now.dist + 1));
                    visited[idx] = false;
                }
            }
        }
    }
}
