import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        char[][] normalGraph = new char[N][N];
        char[][] blindnessGraph = new char[N][N];

        for (int i = 0; i < N; i++) {
            String tokens = br.readLine();
            for (int j = 0; j < N; j++) {
                char color = tokens.charAt(j);
                normalGraph[i][j] = color;
                blindnessGraph[i][j] = (color == 'R') ? 'G' : color;
            }
        }

        int normalCount = countRegions(normalGraph);
        int blindnessCount = countRegions(blindnessGraph);

        System.out.println(normalCount + " " + blindnessCount);
    }

    static int countRegions(char[][] graph) {
        boolean[][] visited = new boolean[N][N];
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j]) {
                    countColorBoundaryBfs(graph, visited, i, j);
                    count++;
                }
            }
        }

        return count;
    }

    static void countColorBoundaryBfs(char[][] graph, boolean[][] visited, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;
        char color = graph[i][j];

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];

            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    if (graph[nx][ny] == color) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}