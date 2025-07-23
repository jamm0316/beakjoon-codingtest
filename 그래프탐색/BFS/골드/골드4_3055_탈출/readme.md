page link : [https://www.acmicpc.net/problem/3055](https://www.acmicpc.net/problem/3055)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 비버 굴로 이동할 수 있는 가장 빠른 시간 출력
- 핵심 아이디어
    - 시간 단위로 시뮬레이션 해야하므로, 큐에 물과 고슴도치의 위치를 같이 담고, 유형을 구분해서 처리
    - 고슴도치는 다음 턴에 물이 퍼질 위치로 이동할 수 없음.
    - 돌(X), 물(*)이 있는 칸, 혹은 이미 방문한 칸은 이동할 수 없음.
- 풀이전략
    1. BFS 큐에 물부터 먼저 넣고, 그 다음 고슴도치를 넣는다.
    2. BFS를 돌리는데, 물이 먼저 퍼지고 → 그 다음 고슴도치가 이동한다.
    3. 이 과정을 반복하면서, 고슴도치가 비버의 굴(D)에 도착하면 그 때 시간을 출력하고 종료
    4. 고슴도치가 더 이상 이동할 수 없고 비버의 굴에 도달하지 못하면 “KAKTUS”를 출력한다.

## 🎨 사용된 알고리즘
BFS

---

## ✍🏻 pseudo code

```java
- 구현 계획
    1. Queue<Node>를 만들고, 여기에 물을 먼저 다 넣고 → 그 다음 S 위치를 넣는다.
    2. Node에 type은 “water” 또는 “hedgehog”로 구분
    3. visited[][] 배열을 따로만들어서 고슴도치가 방문한 곳을 추적
    4. BFS를 돌면서
        - water인 경우 → .인 곳만 물 퍼뜨림 (단, D는 퍼지면 안됨)
        - hedgehog인 경우 → .또는 D인 곳만 이동 (단, 다음 턴에 물이 퍼질 곳은 안됨)
```

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, time;
        String type;

        public Node(int x, int y, int time, String type) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Node:{x: " + x + ", y: " + y + ", type: " + type + "}";
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        bfs();
    }

    private static void bfs() throws IOException {
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == '*') {
                    queue.offer(new Node(i, j, 0, "water"));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    queue.offer(new Node(i, j, 0, "hedgehog"));
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

                if (cur.type.equals("water")) {
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        queue.offer(new Node(nx, ny, 0, "water"));
                    }
                } else if (cur.type.equals("hedgehog")) {
                    if ((map[nx][ny] == '.' || map[nx][ny] == 'D') && !visited[nx][ny]) {
                        if (map[nx][ny] == 'D') {
                            System.out.println(cur.time + 1);
                            return;
                        }

                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, cur.time + 1, "hedgehog"));
                    }
                }
            }
        }
        System.out.println("KAKTUS");
    }
}
```
