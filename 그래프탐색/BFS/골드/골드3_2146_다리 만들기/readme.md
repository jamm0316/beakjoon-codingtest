page link : [https://www.acmicpc.net/problem/2146](https://www.acmicpc.net/problem/2146)

---
# 1차 성능 최적화 성과
<img width="432" height="232" alt="image" src="https://github.com/user-attachments/assets/5a56cb87-36b4-4416-a015-eb79ffb79fec" />
## ✈️ 14배 속도 개선(4초 → 0.2초)
## 🌱 3.6배 메모리 절약(304MB → 84MB)

# 2차 성능 최적화 성과
<img width="426" height="146" alt="image" src="https://github.com/user-attachments/assets/f9401420-94a6-4d53-8d38-a01b0ee83149" />
## 🌱 4배 메모리 절약(84MB → 21MB)


# 💡 풀이전략

- 구하고자 하는 값
    - 가장 짧은 다리 길이
- 풀이전략
    1. 모든 입력값을 받는다.
    2. visited를 이용해서 입력값을 갱신한다
        1. 가장 끝점 queue.offer하기
        2. 각 섬마다 flag달기
    3. 브루트 포스로 기존 섬 끝에 있는 모든 점을 탐색한다.
    4. BFS와 DP를 이용해 map[nx][ny]=0이면 count를 세고, 아니면 minDistance에 넣어둔다

## 🎨 사용된 알고리즘

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    private static final int SEA = 0;
    private static final int LAND = 1;
    private static final int[] DIRECTIONS_X = {-1, 1, 0, 0};
    private static final int[] DIRECTIONS_Y = {0, 0, -1, 1};
    private static int N, islandId;
    private static int minDist = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬 식별
        List<Set<Long>> islandEdges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == LAND) {
                    islandEdges.add(identifyIsland(i, j, ++islandId));
                }
            }
        }

        // 각 섬에서 BFS
        for (int i = 0; i < islandEdges.size(); i++) {
            bfsFromIsland(islandEdges.get(i), i + 1);
        }

        System.out.println(minDist);
    }

    private static Set<Long> identifyIsland(int x, int y, int currentId) {
        Set<Long> edges = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = currentId;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DIRECTIONS_X[i];
                int ny = now[1] + DIRECTIONS_Y[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == LAND) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        map[nx][ny] = currentId;
                    } else if (map[nx][ny] == SEA) {
                        edges.add((long) now[0] * N + now[1]);
                    }
                }
            }
        }
        return edges;
    }

    private static void bfsFromIsland(Set<Long> edges, int currentId) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][N];
        for (Long edge : edges) {
            int x = (int) (edge / N);
            int y = (int) (edge % N);
            queue.offer(new int[]{x, y, 0}); // {x, y, distance}
            visited[x][y] = true;
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], dist = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + DIRECTIONS_X[i];
                int ny = y + DIRECTIONS_Y[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == SEA) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    } else if (map[nx][ny] != currentId) {
                        minDist = Math.min(minDist, dist);
                        return; // 다른 섬에 도달하면 즉시 종료
                    }
                }
            }
        }
    }
}
```

# 🪄 1차 성능 최적화

## 1. edgeSet에 int[] (참조값)을 저장하여 중복값 제거 안됨

### 🔥 문제

- `HashSet<int[]>`는 주소값을 기준으로 비교하므로 중복 제거가 제대로 안 됨
    
    **→ 같은 좌표를 여러번 BFS 탐색할 수 있어 비효율 발생**
    
    **기존 코드**
    
    ```java
    private static void init(int x, int y, int flag) {
        ...
        while (!queue.isEmpty()) {
            ...
            for (int i = 0; i < 4; i++) {
                ...
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    ...
                    } else if (map[nx][ny] == 0) {
                        edgeSet.add(new int[]{now[0], now[1]});  // 참조값으로 저장
                    }
                }
            }
        }
    }
    ```
    

### 🧯 해결 방안

- `HashSet<int[]>` → `Set<Long>`으로 변경
    
    ```java
    edges.add((long) now[0] * N + now[1]);
    ```
    
    - `int[]` 대신 long으로 인덱스를 고유하게 매핑하여 **중복 제거 완벽히 해결**
    - 메모리 사용량도 적고 equals/hashCode 문제도 피할 수 있음.
    
    **수정된 코드**
    
    ```java
    // 섬 식별
    List<Set<Long>> islandEdges = new ArrayList<>();
    for (int i = 0; i < N; i++) {
        for (int j = 0; j < N; j++) {
            if (!visited[i][j] && map[i][j] == LAND) {
                islandEdges.add(identifyIsland(i, j, ++islandId));
            }
        }
    }
    
    private static Set<Long> identifyIsland(int x, int y, int currentId) {
        ...
        while (!queue.isEmpty()) {
            ...
            for (int i = 0; i < 4; i++) {
                ...
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    ...
                    } else if (map[nx][ny] == SEA) {
                        edges.add((long) now[0] * N + now[1]);  //고유값이므로 중복제거
                    }
                }
            }
        }
        return edges;
    }
    ```
    

## 2. 섬 단위로 한 번만 BFS 수행

- 기존에는 edge마다 BFS를 여러번 돌렸지만, 현재는 각 섬의 edge를 한번에 큐에 넣고 BFS 수행.
- 각 섬마다 BFS를 단 한번만 실행하므로, 성능 향상이 매우 큼
- 다른 섬을 만나면 바로 return하여 조기 종료 → 효율 극대화

### 🔥 문제

- edge마다 BFS를 돌리고, 이는 참조값이였기 때문에 중복 등록된 BFS도 수행됨

기존코드

**수정된 코드**

```java
// 섬 식별 -> 각 섬마다 중복이 제거된 edgeSet이 저장된 List
List<Set<Long>> islandEdges = new ArrayList<>();
for (int i = 0; i < N; i++) {
    for (int j = 0; j < N; j++) {
        if (!visited[i][j] && map[i][j] == LAND) {
            islandEdges.add(identifyIsland(i, j, ++islandId));
        }
    }
}

// 각 섬에서 BFS -> 각 섬 List를 순회하면서 edgeSet을 넣고 BFS 수행
for (int i = 0; i < islandEdges.size(); i++) {
    bfsFromIsland(islandEdges.get(i), i + 1);
}

```

## 3. dp 배열 제거

- 거리 정보를 int[] {x, y, dist} 형태로 queue에 직접 담음
    
    → 메모리 절약 + 코드 가독성 향상
    
    **기존 코드**
    
    ```java
    private static void bfs(int x, int y, int nowFlag) {
        ...
        while (!queue.isEmpty()) {
            ...
            for (int i = 0; i < 4; i++) {
                ...
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        dp[nx][ny] = dp[now[0]][now[1]] + 1;
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    } ...
                }
            }
        }
    }
    ```
    
    **수정된 코드**
    
    ```java
    private static void bfsFromIsland(Set<Long> edges, int currentId) {
        Queue<int[]> queue = new LinkedList<>();
        ...
    
        while (!queue.isEmpty()) {
            ...
            for (int i = 0; i < 4; i++) {
                ...
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == SEA) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    }
                }
            }
        }
    }
    ```

# 🪄 2차 성능 최적화

## 1. visited[] 배열 재사용

### 🔥 문제

기존 코드의 경우 매번 new boolean[][]코드를 사용하여 메모리 새로 할당
new 는 JVM 힙 메모리를 반복해서 소비하게 되며, CG 압박이 누적되면 성능 저하로 이어짐

​
**기존코드**
    ```java
    visited = new boolean[N][N];
    ​```

​

### 🧯문제 해결

따라서, Arrays.fill() 메서드를 사용해 이미 할당된 메모리 재사용

​

**수정된 코드**
    ```java
    for (int j = 0; j < N; j++) {
        Arrays.fill(visited[j], false);
    }
    ```
​

## 2. edgsList에 LAND가 아닌 SEA 위치 저장

첫번째 수정된 코드에서는 `map[nx][ny] == 0` 이면 edgeList에 LAND의 좌표를 저장함.
그러나 두번째 코드에서는 SEA의 좌표를 저장함.

따라서, 최종적으로 1번의 연산이 줄어들게 되고 visited 접근 수도 줄어들게됨.

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N, flag;
        static int minDist = Integer.MAX_VALUE;
        static int[][] map, dp;
        static boolean[][] visited;
        static int[] dx = {-1, 1, 0, 0};
        static int[] dy = {0, 0, -1, 1};
        static HashSet<int[]> edgeSet = new HashSet<>();
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            N = Integer.parseInt(br.readLine());
            map = new int[N][N];
            dp = new int[N][N];
            visited = new boolean[N][N];
            for (int i = 0; i < N; i++) {
                map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            }
    
            //1. 맵 초기화 및 필요한 정보 추출
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j] && map[i][j] == 1) {
                        init(i, j, ++flag);
                    }
                }
            }
    
            //2. edgeSet에서 하나씩 추출해서 BFS 돌기
            List<int[]> edgeList = new ArrayList<>(edgeSet);
            for (int[] each : edgeList) {
                for (int i = 0; i < N; i++) {
                    Arrays.fill(visited[i], false);
                    Arrays.fill(dp[i], 0);
                }
                bfs(each[0], each[1], map[each[0]][each[1]]);
            }
    
            System.out.println(minDist);
        }
    
        private static void init(int x, int y, int flag) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
            map[x][y] = flag;
    
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
    
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
    
                    if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                        if (map[nx][ny] == 1) {
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                            map[nx][ny] = flag;
                        } else if (map[nx][ny] == 0) {
                            edgeSet.add(new int[]{now[0], now[1]});
                        }
                    }
                }
            }
        }
    
        private static void bfs(int x, int y, int nowFlag) {
            Queue<int[]> queue = new LinkedList<>();
            queue.offer(new int[]{x, y});
            visited[x][y] = true;
    
            while (!queue.isEmpty()) {
                int[] now = queue.poll();
    
                for (int i = 0; i < 4; i++) {
                    int nx = now[0] + dx[i];
                    int ny = now[1] + dy[i];
    
                    if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                        if (map[nx][ny] == 0) {
                            dp[nx][ny] = dp[now[0]][now[1]] + 1;
                            visited[nx][ny] = true;
                            queue.offer(new int[]{nx, ny});
                        } else if (map[nx][ny] != 0 && map[nx][ny] != nowFlag) {
                            minDist = Math.min(minDist, dp[now[0]][now[1]]);
                        }
                    }
                }
            }
        }
    }
    
    ```
