import java.io.*;
import java.util.*;

public class Main {
    static int N, M, x, y, K;  //지도 크기(N, M), 초기 주사위 좌표(x, y), 명령 갯수(K)
    static int[][] map;
    static Queue<Integer> commands = new LinkedList<>();
    static class Dice {
        int top;
        int bottom;
        Body body;
        int x;
        int y;

        Dice(int x, int y) {
            this.x = x;
            this.y = y;
            top = 0;
            bottom = 0;
            body = new Body();
        }

        @Override
        public String toString() {
            return "{top:" + top + ", bottom:" + bottom +
                    ", N:" + body.N + ", S:" + body.S +
                    ", W:" + body.W + ", E:" + body.E +
                    "}";
        }

        public void roll(int direction, int[] nextPos) {
            updateByDirection(direction);
            refreshPosition(nextPos[0], nextPos[1]);
        }

        public void updateByDirection(int direction) {
            int newTop, newBottom = 0;

            switch (direction) {
                case 1: {  //동
                    newTop = body.W;
                    newBottom = body.E;
                    body.W = bottom;
                    body.E = top;
                    top = newTop;
                    bottom = newBottom;
                    break;
                }
                case 2: {  //서
                    newTop = body.E;
                    newBottom = body.W;
                    body.W = top;
                    body.E = bottom;
                    top = newTop;
                    bottom = newBottom;
                    break;
                }
                case 3: {  //북
                    newTop = body.S;
                    newBottom = body.N;
                    body.N = top;
                    body.S = bottom;
                    top = newTop;
                    bottom = newBottom;
                    break;
                }
                case 4: {  //남
                    newTop = body.N;
                    newBottom = body.S;
                    body.N = bottom;
                    body.S = top;
                    top = newTop;
                    bottom = newBottom;
                    break;
                }
            }
        }

        public void changeBottom(int newBottom) {
            bottom = newBottom;
        }

        private void refreshPosition(int nx, int ny) {
            x = nx;
            y = ny;
        }

        public int[] getNextPosition(int direction) {
            int[] next = moveNextDirection(direction);
            int nx = next[0] + x;
            int ny = next[1] + y;
            return new int[]{nx, ny};
        }

        private int[] moveNextDirection(int direction) {
            switch (direction) {
                case 1: return new int[]{0, 1};  //동
                case 2: return new int[]{0, -1};  //서
                case 3: return new int[]{-1, 0};  //북
                case 4: return new int[]{1, 0};  //남
                default: return new int[]{0, 0};
            }
        }
    }

    static class Body {
        int N, S, E, W;

        Body() {
            N = 0; S =0; E =0; W = 0;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < K; i++) {
            commands.offer(Integer.parseInt(st.nextToken()));
        }

        Dice dice = new Dice(x, y);
        StringBuilder sb = new StringBuilder();
        while (!commands.isEmpty()) {
            int direction = commands.poll();
            int[] nextPos = dice.getNextPosition(direction);
            int nx = nextPos[0]; int ny = nextPos[1];
            if (!validate(nx, ny)) {
                continue;
            }

            //주사위 굴리기(주사위 육면체 숫자 갱신, 위치 갱신)
            dice.roll(direction, nextPos);

            //맵 숫자 에 따라 숫자 갱신
            if (map[nx][ny] == 0) {
                map[nx][ny] = dice.bottom;
            } else {
                dice.changeBottom(map[nx][ny]);
                map[nx][ny] = 0;
            }
            sb.append(dice.top).append('\n');
        }

        System.out.println(sb.toString());
    }

    private static boolean validate(int nx, int ny) {
        if (nx < 0 || N <= nx || ny < 0 || M <= ny) {
            return false;
        }
        return true;
    }
}
