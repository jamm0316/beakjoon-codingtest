page link : [https://www.acmicpc.net/problem/1865](https://www.acmicpc.net/problem/1865)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 시간이 처음보다 돌아갔으면 YES, 아니면 NO 출력
- 풀이전략
    1. 입력값 정의
        1. TC: 테스트 케이스 갯수
        2. N: 지점수, M: 도로갯수, W: 웜홀갯수
        3. S: 시작점, E: 도착점, T:걸리는 시간
    2. 도로는 양방향, 웜홀은 단방향 그래프로 받는다.
    3. 벨만-포드 활용
        1. 거리 배열 dist를 만들고 N - 1 번 모든 간선을 완화
        2. 마지막 N번째 루프에서 거리 갱신이 일어나면 → 음수 사이클 존재
    4. bellmanFord() < 0 ? “YES” : “NO”
- 실패한 풀이전략
    1. 다익스트라로 시작점 부터 각 지점까지 도달하는 최소 비용을 구한다.

## 🎨 사용된 알고리즘
벨만 포드

---

# 🧑🏻‍💻 code

## :Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class City {
        int start, end, time;
        City(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

    static int TC, N, M, W;
    static List<City> cities;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            cities = new ArrayList<>();

            // 도로: 양방향
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                cities.add(new City(start, end, time));
                cities.add(new City(end, start, time));
            }

            // 웜홀: 단방향, 음수 간선
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                cities.add(new City(start, end, -time));
            }

            // 가상 시작점 0번에서 모든 노드로 0 비용 간선 추가
            for (int i = 1; i <= N; i++) {
                cities.add(new City(0, i, 0));
            }

            sb.append(bellmanFord(N + 1) ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    static boolean bellmanFord(int totalNodes) {
        int[] dist = new int[totalNodes];
        Arrays.fill(dist, INF);
        dist[0] = 0; // 가상 시작점

        // N번 반복 (0 포함해서 totalNodes - 1)
        for (int i = 1; i < totalNodes; i++) {
            for (City city : cities) {
                if (dist[city.start] != INF && dist[city.end] > dist[city.start] + city.time) {
                    dist[city.end] = dist[city.start] + city.time;
                }
            }
        }

        // N+1번째에 갱신 발생 시 → 음수 사이클 존재
        for (City edge : cities) {
            if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.time) {
                return true;
            }
        }

        return false;
    }
}
```

# 🪄 해결한 오류

## 1. 벨만-포드 알고리즘 활용

### **문제상황**

초기엔 단순히 최단 경로를 찾는 문제라고 판단해, 다익스트라 알고리즘을 사용했음.

하지만 웜홀의 시간은 음수로 주어졌고, 출발점으로 돌아왔을 떄 시간이 되돌아가는 경우, 즉 음수 사이클의 존재 여부를 확인하는게 문제 핵심.

**다익스트라 실패 이유**

- 다익스트라는 음수 가중치 간선이 포함된 그래프에서 사용불가
- 이유: 우선순위 큐 기반으로 가장 가까운 노드부터 확정하는 방식, 음수 간선이 존재하면 이 전략이 깨짐 → 최단 경로 보장 불가
- 결정적으로, 음수 사이클 존재 여부를 판단할 수 있는 기능이 없음

### **해결: 밸만-포드 알고리즘 도입**

- 밸만-포드는 음수 간선 포함 가능, 음수 사이클 탐지 가능
- 핵심 아이디어: 모든 간선에 대해 N-1번 반복적으로 Relax(거리 갱신) 수행
- 이후 N번째에도 거리 갱신이 일어나면 → 음수 사이클 존재

**구현방법**

```java
// N번 반복 (0 포함해서 totalNodes - 1)
for (int i = 1; i < totalNodes; i++) {
    for (City city : cities) {
        if (dist[city.start] != INF && dist[city.end] > dist[city.start] + city.time) {
            dist[city.end] = dist[city.start] + city.time;
        }
    }
}

// N+1번째에 갱신 발생 시 → 음수 사이클 존재
for (City edge : cities) {
    if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.time) {
        return true;
    }
}

return false;
```

## 2. 모든 노드 탐색 → 시간초과 → 가상의 시작점(0번 노드) 도입

### 문제상황

밸만-포드로 교체하고 나서도 시간초과 발생

이유는 모든 정점 (start = 1 ~ N)을 시작점으로 하여 밸만-포드를 N번 설정했기 때문 → 시간복잡도 O(N^3) → 비효율적

### 해결아이디어

음수 사이클은 어떤 정점에서든 시작할 수 있다.

그럼 모든 정점을 한번에 연결할 수 있는 출발점을 만들어보자.

- 그래서 가상 노드 0번을 만들고, 모든 정점(1~N)으로 가는 비용 0의 간선을 추가함.
- 이러면 모든 정점이 탐색 범위에 포함되면서도, 밸만-포드를 1번만 실행해도 음수 사이클 검출 가능

**구현방식**

```java
// 가상 시작점 0번에서 모든 노드로 0 비용 간선 추가
for (int i = 1; i <= N; i++) {
    cities.add(new City(0, i, 0));
}
```

- dist[0] = 0으로 시작하여 밸만-포드 실행
- 시간복잡도는 O(N x E) 로 감소 → 시간초과 해결

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static class City {
            int end;
            int time;
    
            City(int end, int time) {
                this.end = end;
                this.time = time;
            }
    
            @Override
            public String toString() {
                return "City{end: " + end + ", time: " + time + "}";
            }
        }
    
        static int TC, N, M, W;
        static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        static StringTokenizer st;
        static List<List<City>> graph;
        static int INF = Integer.MAX_VALUE;
        public static void main(String[] args) throws IOException {
            StringBuilder sb = new StringBuilder();
    
            TC = Integer.parseInt(br.readLine());
            while (TC-- > 0) {
                st = new StringTokenizer(br.readLine());
                N = Integer.parseInt(st.nextToken());
                M = Integer.parseInt(st.nextToken());
                W = Integer.parseInt(st.nextToken());
    
                initializeGraph();
                int[] dist = dijkstra();
                sb.append(dist[1] < 0 ? "YES" : "NO").append('\n');
            }
            System.out.println(sb.toString());
        }
    
        private static int[] dijkstra() {
            boolean[] visited = new boolean[N + 1];
            visited[0] = true;
            int[] dist = new int[N + 1];
            Queue<City> queue = new PriorityQueue<>((a, b) -> a.time - b.time);
            queue.offer(new City(1, 0));
            Arrays.fill(dist, INF);
            dist[0] = 0;
            dist[1] = 0;
    
            while (!queue.isEmpty()) {
                City now = queue.poll();
                if (dist[now.end] < now.time) continue;
    
                for (City next : graph.get(now.end)) {
                    if (dist[next.end] > dist[now.end] + next.time) {
                        dist[next.end] = dist[now.end] + next.time;
                        queue.offer(new City(next.end, dist[next.end]));
                    }
                }
                
                //다시 돌아올 수 있게 수정
                if (now.time != 0) {
                    visited[now.end] = true;
                    int count = 1;
                    for (boolean each : visited) {
                        if (each) count++;
                    }
                    if (visited.length == count) {
                        break;
                    }
                }
            }
            return dist;
        }
    
        private static void initializeGraph() throws IOException {
            graph = new ArrayList<>();
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
            }
    
            //도로 초기화
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                        time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new City(end, time));
                graph.get(end).add(new City(start, time));
            }
    
            //웜홀 초기화
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                        time = Integer.parseInt(st.nextToken());
                graph.get(start).add(new City(end, -time));
            }
        }
    }
    
    ```
