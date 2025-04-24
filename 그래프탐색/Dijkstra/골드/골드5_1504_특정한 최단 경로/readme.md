page link : https://www.acmicpc.net/problem/1504

---

# 💡 풀이전략
- 구하고자 하는 값
    - 1번부터 시작해 2개의 정점을 반드시 지나 N번 정점으로 이동하는 최단 길이

---

- 성공한 풀이전략
    1. 1에서 N까지 가는 최단 경로 중 v1, v2를 모두 지나야함.
    2. 단, 경로1(1 → v1 → v2 → N)과 경로2(1 → v2 → v1 → N) 의 최단경로의 합 중에서 작은 숫자를 반환해야한다.
    3. 양방향 그래프이고, 간선 수가 최대 200,000이므로 DFS는 시간 초과.
    4. 따라서, 다익스트라를 3번 사용하여 해결
        1. `int[] from1 = dijkstra(1);` → 1번 노드 부터 각 노드별 최단 경로
        2. `int[] fromV1 = dijkstra(target1);` → `target1`번 노드 부터 각 노드별 최단 경로
        3. `int[] fromV2 = dijkstra(target2);` → `target2`번 노드 부터 각 노드별 최단 경로
        4. 최단거리 비교하여 더 작은값 출력
            1. `path1 = (1 ~ V1 최단거리) + (V1 ~ V2 최단거리) + (V2 ~ N 최단거리)`
            2. `path2 = (1 ~ V2 최단거리) + (V2 ~ V1 최단거리) + (V1 ~ N 최단거리)`
- 실패한 풀이전략(DFS를 사용하여 시간초과 및 최단경로 구할 수 없음)
    1. 입력값을 받는다.
        1. `nodes`, `links`
        2. `links`를 순회하며 `List<List<Integer>>`를 받는다.(양방향)
        3. `targetA`, `targetB` 를 받는다.
    2. `Queue<Node> queue = new PriorityQueue<>((a, b) -> a.dist - b.dist)` 선언
        1. `queue`에 1과 0을 넣는다.
        2. 여기서 `Node`는 inner class로 `nextNode`, `dist`를 field로 갖는다.
        3. `queue.poll()` 이후 `curNode`, `curDist`를 추출한다.
        4. `graph[curNode]` 를 순회하며, `visited[target1]`, `visited[target2] == true`이고, `curNode == N`이면, `minDist = Math.min(minDist, curNode.dist)`로 갱신한다.
    3. `minDist`를 출력한다.

## 🎨 사용된 알고리즘
다익스트라

---

# 🧑🏻‍💻 code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, E;
    static List<List<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w)); // 양방향
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(1);
        int[] fromV1 = dijkstra(v1);
        int[] fromV2 = dijkstra(v2);

        long path1 = (long)from1[v1] + fromV1[v2] + fromV2[N];
        long path2 = (long)from1[v2] + fromV2[v1] + fromV1[N];

        long result = Math.min(path1, path2);
        System.out.println(result >= INF ? -1 : result);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.vertex]) continue;

            for (Node next : graph.get(now.vertex)) {
                if (dist[next.vertex] > dist[now.vertex] + next.cost) {
                    dist[next.vertex] = dist[now.vertex] + next.cost;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return dist;
    }
}
```

# 🪄 해결한 오류

## 1. BFS와 DFS를 사용해서 최단경로를 구하지 못하는 이유

### **📌 BFS가 안되는 이유**

- BFS는 가중치가 동일할 때만 최단 경로를 구할 수 있다.
- **가중치가 다르다면 반드시 다익스트라를 사용해야한다.**

### **📌 DFS가 안되는 이유**

- 경로를 하나하나 끝까지 따라간 후 백트레킹 하여 다른 경로를 탐색
- 모든 경로를 전부 시도하므로 최단 경로보다 더 긴 경로를 먼저 탐색할 수 도 있음.
    - 이 전제는 가중치가 적은 경로를 우선탐색하지 않는다는 전제가 됨.

### ✅ **다익스트라를 써야하는 이유**

- **가중치**가 있는 그래프에서 **한 정점에서 다른 모든 정점까지의 최단 거리**를 구하는 알고리즘
- **우선순위 큐**를 이용해 → 현재까지 가장 짧은 거리부터 탐색해 나감

### ⭐ **알고리즘 사용 조건 정리**

| **알고리즘** | **사용 조건** | **최단 거리 가능 여부** | **핵심 이유** |
| --- | --- | --- | --- |
| DFS | 깊이 우선 탐색, 모든 경로 탐색 | ❌ | 탐색 순서가 최단 경로 보장 안 됨 |
| BFS | 간선 가중치가 모두 같을 때 | ✅ | 먼저 도달한 것이 최단 거리 |
| Dijkstra | 가중치가 양수일 때 | ✅ | 우선순위 큐로 “가장 짧은 경로”를 먼저 탐색 |

---
