page link : [https://www.acmicpc.net/problem/1707](https://www.acmicpc.net/problem/1707)
---

# 💡 풀이전략
- 구하고자 하는 값
    - 이분그래프인지 판별
- 풀이전략
    1. 이분 그래프의 정의
        - 이분 그래프는 정점 두 개의 그룹(A, B)으로 나누었을 때 같은 그룹끼리 연결된 간선이 없는 그래프
        - DFS나 BFS로 탐색하면서 서로 다른 색(그룹)을 칠했을 때, 인접한 두 정점이 같은 색이 된다면 이분 그래프가 아님
        <img width="811" height="355" alt="image" src="https://github.com/user-attachments/assets/b31ec414-f55f-4210-8e46-f0e5330c6a01" />

    2. 핵심 아이디어
        - 각 정점에 색깔(1 또는 -1)을 칠한다.
        - **DFS나 BFS를 돌면서 인접한 정점에 반대 색깔을 칠함.**
        - 이미 칠한 정점인데 인접한 두 노드의 색깔이 같다면 이분 그래프 아님.
    3. 그래프 탐색
        - 그래프가 연결 그래프가 아닐 수 도 있음. → 모든 노드에 대해 방문 체크 필요
        - DFS나 BFS 모두 가능하지만 BFS가 직관적임

## 🎨 사용된 알고리즘
BFS, 깊이우선 탐색

---

# 🧑🏻‍💻 code

## Java

```java
package graph;

import java.io.*;
import java.util.*;

public class Main {
    static int[] color; //각 정점의 색 (0: 미방문, 1: 빨강, -1: 파랑)
    static List<List<Integer>> graph;
    static boolean isTrue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int link = Integer.parseInt(st.nextToken());

            //graph 초기화
            graph = new ArrayList<>();
            for (int k = 0; k <= node; k++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < link; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            color = new int[node + 1];
            isTrue = true;

            for (int k = 1; k <= node; k++) {
                if (color[k] == 0) {
                    bfs(k);
                }
            }

            sb.append(isTrue ? "YES" : "NO").append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (color[next] == 0) {
                    queue.offer(next);
                    color[next] = -color[now];
                } else if (color[next] == color[now]) {
                    isTrue = false;
                    return;
                }
            }
        }
    }
}

```
