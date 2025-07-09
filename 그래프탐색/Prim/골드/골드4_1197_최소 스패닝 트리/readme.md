page link : https://www.acmicpc.net/problem/1197

---

# 💡 풀이전략
- 핵심 아이디어
    - 입력으로 주어진 그래프에서 모든 정점을 연결하면서
    - 간선 가중치의 합이 가장 작은 트리 찾기
    - 사이클 없이 V - 1개의 간선을 선택
- 풀이전략
    1. 임의의 한 정점에서 시작
    2. 현재 트리에 연결된 노드들과 연결된 가장 작은 가중치의 간선을 선택
    3. 선택한 간선의 도착 노드를 트리에 추가
    4. 이 과정을 모든 노드가 연결될 때까지 반복
- 세부 처리
    1. 우선순위 큐(PriorityQueue)를 사용해 가장 작은 간선을 빠르게 선택
    2. visited[] 배열로 이미 트리에 포함된 노드인지 체크
    3. 각 간선 선택 시 누적 비용(totalCost)에 추가

## 🎨 사용된 알고리즘
**Prim(프림 알고리즘)**: MST(최소 스패닝 트리)를 구하는 탐욕적(greedy) 알고리즘

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int next;
        int cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{next: " + next + ", cost: " + cost + "}";
        }
    }

    static List<List<Node>> tree = new ArrayList<>();
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                    cost = Integer.parseInt(st.nextToken());

            tree.get(start).add(new Node(end, cost));
            tree.get(end).add(new Node(start, cost));
        }

        System.out.println(prim(1));
    }

    private static int prim(int start) {
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));

        int totalCost = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.next]) continue;
            visited[now.next] = true;
            totalCost += now.cost;

            for (Node nextNode : tree.get(now.next)) {
                if (!visited[nextNode.next]) {
                    queue.offer(new Node(nextNode.next, nextNode.cost));
                }
            }
        }
        return totalCost;
    }
}

```

# 🪄 해결한 오류

## 1. 스패닝 트리란?

### 🌳 **스패닝 트리**

- 그래프 안의 모든 정점을 연결하고, 사이클이 없는 부분 그래프
- (즉, 트리 구조로 모든 노드 연결)

### 🌲 최소 스패닝 트리(MST)

- 스패닝 트리 중 간선 가중치의 합이 최소인 것
- 조건
    1. 정점 개수가 V라면, 스패닝 트리는 항상 V - 1개의 간선을 가짐
    2. 간선들을 선택할 때 사이클이 생기지 않도록 해야 함
- 예시 (간선 가운데 숫자는 가중치를 의미함)
    
    ```
    1 ---1--- 2
     \       /
      \     /
       3   2
        \ /
         3
    ```
    
    - 위와 같이 연결되어 있는 트리라면 1-2 간선, 2-3 간선의 합은 3으로 가장 작다.
    - 따라서 3이 최소 스패닝 트리가 된다.

---

## 2. 다익스트라 vs Prim

### 🦋 공통점

1. 둘 다 우선순위 큐(RrioriryQueue) 사용
2. 둘 다 작은 값부터 처리
3. 방문 체크(visited[])가 있어 구조가 매우 비슷함

### ⚖️ 다익스트라와 Prim 차이

| **비교 항목** | **🌱 Prim** | **🛣️ 다익스트라** |
| --- | --- | --- |
| 🎯 **목적** | 모든 정점을 연결하는 **최소 스패닝 트리** 구함 | 한 정점에서 모든 정점까지의 **최단 거리** 구함 |
| 🏗️ **큐 내용** | 다음 간선의 **비용** | 시작점부터 누적된 **거리** |
| 🔁 **방문 방식** | 방문한 노드는 다시 처리하지 않음 | 더 짧은 경로 발견 시 방문한 노드도 다시 처리 |
| 🚫 **사이클** | 사이클이 있으면 안 됨 | 사이클 있어도 상관없음 |
| 🕒 **복잡도** | O(E \log V) | O(E \log V) |

### ❌ 잘못된 사용 예

Prim은 MST 구하는 문제에 쓰며, 단일 비용만 사용

그러나 dikstra는 누적비용을 큐에 넣음.

### ✅ 올바른 적용

| **🌱 Prim** | **🛣️ 다익스트라** |
| --- | --- |
| “방문하지 않은 노드 중 가장 작은 간선” 선택 | “가장 짧은 누적 거리” 선택 |
| 방문 체크 후 큐에 간선 삽입 | 더 짧은 경로 발견 시 큐에 다시 삽입 가능 |

---

## 3. 잘못된 Prim사용법

Prim은 MST를 만드는 대표적인 알고리즘인데, 잘못사용하면 비효율을 초래할 수 있다.

### ❌ 잘못된 사용1: 모든 정점에서 Prim 실행

```java
for (int i = 1; i <= V; i++) {
    Arrays.fill(visited, false);
    result = Math.min(result, prim(i));
}
```

- MST는 임의의 한 정점에서 시작해도 항상 동일한 결과 나옴
- 그런데 모든 정점에서 Prim을 돌리니 불필요한 연산 발생
- 특히 V = 10,000일 때 메모리 초과 및 시간 초과 유발

### ❌ 잘못된 사용2: 불필요한 누적 비용

```java
for (Node nextNode : tree.get(now.next)) {
    if (!visited[nextNode.next]) {
        queue.offer(new Node(nextNode.next, totalCost + nextNode.cost));
    }
}
```

- 누적 비용을 큐에 넣으면 큐의 우선순위가 꼬임
- 큐에 실제 간선 비용만 넣어야함

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static class Node {
            int next;
            int cost;
    
            Node(int next, int cost) {
                this.next = next;
                this.cost = cost;
            }
    
            @Override
            public String toString() {
                return "Node{next: " + next + ", cost: " + cost + "}";
            }
        }
    
        static List<List<Node>> tree = new ArrayList<>();
        static boolean[] visited;
        static int result = Integer.MAX_VALUE;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
            visited = new boolean[V + 1];
    
            for (int i = 0; i <= V; i++) {
                tree.add(new ArrayList<>());
            }
    
            for (int i = 0; i < E; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                        cost = Integer.parseInt(st.nextToken());
    
                tree.get(start).add(new Node(end, cost));
                tree.get(end).add(new Node(start, cost));
            }
    
            for (int i = 1; i <= V; i++) {
                Arrays.fill(visited, false);
                result = Math.min(result, prim(i));
            }
    
            System.out.println(result);
        }
    
        private static int prim(int start) {
            Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
            queue.offer(new Node(start, 0));
    
            int totalCost = 0;
    
            while (!queue.isEmpty()) {
                Node now = queue.poll();
                if (visited[now.next]) continue;
                visited[now.next] = true;
                totalCost += now.cost;
    
                for (Node nextNode : tree.get(now.next)) {
                    if (!visited[nextNode.next]) {
                        queue.offer(new Node(nextNode.next, totalCost + nextNode.cost));
                    }
                }
            }
            return totalCost;
        }
    }
    
    ```
