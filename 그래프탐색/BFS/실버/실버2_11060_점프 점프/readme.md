page link : [https://www.acmicpc.net/problem/11060](https://www.acmicpc.net/problem/11060)

---

# 풀이전략

1. 시작점에서 BFS 시작
2. 큐를 사용하여 현재 위치와 점프 횟수 저장
3. 각 칸에서 점프 가능한 범위 내의 칸들을 방문하여 최단 거리로 도달 가능한지 확인
4. 망약 오른쪽 끝(N-1 인덱스)에 도달하면 점프 횟수 반환
5. BFS 탐색이 끝날 때까지 도달 못하면 -1 출력
6. Ai == 0인 칸은 방문 불가하며, 이미 방문한 칸은 다시 방문 불가
</aside>

## 사용된 알고리즘

그래프 이론: BFS

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] graph = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        bw.write(String.valueOf(bfs(N, graph)));
        bw.close();
        br.close();
    }

    private static int bfs(int N, int[] graph) {
        boolean[] visited = new boolean[N];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int jumps = current[1];

            if (position == N - 1) {
                return jumps;
            }

            for (int nextPos = position + 1;
                 nextPos <= position + graph[position] && nextPos < N;
                 nextPos++) {
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, jumps + 1});
                }
            }
        }
        return -1;
    }
}

```
