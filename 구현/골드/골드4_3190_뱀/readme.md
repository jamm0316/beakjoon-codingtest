page link : [https://www.acmicpc.net/problem/3190](https://www.acmicpc.net/problem/3190)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 게임이 끝나는 시간
- 풀이 전략
    1. N을 받아 N*N 그래프 만들기
    2. K를 받은 후 그래프에 사과 넣기
    3. L을 받안 후 Queue에 command 넣기
    4. 각 command의 실행은 메서드로 정의하기

## 🎨 사용된 알고리즘
시뮬레이션

---

# 🧑🏻‍💻 code
## Java
```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, L, runningTime;
    static int[][] map;  //0: 이동 가능, 1: 사과, 2: 뱀 몽길이
    static boolean running = true;
    static Snake snake;

    enum Dir {N, S, W, E}

    static class Command {
        int time;
        char dir;

        Command(int time, char dir) {
            this.time = time;
            this.dir = dir;
        }
    }

    static class Snake {
        Deque<int[]> body;  //머리: 마지막, 꼬리: 첫번쨰
        Dir direction;

        Snake(int[] initPosition) {
            body = new LinkedList<>();
            body.offerLast(initPosition);
            direction = Dir.E;
        }

        public int[] head() {
            return body.peekLast();
        }

        public int[] tail() {
            return body.peekFirst();
        }

        //사과 먹었을 때: 머리만 추가
        public void grow(int x, int y) {
            body.offer(new int[]{x, y});
            map[x][y] = 2;
        }

        //사과 없을 때: 머리 추가, 꼬리 제거
        public void move(int x, int y) {
            body.offer(new int[]{x, y});
            map[x][y] = 2;  //몸 표시
            int[] tail = body.pollFirst();  //꼬리제거
            map[tail[0]][tail[1]] = 0; //맵에서 지우기
        }

        //방향전환
        public void changeDirection(char command) {
            if (command == 'D') {  //오른쪽 90도
                if (direction == Dir.N) direction = Dir.E;
                else if (direction == Dir.S) direction = Dir.W;
                else if (direction == Dir.E) direction = Dir.S;
                else if (direction == Dir.W) direction = Dir.N;
            } else if (command == 'L') {
                if (direction == Dir.N) direction = Dir.W;
                else if (direction == Dir.S) direction = Dir.E;
                else if (direction == Dir.E) direction = Dir.N;
                else if (direction == Dir.W) direction = Dir.S;
            }
        }
        
        //다음 위치 가져오기
        public int[] getNextPosition() {
            int[] nextMove = snake.getNextMove();
            int nx = snake.head()[0] + nextMove[0];
            int ny = snake.head()[1] + nextMove[1];
            return new int[]{nx, ny};
        }

        //이동거리
        private int[] getNextMove() {
            switch (direction) {
                case N: return new int[]{-1, 0};
                case S: return new int[]{1, 0};
                case E: return new int[]{0, 1};
                case W: return new int[]{0, -1};
                default: return new int[]{0, 0};
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        snake = new Snake(new int[]{0, 0});
        map[0][0] = 2;

        K = Integer.parseInt(br.readLine());
        for (int i = 0; i < K; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            map[row][col] = 1;  //사과
        }

        L = Integer.parseInt(br.readLine());
        Queue<Command> commands = new LinkedList<>();
        for (int i = 0; i < L; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            char dir = st.nextToken().charAt(0);
            commands.offer(new Command(time, dir));
        }

        // 게임
        runningTime = 0;
        while (true) {
            runningTime++;

            // 다음 위치 계산
            int[] nextPosition = snake.getNextPosition();
            int nx = nextPosition[0];
            int ny = nextPosition[1];

            // 종료 조건: 벽 또는 자기 몸과 충돌
            if (nx < 0 || nx >= N || ny < 0 || ny >= N || map[nx][ny] == 2) {
                break;
            }

            // 이동 처리
            if (map[nx][ny] == 1) { // 사과가 있는 경우
                snake.grow(nx, ny);
            } else { // 사과가 없는 경우
                snake.move(nx, ny);
            }

            // 명령어 처리
            if (!commands.isEmpty() && commands.peek().time == runningTime) {
                snake.changeDirection(commands.poll().dir);
            }
        }

        // 결과 출력
        System.out.println(runningTime);
    }
}

```
