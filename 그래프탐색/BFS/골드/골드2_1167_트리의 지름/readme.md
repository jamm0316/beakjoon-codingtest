page link : [https://www.acmicpc.net/problem/1167](https://www.acmicpc.net/problem/1167)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 트리의 지름

- 성공한 풀이 전략
    1. BFS를 2번 사용한다.
        1. 트리는 사이클이 없고, 모든 노드가 연결되어 있으므로 BFS로 가장 먼 거리의 경로 찾을 수 있음.
        2. 트리의 가장 먼거리의 node에서 다시 가장 먼거리를 탐색하면 트리의 지름을 구할 수 있음.

## 🎨 사용된 알고리즘
BFS, 그래프 탐색

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int end;
        int dist;

        Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        public String toString() {
            return "Node{next: " + end + ", dist: " + dist + "}";
        }
    }

    static int N;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Node(to, dist));
            }
        }
        Node farNode = bfs(1);
        Node result = bfs(farNode.end);
        System.out.println(result.dist);
    }

    private static Node bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dp = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node next : graph.get(now)) {
                if (!visited[next.end]) {
                    visited[next.end] = true;
                    dp[next.end] = dp[now] + next.dist;
                    queue.offer(next.end);
                }
            }
        }

        int maxDist = 0;
        int maxNode = start;
        for (int i = 0; i <= N; i++) {
            if (dp[i] > maxDist) {
                maxDist = dp[i];
                maxNode = i;
            }
        }
        return new Node(maxNode, maxDist);
    }
}

```

# 🪄 해결한 오류

## 1. 알고리즘 별 사용 조건 정리

### **가중치가 있다**

**1-1. 일반 그래프**

- **다익스트라**: 가중치가 **모두 양수일 때**
- **벨만-포드**: 가중치에 **음수가 포함**될 수 있을 때
- **플로이드-워셜**: **모든 쌍 최단 거리** 구할 때 (보통 V ≤ 400 제한)

**1-2. 트리**

- **DFS or BFS**:
    
    경로가 유일하므로, 가중치가 있어도 **최장/최단 거리 모두 DFS/BFS**로 가능
    

---

### **가중치가 없다**

- **BFS**: 최단 거리 구할 때 유리 (간선 비용이 모두 1이므로 계층 순회)
- **DFS**: 모든 경로 탐색할 때 유리 (ex. 백트래킹, 사이클 탐지 등)

---

### **요약 표로 정리**

| **조건** | **추천 알고리즘** | **이유** |
| --- | --- | --- |
| 가중치 있음 + 일반 그래프 + 양수 | 다익스트라 | 최단 거리, 효율적 |
| 가중치 있음 + 일반 그래프 + 음수 포함 | 벨만-포드 | 음수 감당 가능 |
| 가중치 있음 + 모든 쌍 거리 | 플로이드-워셜 | 모든 정점 간 계산 |
| 가중치 있음 + 트리 | BFS / DFS | 경로가 유일 → 단순 순회 |
| 가중치 없음 | BFS / DFS | 간선 비용 1이면 BFS 최적, DFS는 경로 탐색 |

## 2. 입력방식 개선

| 입력방식 | 메모리 | 시간 |
| --- | --- | --- |
| **StringTokenizer** | 105,272 KB | 756 ms |
| **Array.stream(br.readLine().split(” “))** | 131,456 KB | 856 ms |

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static class Node {
            int end;
            int dist;
    
            Node(int end, int dist) {
                this.end = end;
                this.dist = dist;
            }
    
            public String toString() {
                return "Node{next: " + end + ", dist: " + dist + "}";
            }
        }
    
        static int N;
        static List<List<Node>> graph = new ArrayList<>();
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
    
            for (int i = 0; i < N; i++) {
                int[] element = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
                int start = element[0];
                for (int j = 1; j < element.length - 1; j++) {
                    if (j % 2 != 0) {  //next, dist 입력
                        graph.get(start).add(new Node(element[j], element[j + 1]));
                    }
                }
            }
            Node farNode = bfs(1);
            Node result = bfs(farNode.end);
            System.out.println(result.dist);
        }
    
        private static Node bfs(int start) {
            boolean[] visited = new boolean[N + 1];
            int[] dp = new int[N + 1];
            Queue<Integer> queue = new LinkedList<>();
    
            queue.offer(start);
            visited[start] = true;
    
            while (!queue.isEmpty()) {
                int now = queue.poll();
                for (Node next : graph.get(now)) {
                    if (!visited[next.end]) {
                        visited[next.end] = true;
                        dp[next.end] = dp[now] + next.dist;
                        queue.offer(next.end);
                    }
                }
            }
    
            int maxDist = 0;
            int maxNode = start;
            for (int i = 0; i <= N; i++) {
                if (dp[i] > maxDist) {
                    maxDist = dp[i];
                    maxNode = i;
                }
            }
            return new Node(maxNode, maxDist);
        }
    }
    
    ```
