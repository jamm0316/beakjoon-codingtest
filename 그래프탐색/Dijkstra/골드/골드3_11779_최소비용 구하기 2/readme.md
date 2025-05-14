page link : [https://www.acmicpc.net/problem/11779](https://www.acmicpc.net/problem/11779)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 시작점 부터 끝점까지 최소 비용 및 경로
- 풀이전략
    1. 다익스트라를 구현하여 시작점 부터 끝점까지의 최소 비용을 구한다.
    2. 이때, prev[]을 이용해 각 노드의 최단 경로의 바로 직전 경로를 저장한다.
    3. 다익스트라의 dp배열을 최신화 할때 prev도 최신화 하여 출력한다.

## 🎨 사용된 알고리즘
다익스트라, 역추적

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int end, cost;
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M;
    static List<List<Node>> graph;
    static int[] dist, prev;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end   = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        prev = new int[N + 1];
        Arrays.fill(dist, INF);

        dijkstra(start);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append('\n');

        LinkedList<Integer> path = new LinkedList<>();
        for (int at = end; at != 0; at = prev[at]) {
            path.addFirst(at);
        }

        sb.append(path.size()).append('\n');
        for (int city : path) {
            sb.append(city).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.end] < now.cost) continue;

            for (Node next : graph.get(now.end)) {
                int newCost = dist[now.end] + next.cost;
                if (dist[next.end] > newCost) {
                    dist[next.end] = newCost;
                    prev[next.end] = now.end;
                    pq.offer(new Node(next.end, newCost));
                }
            }
        }
    }
}
```

# 🪄 해결한 오류

## 1. 역추적(prev[] 배열 사용) 개념

**`prev[]`:** 각 정점에 대해 최단 경로 상 직전 정점을 저장해 둔 배열

- **역할:** “경로 복원” 중심. 다익스트라, BFS 종료 후 **과거 기록을 따라가며 경로 복원**
- **사용 예시:** 최단경로 알고리즘, 동적 계획법에서 **최적 경로 재구성** 시 사용
- **세부 설명**
    - **다익스트라가 어떤 정점 v를 더 짧은 비용으로 갱신할 때**
        
        prev[v] = u (여기서 u는 v를 갱신해 준 “바로 직전” 정점)을 기록한다.
        
    - 알고리즘이 끝나면 **start → … → v 최단 경로에서 v 직전 정점이 prev[v]** 로 확정돼 있다.
        
        → 결국 end에서 prev 체인을 거꾸로 따라가면 출발점까지 되돌아가는 길을 복원할 수 있다.
        

## 2. **센티널(Sentinel)이란?**

- 센티널(sentinel)은 알고리즘에서 **특별한 종료 조건을 나타내는 값**으로 자주 쓰이는 개념.

말 그대로 “감시병”, “종료 신호”처럼 동작.

### **예시**

- 배열 끝을 나타내기 위해 -1, 0, null 같은 값 사용
- 연결 리스트의 null 포인터
- 다익스트라의 prev[]에서 0 사용 → “**이 정점은 시작점이다**”를 뜻함

즉, **센티널은 일반 데이터와 구분되는 ‘경계자’ 역할**을 하며,

**루프 종료 조건**이나 **초기 상태 표시**에 자주 쓰임.

### 센티널 사용 예시

**기존코드**

```java
for (int at = end; at != start; at = prev[at]) {
    path.addFirst(at);
}
path.addFirst(start); // 따로 넣어야 함
```

**수정된 코드**

```java
for (int at = end; at != 0; at = prev[at]) {  //0이 센티널 역할
    path.addFirst(at);
}
```
