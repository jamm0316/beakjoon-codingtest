page link : https://www.acmicpc.net/problem/1753

---

# 💡 풀이전략
- 구하고자 하는 값
    - 시작점으로부터 각 vertex의 최단경로 순차적으로 출력하기

---

- 성공한 풀이전략
    1. 입력값을 받는다.
        1. `vertex`와 `edge`
        2. `root`와 `List<List<Node>> graph`,
            1. 여기서 `Node`는 `int end`, `int cost`를 값으로 갖는다.
    2. 다익스트라를 사용한다
        1. `dist[vertex + 1]`을 만든다.
            1. `Arrays.fill(dist, INF)`를 활용하여 `Integer.MAX_VALUE`로 모두 초기화 한다.
            2. `dist[1]`은 0으로 초기화 한다. 
        2. `PriorityQueue` 이용
            - `Queue<Node> queue = PriorityQueue<>((a, b) → a.cost - b.cost)`
            - now.vertex를 순회하여 Node next를 탐색
                - `dist[next.vertex] > dist[now.vertex] + next.cost` 이면 `dist[next.vertex]` 갱신
    3. `dist[1]`부터 차례로 출력한다.

## 🎨 사용된 알고리즘
다익스트라(dijkstra)

---

# 🧑🏻‍💻 code

## :java:Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int endVertex;
        int cost;
        Node(int end, int cost) {
            this.endVertex = end;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return "Node{endVertex: " + endVertex + ", cost: " + cost + "}";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertex = Integer.parseInt(st.nextToken()), edge = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(br.readLine());

        //그래프 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<>());
        }

        //단방향 그래프 초기화
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        //dijkstra
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(root, 0));
        int[] dist = new int[vertex + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[root] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (dist[now.endVertex] < now.cost) continue;

            for (Node next : graph.get(now.endVertex)) {
                if (dist[next.endVertex] > dist[now.endVertex] + next.cost) {
                    dist[next.endVertex] = dist[now.endVertex] + next.cost;
                    queue.offer(new Node(next.endVertex, dist[next.endVertex]));
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

```

# 🪄 해결한 오류

## 1. **Dijkstra의 핵심 개념 요약**

### **1. 목적**

Dijkstra는 시작 노드(root)로부터 각 노드(vertex)까지의 누적 최소 비용을 계산하는

**그리디 + DP** 알고리즘이다.

탐색의 정렬 기준은 `PriorityQueue`가 담당하고, 최적 경로 저장은 dist[] 배열 (DP 테이블)이 담당한다.

---

### **2. 주요 구성 요소**

| **요소** | **설명** |
| --- | --- |
| dist[] | 각 정점까지의 **누적 최소 거리** 저장하는 DP 배열 |
| PriorityQueue<Node> | 누적 비용이 가장 적은 정점부터 탐색하기 위한 **우선순위 큐** |
| Node(endVertex, cost) | graph에서는 단건 cost 의미, queue에서는 누적 cost 의미 (역할 구분 필요) |

이때 각 자료구조에서 사용되는 Node field의 속성 차이를 이해해야 헷갈리지 않을 수 있다.

- **graph에서는 단건 비용**
- **queue에서는 누적 비용**

따라서, 이 흐름을 명확히 이해해야 전체 흐름이 자연스럽게 연결된다.

| **사용 위치** | endVertex **의미** | cost **의미** |
| --- | --- | --- |
| graph 내부 | 인접 정점 | 단건 간선 비용 |
| PriorityQueue 내부 | 현재 탐색 중인 정점 | **누적 비용** |

### 3. 위 개념을 활용하여 우선순위 큐에서 검증과 offer 기준을 정한다.

- 검증 기준
    - 최소 누적 비용을 저장한 dp에 갱신되어있는 다음 노드까지의 최소 비용 = `dist[now.nextVertex]`
    - 현재에서 다음 `vertex`까지 넘어갈 때 소요되는 누적 비용 = `now.cost`
    - 보통은 이 둘이 같으나, `dist[now.endtVertex]`가 다른 `Node` 검증에서 이미 최소로 갱신된 경험이 있다면 해당 검증은 필요 없게 되므로 `continue`로 다음 검증으로 넘어간다.
- offer 기준
    - `PriorityQueue`에 `offer`되는 `Node`의 `cost`는 `vertex`부터 다음 `vertex`까지의 누적 `cost`다.
    - 따라서, 위에서 갱신한 `root`부터 해당 `vertex`의 최소 누적 `cost`인 `dist[next.endVertex]`를 `next.endVertex` 의 `cost`로 사용해야한다.
