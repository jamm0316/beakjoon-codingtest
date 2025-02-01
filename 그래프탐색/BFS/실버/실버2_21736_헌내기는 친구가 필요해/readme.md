page link : [https://www.acmicpc.net/problem/21736](https://www.acmicpc.net/problem/21736)

---

# 풀이전략

1. `row`, `col`을 받는다.
2. `visited`를 만든다
3. `for row`, `for col`만큼 순회하며 `map`을 만든다.
    1. 이때, `I`의 좌표를 받는다.
4. bfs를 실행한다.
    1. `dx`, `dy`를 설정한다.
    2. `Queue`에 최초 좌표를 넣는다.
    3. `nx`, `ny`를 설정한다(`현재 좌표 + dx(dy)`)
    4. `!visited[nx][ny]`이고, `nx`, `ny`의 범위가 `map` 안에 있다면,
        1. `map[nx][ny]`가 `X`가 아닐때
            - `queue.offer([nx, ny])`
            - P일 때 `count++`

## 사용된 알고리즘

그래프 이론: BFS

---

# code

## Java

```java
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

```
