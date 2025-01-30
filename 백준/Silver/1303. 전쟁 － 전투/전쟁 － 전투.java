import java.io.*;
import java.util.*;

public class Main {
    static int row;
    static int col;
    static int powerOfFriendly;
    static int powerOfEnemy;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        col = Integer.parseInt(st.nextToken());
        row = Integer.parseInt(st.nextToken());
        String[][] graph = new String[row][col];
        boolean[][] visited = new boolean[row][col];

        for (int i = 0; i < row; i++) {
            graph[i] = br.readLine().trim().split("");
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (!visited[i][j] && graph[i][j].equals("W")) {
                    bfs(graph, visited, i, j, "W");
                } else if (!visited[i][j] && graph[i][j].equals("B")) {
                    bfs(graph, visited, i, j, "B");
                }
            }
        }

        bw.write(String.valueOf(powerOfFriendly));
        bw.write(" ");
        bw.write(String.valueOf(powerOfEnemy));
        bw.close();
        br.close();
    }

    private static void bfs(String[][] graph, boolean[][] visited, int x, int y,  String type) {
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};
        int count = 1;
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int xPosition = curPosition[0];
            int yPosition = curPosition[1];

            for (int i = 0; i < 4; i++) {
                int nx = xPosition + dx[i];
                int ny = yPosition + dy[i];
                if (0 <= nx && nx < row && 0 <= ny && ny < col
                        && !visited[nx][ny] && graph[nx][ny].equals(type)) {
                    visited[nx][ny] = true;
                    count++;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        if (type.equals("W")) {
            powerOfFriendly += Math.pow(count, 2);
        } else {
            powerOfEnemy += Math.pow(count, 2);
        }
    }
}
