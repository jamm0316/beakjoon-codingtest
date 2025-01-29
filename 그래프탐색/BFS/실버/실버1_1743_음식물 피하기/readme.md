page link : [https://www.acmicpc.net/problem/1743](https://www.acmicpc.net/problem/1743)

---

# 풀이전략

1. 상하 좌우 움직일 수 있는 좌표를 준다
    1. `dx, dy = {1, -1, 0, 0}, {0, 0, 1, -1}`
2. 그래프와 음식물의 좌표를 받는다.
3. 0,0 부터 돌면서 1이라면 상하좌우의 크기를 체크한다.
    1. 이 때, `Queue`를 사용
        1. `Queue`에는 `int[]` 배열로 좌표 사용
    2. 인접한 부분에 있을 시 size에 +1씩
4. 최종 size가 가장 큰것을 출력

## 사용된 알고리즘

그래프 이론: bfs

---

# code

## Java

```java
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

```
