import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static char[][] board;
    static int sxR, syR, sxB, syB;
    static final int[] dx = {-1, 1, 0, 0}; // 상, 하, 좌, 우
    static final int[] dy = {0, 0, -1, 1};

    static class State {
        int rx, ry, bx, by, depth;
        State(int rx, int ry, int bx, int by, int depth) {
            this.rx = rx; this.ry = ry; this.bx = bx; this.by = by; this.depth = depth;
        }
    }

    static class Roll {
        int x, y, moved;
        boolean inHole;
        Roll(int x, int y, int moved, boolean inHole) {
            this.x = x; this.y = y; this.moved = moved; this.inHole = inHole;
        }
    }

    enum Outcome { SUCCESS, FAIL, CONTINUE }

    static Roll roll(int x, int y, int dir) {
        int nx = x, ny = y, cnt = 0;
        while (true) {
            int tx = nx + dx[dir], ty = ny + dy[dir];
            if (board[tx][ty] == '#') break;
            nx = tx; ny = ty; cnt++;
            if (board[nx][ny] == 'O') return new Roll(nx, ny, cnt, true);
        }
        return new Roll(nx, ny, cnt, false);
    }

    static Outcome tilt(State cur, int dir, boolean[][][][] visited, Deque<State> q) {
        Roll r = roll(cur.rx, cur.ry, dir);
        Roll b = roll(cur.bx, cur.by, dir);

        if (b.inHole) return Outcome.FAIL;          // 파랑 빠지면 실패
        if (r.inHole) return Outcome.SUCCESS;       // 빨강만 빠지면 성공

        int nrx = r.x, nry = r.y, nbx = b.x, nby = b.y;

        // 같은 칸에 멈춘 경우 → 더 멀리 움직인 공을 한 칸 뒤로
        if (nrx == nbx && nry == nby) {
            if (r.moved > b.moved) {
                nrx -= dx[dir]; nry -= dy[dir];
            } else {
                nbx -= dx[dir]; nby -= dy[dir];
            }
        }

        // 둘 다 안 움직였으면 푸시 X (미세 가지치기)
        if (r.moved == 0 && b.moved == 0) return Outcome.CONTINUE;

        if (!visited[nrx][nry][nbx][nby]) {
            visited[nrx][nry][nbx][nby] = true;
            q.offer(new State(nrx, nry, nbx, nby, cur.depth + 1));
        }
        return Outcome.CONTINUE;
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken()); M = Integer.parseInt(st.nextToken());
        board = new char[N][M];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                char c = line.charAt(j);
                if (c == 'R') { sxR = i; syR = j; board[i][j] = '.'; }
                else if (c == 'B') { sxB = i; syB = j; board[i][j] = '.'; }
                else board[i][j] = c;
            }
        }

        boolean[][][][] visited = new boolean[N][M][N][M];
        Deque<State> q = new ArrayDeque<>();
        visited[sxR][syR][sxB][syB] = true;
        q.offer(new State(sxR, syR, sxB, syB, 0));

        while (!q.isEmpty()) {
            State cur = q.poll();
            if (cur.depth == 10) continue; // 10회 초과 불가

            for (int dir = 0; dir < 4; dir++) {
                Outcome o = tilt(cur, dir, visited, q);
                if (o == Outcome.SUCCESS) { System.out.println(cur.depth + 1); return; }
                // FAIL은 스킵, CONTINUE는 큐에 추가 여부가 tilt 내부에서 처리됨
            }
        }
        System.out.println(-1);
    }
}