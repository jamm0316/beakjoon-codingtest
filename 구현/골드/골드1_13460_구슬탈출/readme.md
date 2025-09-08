page link : [https://www.acmicpc.net/problem/13460](https://www.acmicpc.net/problem/13460)

---

# 💡 풀이전략
- 시간복잡도로 최대 연산 수 계산
    - N, M모두 10이 최대 → 시간제한 2초 → 16번이나 중첩 반복문 사용 가능
    - 넉넉한 시간 제한
- 구하고자 하는 값
    - 빨간 구슬 빼기 위한 최소 이동 횟수
- 아이디어
    - 최소 이동횟수를 구하는 것으로 그래프 탐색을 이용한다.
    - 그래프 탐색의 방법에는 DFS, BFS가 있다.
    - 너비 우선 탐색으로 먼저 0애 도달하는 경우의 수가 최소 이동횟수 이다.
- 풀이전략
    1. 입력값을 받는다 (N, M, map)
    2. 상태 관리
        1. `State {Rx, Ry, Bx, By, depth}`를 관리한다.
    3. 4방향(상하좌우) 각 방향에 대해 기울임 시뮬레이션 
        1. 빨강/파랑 각각 해당 방향으로 벽(`#`) 앞/ 구멍(`0`)까지 굴림
        2. 파랑이 `0`에 빠지면 `continue`
        3. 빨강이 `0`이고 파랑이 `0`이 아니면 `depth+1` 반환 (성공)
        4. 두 공의 위치가 같다면, 이번 방향으로 더 많이 굴러온 공을 한 칸 뒤로.
        5. 방문하지 않은 상태라면 큐에 offer
    4. BFS 내에 성공 못 찾으면 `-1` 반환

## 🎨 사용된 알고리즘
시뮬레이션, BFS

---

# 🧑🏻‍💻 code
## Java
```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int sRx, sRy, sBx, sBy;  //시작 위치
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class State {
        int rx, ry, bx, by, depth;

        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx;
            this.ry = ry;
            this.bx = bx;
            this.by = by;
            this.depth = depth;
        }
    }

    static class Roll {
        int x, y, moved;
        boolean inHole;

        Roll(int x, int y, int moved, boolean inHole) {
            this.x = x;
            this.y = y;
            this.moved = moved;
            this.inHole = inHole;
        }
    }

    enum OutCome {SUCCESS, CONTINUE, FAIL}

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        board = new char[N][M];
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'R') {
                    sRx = i;
                    sRy = j;
                    board[i][j] = '.';
                } else if (c == 'B') {
                    sBx = i;
                    sBy = j;
                    board[i][j] = '.';
                } else {
                    board[i][j] = c;
                }
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];  //rx, ry, bx, by 가 동시성의 띄므로 4차원 배열 사용
        Deque<State> deque = new ArrayDeque<>();
        visited[sRx][sRy][sBx][sBy] = true;
        deque.offer(new State(sRx, sRy, sBx, sBy, 0));

        while (!deque.isEmpty()) {
            State now = deque.poll();
            if (now.depth == 10) continue;

            for (int i = 0; i < 4; i++) {
                OutCome o = tilt(now, i, visited, deque);
                if (o == OutCome.SUCCESS) {
                    System.out.println(now.depth + 1);
                    return;
                }
            }
        }
        System.out.println(-1);
    }

    private static OutCome tilt(State now, int dir, boolean[][][][] visited, Deque<State> deque) {
        Roll r = roll(now.rx, now.ry, dir);
        Roll b = roll(now.bx, now.by, dir);

        if (b.inHole) return OutCome.FAIL;
        if (r.inHole) return OutCome.SUCCESS;

        int nrx = r.x, nry = r.y, nbx = b.x, nby = b.y;

        if (nrx == nbx && nry == nby) {
            if (r.moved > b.moved) {
                nrx -= dx[dir];
                nry -= dy[dir];
            } else {
                nbx -= dx[dir];
                nby -= dy[dir];
            }
        }

        if (r.moved == 0 && b.moved == 0) return OutCome.CONTINUE;

        if (!visited[nrx][nry][nbx][nby]) {
            visited[nrx][nry][nbx][nby] = true;
            deque.offer(new State(nrx, nry, nbx, nby, now.depth + 1));
        }
        return OutCome.CONTINUE;
    }

    private static Roll roll(int x, int y, int dir) {
        int nowX = x, nowY = y, count = 0;
        while (true) {
            int nx = nowX + dx[dir], ny = nowY + dy[dir];
            if (board[nx][ny] == '#') break;
            nowX = nx;
            nowY = ny;
            count++;
            if (board[nx][ny] == 'O') return new Roll(nowX, nowY, count, true);
        }
        return new Roll(nowX, nowY, count, false);
    }
}
```
