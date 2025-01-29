import java.io.*;
import java.util.*;

public class Main {
    static int row, col;
    static int[][] graph;
    static boolean[][] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        row = Integer.parseInt(st.nextToken());
        col = Integer.parseInt(st.nextToken());
        int compost = Integer.parseInt(st.nextToken());
        graph = new int[row + 1][col + 1];
        visited = new boolean[row + 1][col + 1];

        //graphInitialize
        for (int i = 0; i < compost; i++) {
            st = new StringTokenizer(br.readLine());
            int curRow = Integer.parseInt(st.nextToken());
            int curCol = Integer.parseInt(st.nextToken());

            graph[curRow][curCol] = 1;
        }

        int maxCompostSize = 0;

        //searchBfs
        for (int i = 0; i <= row; i++) {
            for (int j = 0; j <= col; j++) {
                if (graph[i][j] == 1 && !visited[i][j]) {
                    maxCompostSize = Math.max(maxCompostSize, bfs(i, j));
                }
            }
        }

        bw.write(String.valueOf(maxCompostSize));
        bw.close();
        br.close();
    }

    private static int bfs(int startRow, int startCol) {
        int[] dRow = new int[]{1, -1, 0, 0};
        int[] dCol = new int[]{0, 0, 1, -1};
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol});
        visited[startRow][startCol] = true;
        int size = 1;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curRow = cur[0];
            int curCol = cur[1];

            for (int i = 0; i < 4; i++) {
                int nRow = curRow + dRow[i];
                int nCol = curCol + dCol[i];

                if (nRow <= row && 1 <= nRow
                        && nCol <= col && 1 <= nCol
                        && !visited[nRow][nCol]) {
                    if (graph[nRow][nCol] == 1) {
                        queue.offer(new int[]{nRow, nCol});
                        visited[nRow][nCol] = true;
                        size++;
                    }
                }
            }
        }
        return size;
    }
}
