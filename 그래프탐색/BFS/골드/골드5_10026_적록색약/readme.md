page link : [https://www.acmicpc.net/problem/10026](https://www.acmicpc.net/problem/10026)

---

# 풀이전략
- 구하고자 하는 값
    - RGB 경계의 갯수, R-G가 같게 보일 때 구역의 갯수

---

- 성공한 풀이 전략
    1. 색의 경계가 아닌 구역의 갯수를 세야함.
        
        
        ```
        RG
        GR
        ```
        
        이 경우
        
        1. 색의 경계로 보면 일반인은 3, 색약은 1이다.(대각선허용)
        2. 그러나 구역의 갯수로 보면 일반인 4, 색약은 1이다(대각선 미허용)
    2. 따라서, (0, 0)부터 시작하여 모든 경계를 탐색하는 것이 아닌, 구역별로 탐색한다.
        1. 브루트 포스로 전체를 탐색한다.(일반인, 색약 2번 순회)
            1. 이때, 방문하지 않았다면 bfs를 돈다.
            2. 이후 `normalCount++` 또는 `blindnessCount++`를 한다.
    3. bfs 순회방법
        1. 브루트포스에서 입력되는 i, j값을 이용해 이전 색과 비교하여, 방문한적이 없고, 같은 색이라면 queue에 다음 좌표를 offer한다.
    4. 최종 `normalCount,` `blindnessCount`를 반환

---

- 실패한 풀이 전략(오답) → 색의 경계의 갯수를 셈
    1. dx, dy를 상하좌우로 설정하여 전역변수로 선언한다.
    2. RGB그래프를 만든다.
        - 일반인 그래프
        - 적록색약 그래프
    3. countSheet를 만든다.
        1. 그래프를 0,0부터 탐색하며, 인접 배열 값과 값이 같으면 해당 countSheet유지,
        값이 다르면 countSheet++
        2. 제일 마지막에 maxCount 반환
        3. 적록색약 그래프도 같은 조건으로 탐색

## 사용된 알고리즘
그래프 탐색, BFS

---

# code

## Java

```java
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
```

## 해결한 오류

### 1. 색의 경계(대각선 허용)와 격자모양의 경계(대각선 미허용) 혼동

| **구분** | **기존 코드** | **문제에서 요구하는 정답 방식** |
| --- | --- | --- |
| 기준 | 색이 바뀌는 경계 | 연결된 구역 (connected area) |
| 방식 | 한 번의 BFS + 색 변화에 따른 count | 전체 탐색 + 방문 안 한 곳마다 BFS/DFS |
| 핵심 | 대각선으로 인접한 색깔 연결성 허용 | 대각선으로 인접한 색깔 연결성 미허용 |
| 결과 | 일부 케이스에서만 정답 | 모든 케이스에 대해 정확 |

<br>
✅ 내가 놓친 부분

**위 문제에서는 상하 좌우의 경우에만 같은 구역이라 명확히 제시했다.**

![image](https://github.com/user-attachments/assets/f6157b69-3656-43c6-99d1-48fad8a9a475)

</aside>

그러나, 나는 대각선을 허용하는 코드를 작성했다.
이 때문에 몇몇 테스트 케이스에서는 틀린 답이 나온다.

📌 반례

```
RG
GR
```

❌ 기존 코드(일반인)

```
12
23
```

✅ 수정된 코드(일반인)

```
12
34
```

대각선을 허용하지 않는 문제의 조건이라면 일반인 눈에는 4개의 구역이 보여야한다.

그러나 내가 짠 코드는 대각선을 허용한다는 조건으로 3개의 경계로 보인다고 출력하였다.

따라서, 브루트포스와 bfs를 섞어서 사용한다.

즉, 하나의 색깔이 들어오면 인접한 색깔 중 해당 색깔과 같은 것을 모두 방문기록을 남긴다.

하나의 색깔 구역 탐색이 끝나면 count를 올려준다.

다시 브루트 포스 탐색을 이어나가며 방문한적이 없는 지역의 색깔이 들어오면 위를 반복한다.

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, 1, -1};
        static int N;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    
            N = Integer.parseInt(br.readLine());
            String[][] normalGraph = new String[N][N];
            String[][] blindnessGraph = new String[N][N];
    
            //그래프 초기화
            for (int i = 0; i < N; i++) {
                String[] tokens = br.readLine().split("");
                for (int j = 0; j < N; j++) {
                    String color = tokens[j];
                    normalGraph[i][j] = color;
                    blindnessGraph[i][j] = color.equals("R") ? "G" : color;
                }
            }
    
            int normalCount = countColorBoundary(normalGraph);
            int blindnessCount = countColorBoundary(blindnessGraph);
    
            bw.write(normalCount + " " + blindnessCount);
            bw.close();
            br.close();
        }
    
        static private int countColorBoundary(String[][] graph) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{0, 0});
            boolean[][] visited = new boolean[N][N];
            int[][] countSheet = new int[N][N];
            countSheet[0][0] = 1;
            visited[0][0] = true;
            int maxCount = 0;
    
            while (!queue.isEmpty()) {
                int[] pos = queue.poll();
                int x = pos[0], y = pos[1];
                String curColor = graph[x][y];
                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
    
                    if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        String nextColor = graph[nx][ny];
    
                        countSheet[nx][ny] = curColor.equals(nextColor) ? countSheet[x][y] : countSheet[x][y] + 1;
                        maxCount = Math.max(maxCount, countSheet[nx][ny]);
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            return maxCount;
        }
    }
    
    ```
