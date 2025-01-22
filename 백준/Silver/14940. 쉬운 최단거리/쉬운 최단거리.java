import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());
        int[][] map = new int[row][col];
        int[][] distance = new int[row][col];
        Queue<int[]> queue = new LinkedList<>();

        for (int i = 0; i < row; i++) {
            map[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                if (map[i][j] == 2) {
                    queue.offer(new int[]{i, j});
                    distance[i][j] = 0;
                } else if (map[i][j] == 0) {
                    distance[i][j] = 0;
                } else {
                    distance[i][j] = -1;
                }
            }
        }

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int x = current[0];
            int y = current[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx >= 0 && nx < row && ny >= 0 && ny < col
                        && map[nx][ny] == 1 && distance[nx][ny] == -1) {
                    distance[nx][ny] = distance[x][y] + 1;
                    queue.offer(new int[]{nx, ny});
                }
            }
        }

        for (int[] i : distance) {
            Arrays.stream(i).forEach(j -> {
                try {
                    bw.write(String.valueOf(j) + " ");
                } catch (IOException e) {
                }
            });
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}
