page link : [https://www.acmicpc.net/problem/11724](https://www.acmicpc.net/problem/11724)

---

# 풀이전략

1. 모든 그래프를 양방향으로 연결한다.
2. dfs를 이용해 재귀가 모두 끝날때마다 count++ 를 실행한다.
    a. 이 때, visited를 이용하여 한번 방문한 곳은 재방문 하지 않는다.

## 사용된 알고리즘

dfs(깊이 우선 탐색), bfs(너비 우선 탐색)

---

# code

## Java(DFS)

```java
import java.util.*;
import java.io.*;

public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        int links = 0;

        // 그래프와 방문 배열 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // dfs
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visitConnectedComponentUsingDfs(i);
                links++;
            }
        }

        System.out.println(links);
    }

    private static void visitConnectedComponentUsingDfs(int start) {
        visited[start] = true;
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                visitConnectedComponentUsingDfs(neighbor);
            }
        }
    }
}

```

## Java(BFS)

```java
import java.util.*;
import java.io.*;

public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        int links = 0;

        // 그래프와 방문 배열 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // bfs
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visitConnectedComponentUsingBfs(i);
                links++;
            }
        }

        System.out.println(links);
    }

    private static void visitConnectedComponentUsingBfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int neighbor : graph.get(current)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
    }
}
```

## 해결한 오류

### 1. ArrayList 초기 용량 설정의 장점

배열의 크기가 명확하다면 `new ArrayList<>(N + 1);` 과 같이 초기용량 설정을 해주는 것이 메모리 관리 차원에서 유리하다.

| 항목 | `new ArrayList<>(N + 1)` | `new ArrayList<>()` |
| --- | --- | --- |
| 초기 용량 | 명시적으로 잡힘 (내부 배열이 N+1짜리로 시작됨) | 기본 용량 10부터 시작 |
| 용량 초과 시 | 추가 할당 안 해도 됨 (충분한 크기 확보) | `add()` 하다가 넘치면 내부 배열 복사 발생 (성능 저하) |
| 성능 | 초기 삽입이 빠름 | 크기 늘어날 때 느려질 수 있음 |

### 2. 원시배열과 래퍼 클래스의 리스트(객체 리스트) 차이

- **무조건 `boolean[]`이 빠르고 가볍다.**
- 방문 여부처럼 단순한 true/false 체크는 `boolean[]`이 정석.
- `List<Boolean>`은 진짜 불필요하게 무겁고, 속도도 느림.

| 항목 | `boolean[]` | `List<Boolean>` |
| --- | --- | --- |
| 타입 | **원시 타입 배열 (primitive array)** | **래퍼 객체 리스트 (wrapper object list)** |
| 메모리 사용 | 작다
(1bit에 가까운 단위로 저장됨) | 크다
(`Boolean` 객체가 heap에 따로 존재함) |
| 접근 속도 | 빠르다
(배열은 인덱스 접근이 단순함) | 상대적으로 느리다
(`get()` 호출, 내부 boxing/unboxing 발생) |
| 저장 시 처리 | 단순 저장 | 자동 박싱(Auto-boxing) 필요 |
| 일반 용도 | 성능 위주 로직, 단순 플래그 처리 | List 인터페이스 기능이 필요할 때 (동적 크기 등) |

---

- 기존코드
    
    ```java
    import java.util.*;
    import java.io.*;
    
    public class Main {
        static List<List<Integer>> graph;
        static List<Boolean> visited;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());
            graph = new ArrayList<>();
            visited = new ArrayList<>();
            int links = 0;
    
            // initialize Array
            for (int i = 0; i <= N; i++) {
                graph.add(new ArrayList<>());
                visited.add(false);
            }
    
            // initialize Graph
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
    
                graph.get(start).add(end);
                graph.get(end).add(start);
            }
    
            // dfs
            for (int i = 1; i <= N; i++) {
                if (!visited.get(i)) {
                    visited.set(i, true);
                    findUnvisitedLinks(i);
                    links++;
                }
            }
    
            System.out.println(links);
        }
    
        private static void findUnvisitedLinks(int start) {
            for (int neighbor : graph.get(start)) {
                if (!visited.get(neighbor)) {
                    visited.set(neighbor, true);
                    findUnvisitedLinks(neighbor);
                }
            }
        }
    }
    ```
