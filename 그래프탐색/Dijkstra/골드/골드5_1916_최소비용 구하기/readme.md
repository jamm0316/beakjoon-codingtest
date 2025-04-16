page link : [https://www.acmicpc.net/problem/1916](https://www.acmicpc.net/problem/1916)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 그래프에서 M에 도달하는 최소비용

---

- 성공한 풀이전략
    1. `Node` 클래스 정의 (도착 도시, 비용 정보 포함)
        1. 각 간선을 표현할 수 있도록 `Node(int nextCity, int cost)` 이너 클래스 정의
    2. `PriorityQueue`를 이용한 최소 비용 우선 탐색
        1. `PriorityQueue`는 `a.cost - b.cost`로 비용이 가장 낮은 노드부터 탐색되도록 설정
    3. `dist[]` 배열을 사용하여 시작점부터 각 도시까지 최소 비용 저장
        1. 초기에는 모든 도시까지의 비용을 `MAX_VALUE`로 지정
        2. 시작 도시만 `0`으로 초기화
        3. 이후 우선훈위 큐를 통해 꺼낸 도시의 인접도시 확인
            1. `dist[]`의 비용보다 현재 비용이 더 크다면 `continue`로 스킵
            2. 기존 비용보다 저렴한 경로가 있다면 `dist[]` 값을 갱신 후 큐 갱신
    4. 탐색 종료 후 `dist[end]` 값 출력

- 실패한 풀이전략-1 (DFS, 시간초과)
    1. 시작도시에서 갈 수있는 도시만큼 순회
        1. 이때, `boolean[] visited` 로 방문한 곳은 다시 방문하지 않는다.
        2. curCity가 end라면 curCost를 출력한다.
        3. 아니라면 curCity에서 방문할 수 있는 도시를 순회
            1. `nextCity`에 방문한적 없다면 재귀를 호출하고 재귀가 `return` 되면 다음 순번을 위해 `visited[nextCity]`를 `false`로 해제해준다.

- 실패한 풀이전략-2 (BFS, 메모리초과)
    1. `Queue`에 현재 비용을 함께 입력하여 상태관리를 해준다.

## 🎨 사용된 알고리즘
Dijkstra(다익스트라) 알고리즘

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int nextCity, cost;

        Node(int nextCity, int cost) {
            this.nextCity = nextCity;
            this.cost = cost;
        }
    }
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  //도시 갯수
        int M = Integer.parseInt(br.readLine());  //버스 노선 수

        List<List<Node>> busGraph = new ArrayList<>(N + 1);

        //graph 초기화
        for (int i = 0; i <= N; i++) {
            busGraph.add(new ArrayList<>());
        }

        //graph 값 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            busGraph.get(startNode).add(new Node(endNode, cost));
        }

        //시, 종착점 초기화
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

				//dist 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

				//dijkstra 구현(PriorityQueue)
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.cost > dist[cur.nextCity]) continue;  //현재비용이 더 크면 skip

            for (Node next : busGraph.get(cur.nextCity)) {
                if (dist[next.nextCity] > dist[cur.nextCity] + next.cost) {
                    dist[next.nextCity] = dist[cur.nextCity] + next.cost;
                    queue.offer(new Node(next.nextCity, dist[next.nextCity]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}

```

# 🪄 해결한 오류

## 1. DFS/BFS의 시간 초과, 메모리 초과와 Dijkstra로 해결

💥 **핵심 문제점**

- DFS/BFS는 모든 지역을 다 탐색하여 최소비용을 구하므로,
도시 수가 N = 1,000, 버스 수 M = 100,000일 때, 경우의 수가 기하급수적으로 증가해 재귀 호출이 스택 오버플로우 혹은 시간초과 발생

**🔧 DFS 구현 방식**

- 시작 도시에서 도착 도시까지 갈 수 있는 **모든 경로를 재귀 탐색**
- 매번 이동할 수 있는 도시로 **재귀호출하며 누적 비용을 더함**
- `visited[]` 배열로 순환을 방지했지만, **모든 가능한 경로를 일일이 탐색**
    
    **기존코드**
    
    ```java
    //시작점에서 갈 수 있는 모든 경로 탐색
    for (int i = 0; i < busGraph.get(start).size(); i++) {
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        int nextCity = busGraph.get(start).get(i);
        if (!visited[nextCity]) {
            int cost = costGraph.get(start).get(i);
            //재귀 호출로 종착점에 도달하는 경로 모두 탐색
            calculateRecursive(nextCity, cost, visited);
        }
    }
    ```
    

**🔧 BFS 구현 방식**

- 일반적인 최단 거리 탐색 문제처럼 Queue를 사용해서 풀었고, 
`queue.offer(new int[](도시번호, 누적비용))` 형태로 큐에 추가
- 도착 도시에 도달하면 최소비용 갱신하거나 출력
    
    **기존코드**
    
    ```java
    Queue<int[]> queue = new LinkedList<>();
    queue.offer(new int[]{start, 0});;
    while (!queue.isEmpty()) {
        int[] info = queue.poll();
        int curCity = info[0], curCost = info[1];
    
        //마지막 도시에 도달 시 최소 비용인지 검증
        if (curCity == end) {
            minCost = Math.min(minCost, curCost);
        }
    
        for (int i = 0; i < busGraph.get(curCity).size(); i++) {
            int nextCity = busGraph.get(curCity).get(i);
            int nextCost = costGraph.get(curCity).get(i);
            queue.offer(new int[]{nextCity, curCost + nextCost});
        }
    }
    ```
    

### ✅ Dijkstra(다익스트라) 알고리즘으로 해결

**🔧 개선 방식**

- PrioriryQueue를 사용해서 **가장 비용이 적은 경로부터 먼저 탐색**
- 각 노드에 대한 **현재까지 최단 거리보다 더 짧은 경로만 갱신**
- 방문 처리 하지 않고, **더 좋은 경로가 발견되면 다시 탐색 가능**
    
    **개선된 코드**
    
    ```java
    PriorityQueue<Node> pq = new PriorityQueue<>();
    pq.offer(new Node(start, 0));
    dist[start] = 0;
    
    while (!pq.isEmpty()) {
        Node now = pq.poll();
    
        if (now.cost > dist[now.to]) continue;
    
        for (Node next : graph.get(now.to)) {
            if (dist[next.to] > now.cost + next.cost) {
                dist[next.to] = now.cost + next.cost;
                pq.offer(new Node(next.to, dist[next.to]));
            }
        }
    }
    ```
    

## 2. Dijkstra 알고리즘 개념

> Dijkstra 알고리즘은
> **가중치가 양수인 그래프에서, 시작 정점으로부터 다른 모든 정점까지의 최단 경로를 구하는 알고리즘**

### **📌 핵심 특징**

- 가중치가 **0 이상일 때만 사용 가능** (음수 있으면 사용 불가)
- **최단 거리부터 탐색**하기 위해 PriorityQueue (우선순위 큐)를 사용
- **모든 간선의 가중치가 다를 수 있는 상황**에서 최적의 해법

### **📌 시간 복잡도**

- 일반 구현: O(V²)
- PriorityQueue 사용 시: O(E log V)

### **📌 사용 예**

- 도시 간 최소 비용 구하기
- 지도 앱에서 가장 빠른 길 찾기
- 배송 경로 중 가장 저렴한 루트 탐색 등

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N;
        static int M;
        static StringTokenizer st;
        static List<List<Integer>> busGraph;
        static List<List<Integer>> costGraph;
        static int start;
        static int end;
        static int minCost = Integer.MAX_VALUE;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());  //도시 갯수
            M = Integer.parseInt(br.readLine());  //버스 노선 수
            busGraph = new ArrayList<>(N + 1);
            costGraph = new ArrayList<>(N + 1);
    
            //graph 초기화
            for (int i = 0; i <= N; i++) {
                busGraph.add(new ArrayList<>());
                costGraph.add(new ArrayList<>());
            }
    
            //graph 값 받기
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int startNode = Integer.parseInt(st.nextToken());
                int endNode = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
    
                busGraph.get(startNode).add(endNode);
                costGraph.get(startNode).add(cost);
            }
    
            //시, 종착점 초기화
            st = new StringTokenizer(br.readLine());
            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());
    
            //graph 탐색
            for (int i = 0; i < busGraph.get(start).size(); i++) {
                boolean[] visited = new boolean[N + 1];
                visited[start] = true;
                int nextCity = busGraph.get(start).get(i);
                if (!visited[nextCity]) {
                    int cost = costGraph.get(start).get(i);
                    calculateRecursive(nextCity, cost, visited);
                }
            }
    
            System.out.println(minCost);
        }
    
        private static void calculateRecursive(int curCity, int curCost, boolean[] visited) {
            if (curCity == end) {
                minCost = Math.min(minCost, curCost);
                return;
            }
    
            for (int i = 0; i < busGraph.get(curCity).size(); i++){
                int nextCity = busGraph.get(curCity).get(i);
                if (!visited[nextCity]) {
                    visited[nextCity] = true;
                    int nextCost = costGraph.get(curCity).get(i);
                    calculateRecursive(nextCity, curCost + nextCost, visited);
                    visited[nextCity] = false;
                }
            }
        }
    
        private static void calculateQueue(int start, int end) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{start, 0});
    
            while (!queue.isEmpty()) {
                int[] info = queue.poll();
                int curCity = info[0], curCost = info[1];
    
                //마지막 도시에 도달 시 최소 비용인지 검증
                if (curCity == end) {
                    minCost = Math.min(minCost, curCost);
                }
    
                for (int i = 0; i < busGraph.get(curCity).size(); i++) {
                    int nextCity = busGraph.get(curCity).get(i);
                    int nextCost = costGraph.get(curCity).get(i);
                    queue.offer(new int[]{nextCity, curCost + nextCost});
                }
            }
        }
    }
    
    ```
