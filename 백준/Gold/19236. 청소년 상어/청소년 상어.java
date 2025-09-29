import java.io.*;
import java.util.*;

public class Main {
    static class Fish {
        int x, y, dir;
        boolean alive = true;
        Fish(int x, int y, int dir) { this.x=x; this.y=y; this.dir=dir; }
        Fish(Fish o) { this.x=o.x; this.y=o.y; this.dir=o.dir; this.alive=o.alive; }
    }

    static final int N = 4;
    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗ (문제 정의 순서 1~8 → index 0~7)
    static final int[][] D = {{-1,0},{-1,-1},{0,-1},{1,-1},{1,0},{1,1},{0,1},{-1,1}};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[][] board = new int[N][N];        // 0=빈칸, -1=상어, 1..16=물고기 번호
        Fish[] fishes = new Fish[17];         // 1..16

        for (int i=0;i<N;i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j=0;j<N;j++) {
                int num = Integer.parseInt(st.nextToken());
                int dir = Integer.parseInt(st.nextToken()) - 1; // 0-based
                board[i][j] = num;
                fishes[num] = new Fish(i, j, dir);
            }
        }

        // 1) 초기 상어가 (0,0)의 물고기 먹기
        int first = board[0][0];
        Fish f0 = fishes[first];
        int sharkDir = f0.dir;
        f0.alive = false;
        board[0][0] = -1;

        dfs(board, fishes, 0, 0, sharkDir, first);
        System.out.println(answer);
    }

    static void dfs(int[][] board, Fish[] fishes, int sx, int sy, int sdir, int sum) {
        answer = Math.max(answer, sum);

        // 2) 물고기 이동 (1→16)
        moveAllFish(board, fishes, sx, sy);

        // 3) 상어 이동(1~3칸)
        for (int step=1; step<=3; step++) {
            int nx = sx + D[sdir][0]*step;
            int ny = sy + D[sdir][1]*step;
            if (!in(nx,ny)) break;                 // 더 진행 불가
            if (board[nx][ny] == 0) continue;      // 빈칸이면 못 섭취 → 다음 step

            // 상태 깊은 복사
            int[][] b2 = copyBoard(board);
            Fish[] f2 = copyFishes(fishes);

            int eat = b2[nx][ny];
            int prevX = sx, prevY = sy;

            // 먹기
            f2[eat].alive = false;
            b2[prevX][prevY] = 0;      // 원래 상어 자리 비움
            b2[nx][ny] = -1;           // 상어 이동
            int ndir = f2[eat].dir;

            dfs(b2, f2, nx, ny, ndir, sum + eat);
        }
        // 이동 가능한 칸이 하나도 없으면 자연스럽게 리턴 → 리프에서 answer 갱신됨
    }

    static void moveAllFish(int[][] board, Fish[] fishes, int sx, int sy) {
        for (int n=1; n<=16; n++) {
            Fish f = fishes[n];
            if (f == null || !f.alive) continue;

            for (int rot=0; rot<8; rot++) {
                int nd = (f.dir + rot) % 8;
                int nx = f.x + D[nd][0];
                int ny = f.y + D[nd][1];
                if (!in(nx,ny) || (sx==nx && sy==ny)) continue;

                // 목적지가 빈칸 또는 물고기
                if (board[nx][ny] == 0) {
                    // 빈칸 이동
                    board[f.x][f.y] = 0;
                    board[nx][ny] = n;
                    f.x = nx; f.y = ny; f.dir = nd;
                } else if (board[nx][ny] > 0) {
                    // 스왑
                    int other = board[nx][ny];
                    Fish g = fishes[other];

                    board[f.x][f.y] = other;
                    board[nx][ny] = n;

                    // 좌표 교환
                    int ox = g.x, oy = g.y;
                    g.x = f.x; g.y = f.y;
                    f.x = nx; f.y = ny;
                    f.dir = nd;
                }
                break; // 이동 성공 시 중단
            }
        }
    }

    static boolean in(int x,int y){ return 0<=x && x<N && 0<=y && y<N; }

    static int[][] copyBoard(int[][] src){
        int[][] dst = new int[N][N];
        for (int i=0;i<N;i++) System.arraycopy(src[i],0,dst[i],0,N);
        return dst;
    }

    static Fish[] copyFishes(Fish[] src){
        Fish[] dst = new Fish[17];
        for (int i=1;i<=16;i++){
            if (src[i]!=null) dst[i] = new Fish(src[i]);
        }
        return dst;
    }
}
