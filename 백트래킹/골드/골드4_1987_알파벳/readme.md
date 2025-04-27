page link : [https://www.acmicpc.net/problem/1987](https://www.acmicpc.net/problem/1987)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 말이 이동할 수 있는 최대 값

- 풀이전략
    1. DFS를 이용해 최대 경로를 탐색한다.
        1. DFS에 각 경로마다 방문한 알파벳을 개별로 상태관리한다.
            1. `boolean[26]`으로 관리
        2. 방문한 수가 가장 많은 숫자로 출력한다.

## 🎨 사용된 알고리즘
DFS, 백트래킹

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        StringBuilder sb = new StringBuilder();
        int x;
        int y;
        int dist;

        Node(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public String toString() {
            sb.append("Node{pos: [").append(x).append(", ").append(y)
                    .append("], dist: ").append(dist).append("}").append('\n')
                    .append(visited);
            return sb.toString();
        }
    }

    static int R, C;
    static char[][] graph;
    static boolean[] visited = new boolean[26];
    static int maxDist = 0;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new char[R][C];
        for (int i = 0; i < R; i++) {
            graph[i] = br.readLine().toCharArray();
        }

        //DFS탐색
        visited[graph[0][0] - 'A'] = true;
        recursive(new Node(0, 0, 1));

        System.out.println(maxDist);
    }

    private static void recursive(Node now) {
        maxDist = Math.max(maxDist, now.dist);

        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];

            if (0 <= nx && nx < R && 0 <= ny && ny < C) {
                int idx = graph[nx][ny] - 'A';
                if (!visited[idx]) {
                    visited[idx] = true;
                    recursive(new Node(nx, ny, now.dist + 1));
                    visited[idx] = false;
                }
            }
        }
    }
}
```

# 🪄 해결한 오류

## 1. BFS가 아닌 DFS로 풀이해야하는 이유

**모든 경로를 끝까지 탐색해 최대 거리를 찾기 위해**

→ BFS는 최단거리를 찾을 때 유리하지만, 이 문제는 최대 거리가 목표다.

따라서, 한 경로를 끝까지 깊게 탐색하는 DFS가 적합하다.

## 2. 알파벳은 26자인 것을 적극 이용

기존 코드에서는 Set<Character>를 이용해 매번 복사하고 추가하는 방식으로 탐색했다.

이 경우 재귀 호출마다 Set을 복사하는 비용이 O(k)씩 발생한다.(방문한 알파벳 수만큼)

**기존코드**

```java
 if (0 <= nx && nx < R && 0 <= ny && ny < C &&
        !now.visited.contains(graph[nx][ny])) {
    Set<Character> newVisited = new HashSet<>(now.visited);
    newVisited.add(graph[nx][ny]);
    recursive(new Node(nx, ny, now.dist + 1, newVisited));
}
```

이를 해결하기 위해, 알파벳이 26자라는 점을 활용하여 `boolean[26]` 배열로 방문 여부를 전역관리 한다.

DFS 과정에서 백트래킹을 활용하여,

- 이동할 떄는 `visited[idx] = true`
- 탐색이 끝나고 돌아올 때는 `visited[idx] = false`

로 처리하여 복사 비용 없이 효율적으로 탐색할 수 있도록 최적화 했다.

**수정된 코드**

```java
if (0 <= nx && nx < R && 0 <= ny && ny < C) {
    int idx = graph[nx][ny] - 'A';
    if (!visited[idx]) {
        visited[idx] = true;
        recursive(new Node(nx, ny, now.dist + 1));
        visited[idx] = false;
    }
}
```

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static class Node {
            StringBuilder sb = new StringBuilder();
            int x;
            int y;
            int dist;
            Set<Character> visited;
    
            Node(int x, int y, int dist) {
                this.x = x;
                this.y = y;
                this.dist = dist;
            }
    
            Node(int x, int y, int dist, Set<Character> visited) {
                this.x = x;
                this.y = y;
                this.dist = dist;
                this.visited = visited;
            }
    
            @Override
            public String toString() {
                sb.append("Node{pos: [").append(x).append(", ").append(y)
                        .append("], dist: ").append(dist).append("}").append('\n')
                        .append(visited);
                return sb.toString();
            }
        }
    
        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, 1, -1};
        static int R;
        static int C;
        static char[][] graph;
        static int maxDist = 0;
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            R = Integer.parseInt(st.nextToken());
            C = Integer.parseInt(st.nextToken());
    
            //그래프 초기화
            graph = new char[R][C];
            for (int i = 0; i < R; i++) {
                String input = br.readLine();
                for (int j = 0; j < C; j++) {
                    graph[i][j] = input.charAt(j);
                }
            }
            //DFS탐색
            Set<Character> init = new HashSet<>();
            init.add(graph[0][0]);
            recursive(new Node(0, 0, 1, init));
    
            System.out.println(maxDist);
    
        private static void recursive(Node now) {
    
            if (maxDist < now.dist) {
                maxDist = now.dist;
            }
    
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < R && 0 <= ny && ny < C &&
                        !now.visited.contains(graph[nx][ny])) {
                    Set<Character> newVisited = new HashSet<>(now.visited);
                    newVisited.add(graph[nx][ny]);
                    recursive(new Node(nx, ny, now.dist + 1, newVisited));
                }
            }
        }
    }
    
    ```
