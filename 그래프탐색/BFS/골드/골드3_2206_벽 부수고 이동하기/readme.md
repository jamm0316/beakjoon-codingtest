page link : [https://www.acmicpc.net/problem/2206](https://www.acmicpc.net/problem/2206)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 목적지까지 최단 경로

- 풀이전략
    - BFS를 사용하여 최단경로 구하기
    - `boolean visited[N][M][2]` 3차원으로 구성
        - `[N][M]` 은 좌표 역할
        - `[2]` 는 벽을 부수고 도달했는지 안부수고 도달했는지 확인하는 역할
            
            → 같은 좌표라도 벽을 부수고 도달했을 때와 안부수고 도달했을 때 서로 다른 경우의 수로 인식해야하기 때문
            
    - BFS에 포함할 요소
        - 현재 위치, 벽 부수기 사용 여부

## 🎨 사용된 알고리즘
BFS, 3차원 상태관리(좌표, 상태)

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] graph;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class Node {
        int x;
        int y;
        int count;
        boolean usedCrush;

        Node(int x, int y, int count, boolean usedCrush) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.usedCrush = usedCrush;
        }

        @Override
        public String toString() {
            return "Node{x: " + x + ", y: " + y + ", count: " + count + ", usedCrush: " + usedCrush + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        graph = new int[N][M];
        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
        }

        int maxCount = 0;
        Queue<Node> queue = new LinkedList<>();
        visited = new boolean[N][M][2];  //x, y, [0 or 1] => 0: 아직 안부심, 1: 이미 부심
        queue.offer(new Node(0, 0, 1, false));
        visited[0][0][0] = true;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (now.x == N - 1 && now.y == M - 1) {
                System.out.println(now.count);
                return;
            }

            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < M) {
                    if (graph[nx][ny] == 0) {
                        if (!now.usedCrush && !visited[nx][ny][0]) {
                            visited[nx][ny][0] = true;
                            queue.offer(new Node(nx, ny, now.count + 1, false));
                        } else if (now.usedCrush && !visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            queue.offer(new Node(nx, ny, now.count + 1, true));
                        }
                    } else if (graph[nx][ny] == 1 && !now.usedCrush) {
                        if (!visited[nx][ny][1]) {
                            visited[nx][ny][1] = true;
                            queue.offer(new Node(nx, ny, now.count + 1, true));
                        }
                    }
                }
            }
        }
        System.out.println(-1);
    }
}

```

# 🪄 해결한 오류

## 1. **벽 부수고 이동하기에서 `전체 상태관리`와 `현재 상태관리`의 혼동**

### **❓ 문제 상황**

> 백준 2206번 문제를 BFS로 풀이하려 했지만, 추가 테스트 케이스에서 틀림.
처음에는 visited를 2차원 배열로 관리하고, 벽을 부쉈는지 여부는 Node 클래스의 usedCrush 필드만으로 판단하려 했다.
이 경우 전체 경우의수를 고려하지 못하는 현상이 발생했다.
> 

---

### **⚠️ 원인 분석**

1. **visited의 의미 오해**
    - `visited[x][y] = true`만 사용할 경우,
        
        벽을 **부수고 온 경로**와 **부수지 않고 온 경로**를 **동일하게 처리**하게 된다.
        
    - 하지만 두 상태는 이후 경로 선택지에 큰 영향을 주므로,
        
        반드시 **구분하여 방문 여부를 관리해야 한다**.
        
2. **DFS 백트래킹 사용법과 BFS의 혼동**
    - 처음에는 `visited[nx][ny] = false`로 상태를 풀어주고, 탐색하는 **백트래킹 방식**을 떠올렸다.
    - 이 방식은 **모든 경로를 탐색할 때 유효한 DFS 전략**이며,
        
        **BFS에서는 되돌림 없이 최단 경로만을 보장해야 하므로 사용하면 안 된다.**
        

---

### **✅ 해결 방법**

| **개념** | **설명** |
| --- | --- |
| `Node.usedCrush` | 현재 Node가 벽을 부쉈는지 여부를 나타내는 상태값 |
| `visited[x][y][0 or 1]` | 해당 좌표를 벽을 **부수지 않고(0)**, 혹은 **부수고(1)** 방문했는지 여부 |
- (x, y) 위치를 방문할 때, **벽을 부쉈는지 여부에 따라 별도의 방문 처리**가 필요하다.
    - `visited[x][y][0]`: 벽 안 부수고 도달
    - `visited[x][y][1]`: 벽 부수고 도달
- 따라서 같은 좌표라도 `usedCrush == false`인 경로와 `true`인 경로는 **엄연히 다른 상태**이며, 
**두 번 방문이 모두 허용되어야** 한다.

---

### **🔧 수정된 코드**

```java
if (graph[nx][ny] == 0) {
    if (!now.usedCrush && !visited[nx][ny][0]) {
        visited[nx][ny][0] = true;
        queue.offer(new Node(nx, ny, now.count + 1, false));
    } else if (now.usedCrush && !visited[nx][ny][1]) {
        visited[nx][ny][1] = true;
        queue.offer(new Node(nx, ny, now.count + 1, true));
    }
}
```

---

### **🧠 교훈**

- BFS에서의 visited는 단순히 좌표 방문 여부를 저장하는 것이 아니라,
    - **특정 상태(state)로 해당 좌표를 방문했는지 여부**를 함께 저장해야 한다.
- DFS에서는 백트래킹으로 상태를 되돌려 모든 경로를 탐색할 수 있지만,
    
    BFS는 최단 거리 보장을 위해 **한 번 방문한 상태는 다시 방문하지 않도록 관리**해야 한다.
    
- 문제 조건에 따라 벽을 부순 횟수, 열쇠 보유 여부 등으로 상태가 나뉘는 경우,
    
    `visited[x][y][state]`처럼 상태 기반의 `visited` 구조를 확장하여 관리할 수 있다.
    

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N, M;
        static int[][] graph;
        static boolean[][] visited;
        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, 1, -1};
    
        static class Node {
            int x;
            int y;
            int count;
            boolean usedCrush;
    
            Node(int x, int y, int count, boolean usedCrush) {
                this.x = x;
                this.y = y;
                this.count = count;
                this.usedCrush = usedCrush;
            }
    
            @Override
            public String toString() {
                return "Node{x: " + x + ", y: " + y + ", count: " + count + ", usedCrush: " + usedCrush + "}";
            }
        }
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
    
            graph = new int[N][M];
            for (int i = 0; i < N; i++) {
                graph[i] = Arrays.stream(br.readLine().split("")).mapToInt(Integer::parseInt).toArray();
            }
    
            int maxCount = 0;
            Queue<Node> queue = new LinkedList<>();
            visited = new boolean[N][M];
            queue.offer(new Node(0, 0, 1, false));
            visited[0][0] = true;
            boolean isGoal = false;
            while (!queue.isEmpty()) {
                Node now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
    
                    if (0 <= nx && nx < N && 0 <= ny && ny < M
                            && !visited[nx][ny]) {
                        if (graph[nx][ny] == 1) {
                            if (!now.usedCrush) {
                                visited[nx][ny] = true;
                                queue.offer(new Node(nx, ny, now.count + 1, true));
                            } else {
                                continue;
                            }
                        } else if (graph[nx][ny] == 0) {
                            visited[nx][ny] = true;
                            queue.offer(new Node(nx, ny, now.count + 1, now.usedCrush));
                        }
                    }
                    maxCount = Math.max(maxCount, now.count);
                }
                if (now.x == N - 1 && now.y == M - 1) {
                    isGoal = true;
                }
            }
            System.out.println(isGoal ? maxCount : -1);
        }
    }
    
    ```
