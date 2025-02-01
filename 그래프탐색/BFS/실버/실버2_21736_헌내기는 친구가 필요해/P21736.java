import java.io.*;
import java.util.*;

public class Main {
    static int row;
    static int col;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        String[][] map = new String[row][col];
        boolean[][] visited = new boolean[row][col];
        int[] position = new int[2];

        for (int i = 0; i < row; i++) {
            String[] input = br.readLine().split("");
            for (int j = 0; j < col; j++) {
                map[i][j] = input[j];
                if (input[j].equals("I")) {
                    position[0] = i;
                    position[1] = j;
                }
            }
        }

        String result = bfs(map, visited, position);
        if (result.equals("0")) {
            result = "TT";
        }
        bw.write(result);
        bw.close();
        br.close();
    }

    private static String bfs(String[][] map, boolean[][] visited, int[] position) {
        Queue<int[]> queue = new LinkedList<>();
        visited[position[0]][position[1]] = true;
        queue.offer(position);
        int count = 0;
        int[] dx = new int[]{1, -1, 0, 0};
        int[] dy = new int[]{0, 0, 1, -1};

        while (!queue.isEmpty()) {
            int[] curPosition = queue.poll();
            int x = curPosition[0];
            int y = curPosition[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (0 <= nx && nx < row && 0 <= ny && ny < col
                && !visited[nx][ny]) {
                    visited[nx][ny] = true;
                    if (!map[nx][ny].equals("X")) {
                        queue.offer(new int[]{nx, ny});
                        if (map[nx][ny].equals("P")) {
                            count++;
                        }
                    }
                }
            }
        }
        return String.valueOf(count);
    }
}
