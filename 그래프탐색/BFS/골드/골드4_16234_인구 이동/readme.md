page link : [https://www.acmicpc.net/problem/16234](https://www.acmicpc.net/problem/16234)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 인구이동이 발생하는 총 날짜 구하기
- 풀이전략
    1. fullscan으로 연합 조건에 부합하는지 확인
        1. 부합하면
            1. 연합 끼리 돌아가면서 인구배분
        2. 부합하지 않으면
            1. 인구 이동한 날짜 반환

## 🎨 사용된 알고리즘
BFS, 시뮬레이션

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, moveDays, maxTeam;
    static List<List<City>> map = new ArrayList<>();
    static boolean[][] visited;
    static boolean isMove = true;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class City {
        int x, y, population;

        public City(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }

        @Override
        public String toString() {
            return "City(" + x + ", " + y + "){인구수: " + population + "}";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int population = Integer.parseInt(st.nextToken());
                map.get(i).add(new City(i, j, population));
            }
        }

        while (isMove) {
            isMove = false;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<City> union = findUnion(i, j);
                        if (union.size() > 1) {
                            movePopulation(union);
                            isMove = true;
                        }
                    }
                }
            }

            if (!isMove) break;
            moveDays++;
        }
        System.out.println(moveDays);
    }

    private static List<City> findUnion(int x, int y) {
        List<City> union = new ArrayList<>();
        Queue<City> queue = new LinkedList<>();

        union.add(map.get(x).get(y));
        queue.offer(map.get(x).get(y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            City now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    City next = map.get(nx).get(ny);
                    int diff = Math.abs(now.population - next.population);
                    if (L <= diff && diff <= R) {
                        queue.offer(map.get(nx).get(ny));
                        union.add(map.get(nx).get(ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return union;
    }

    private static void movePopulation(List<City> union) {
        int totalPopulation = 0;
        for (City c : union) {
            totalPopulation += c.population;
        }

        int avgPopulation = totalPopulation / union.size();
        for (City c : union) {
            c.population = avgPopulation;
        }
    }
}

```

---

# 🪄 해결한 오류

## 1. 연합 지정 방식 오류

### 문제

- 최초 코드에서는 (0, 0)부터 BFS로 돌면서 인구차가 조건에 맞으면 같은팀으로 묶고, 맞지 않으면 바로 새로운 팀번호 부여
- 이 방식은 연합이 서로 이어질 수 있는 국가들을 잘 못 분리시켜 연합 분리 오류가 발생

**기존 코드**

```java
private static boolean checkTrue() {
    boolean result = false;

    int curTeam = 1;
    Queue<City> queue = new LinkedList<>();
    map.get(0).get(0).team = curTeam;
    queue.offer(map.get(0).get(0));
    visited[0][0] = true;

    // 팀 갱신
    while (!queue.isEmpty()) {
        City now = queue.poll();
        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                visited[nx][ny] = true;
                City next = map.get(nx).get(ny);

                int diff = Math.abs(now.population - next.population);
                if (L <= diff && diff <= R) {
                    next.team = now.team;
                    result = true;
                } else {
                    next.team = ++curTeam; // 조건 불만족 시 새 팀 부여
                }

                queue.offer(next);
            }
        }
    }
    maxTeam = curTeam;

    return result;
}
```

### 해결 방안

- 각 국가마다 BFS 탐색을 돌려서 조건에 맞는 국가를 한꺼번에 연합으로 지정
- 조건에 맞지 않으면 새로운 BFS를 시작해 다른 연합을 탐색하고, 팀 번호 없이 연합단위 처리

**수정된 코드**

```java
private static List<City> findUnion(int x, int y) {
    List<City> union = new ArrayList<>();
    Queue<City> queue = new LinkedList<>();

    union.add(map.get(x).get(y));
    queue.offer(map.get(x).get(y));
    visited[x][y] = true;

    while (!queue.isEmpty()) {
        City now = queue.poll();
        for (int i = 0; i < 4; i++) {
            int nx = now.x + dx[i];
            int ny = now.y + dy[i];
            if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                City next = map.get(nx).get(ny);
                int diff = Math.abs(now.population - next.population);
                if (L <= diff && diff <= R) {
                    queue.offer(map.get(nx).get(ny));
                    union.add(map.get(nx).get(ny));
                    visited[nx][ny] = true;
                }
            }
        }
    }

    return union;
}
```

## 2. visited 처리 시점 문제

### 문제

- visited 배열 처리 시점이 잘못되어, 연합이 될 수 있는 국가들이 탐색되지 않고 누락되는 경우 발생

**기존코드**

```java
if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
    visited[nx][ny] = true;
    City next = map.get(nx).get(ny);

    int diff = Math.abs(now.population - next.population);
    if (L <= diff && diff <= R) {
        next.team = now.team;
        result = true;
    } else {
        next.team = ++curTeam;
    }

    queue.offer(next);
}
```

### 해결방안

- 탐색 조건이 충족된 경우에만 visited를 true로 처리
- 조건 불충시 visited 처리하지 않고 다른 BFS에서 탐색할 수 있도록 유지

```java
if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
    City next = map.get(nx).get(ny);
    int diff = Math.abs(now.population - next.population);
    if (L <= diff && diff <= R) {
        queue.offer(map.get(nx).get(ny));
        union.add(map.get(nx).get(ny));
        visited[nx][ny] = true;
    }
}
```

## 3. 인구 이동 유무 감지 실패

### 문제

- 하루 동안 인구 이동이 실제로 있었는지 감지하지 못하고, 이동이 없는데도 불필요한 연산 반복

**기존코드**

```java
if (!checkTrue()) {
    System.out.println(moveDays);
    return;
} else {
    movePopulation();
    moveDays++;
}
```

### 해결방안

- BFS 수행 중 연합의 크기를 체크
- 크기가 2 이상인 경우에만 인구 이동이 발생하도록 플래그(isMove)를 사용

```java
if (!isMoved) break;
moveDays++;
```

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N, L, R, moveDays, maxTeam;
        static List<List<City>> map = new ArrayList<>();
        static int[][] team;
        static boolean[][] visited;
        static boolean isTrue;
        static int[] dx = {1, -1, 0, 0};
        static int[] dy = {0, 0, 1, -1};
    
        static class City {
            int x;
            int y;
            int population;
            int team;
    
            public City(int x, int y, int population, int team) {
                this.x = x;
                this.y = y;
                this.population = population;
                this.team = team;  //0: 연합 없음
            }
    
            @Override
            public String toString() {
                return "City(" + x + ", " + y + "){인구수: " + population + ", 연합: " + team + "}";
            }
        }
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            L = Integer.parseInt(st.nextToken());
            R = Integer.parseInt(st.nextToken());
            team = new int[N][N];
            visited = new boolean[N][N];
    
            for (int i = 0; i < N; i++) {
                map.add(new ArrayList<>());
                st = new StringTokenizer(br.readLine());
                for (int j = 0; j < N; j++) {
                    int population = Integer.parseInt(st.nextToken());
                    map.get(i).add(new City(i, j, population, 0));
                }
            }
    
            while (true) {
                //0.초기화
                for (int i = 0; i < N; i++) {
                    Arrays.fill(visited[i], false);
                    maxTeam = 0;
                    for (int j = 0; j < N; j++) {
                        map.get(i).get(j).team = 0;
                    }
                }
    
                //1. 연합 조건 부합하지 않을 시
                if (!checkTrue()) {
                    System.out.println(moveDays);
                    return;
                }
    
                //2. 부합시
                else {
                    movePopulation();
                    moveDays++;
                }
            }
        }
    
        private static boolean checkTrue() {
            boolean result = false;
    
            int curTeam = 1;
            Queue<City> queue = new LinkedList<>();
            map.get(0).get(0).team = curTeam;
            queue.offer(map.get(0).get(0));
            visited[0][0] = true;
    
            //1. 팀갱신
            while (!queue.isEmpty()) {
                City now = queue.poll();
                for (int i = 0; i < 4; i++) {
                    int nx = now.x + dx[i];
                    int ny = now.y + dy[i];
                    if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                        visited[nx][ny] = true;
                        City next = map.get(nx).get(ny);
    
                        int diff = Math.abs(now.population - next.population);
                        if (L <= diff && diff <= R) {
                            next.team = now.team;
                            result = true;
                        } else {
                            next.team = ++curTeam;
                        }
    
                        queue.offer(next);
                    }
                }
            }
            maxTeam = curTeam;
    
            return result;
        }
    
        private static void movePopulation() {
            int[] totalPopulation = new int[maxTeam + 1];
            int[] totalCountries = new int[maxTeam + 1];
    
            //1. 팀별 인구 수 갱신
            for (int team = 1; team <= maxTeam; team++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        City now = map.get(i).get(j);
                        if (now.team == team) {
                            totalPopulation[team] += now.population;
                            totalCountries[team] += 1;
                        }
                    }
                }
            }
    
            //2. City 갱신
            for (int team = 1; team <= maxTeam; team++) {
                for (int i = 0; i < N; i++) {
                    for (int j = 0; j < N; j++) {
                        City now = map.get(i).get(j);
                        if (now.team == team) {
                            now.population = (int) Math.floor(totalPopulation[team] / totalCountries[team]);
                        }
                    }
                }
            }
    
            for (List<City> each : map) {
                System.out.println(each);
            }
        }
    }
    ```
