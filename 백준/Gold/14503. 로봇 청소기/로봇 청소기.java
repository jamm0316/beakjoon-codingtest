import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int r, c, d; // 0:북, 1:동, 2:남, 3:서
    static int[][] map; // 0:미청소, 1:벽, 2:청소됨

    // 북, 동, 남, 서
    static int[] dr = {-1, 0, 1, 0};
    static int[] dc = {0, 1, 0, -1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        System.out.println(simulate());
    }

    static int simulate() {
        int cleaned = 0;

        while (true) {
            // 1) 현재 칸 청소
            if (map[r][c] == 0) {
                map[r][c] = 2;
                cleaned++;
            }

            // 2) 주변 4칸에 미청소 빈칸 있는지 확인하며 왼쪽 회전/전진 시도
            boolean moved = false;
            for (int i = 0; i < 4; i++) {
                d = (d + 3) % 4; // 왼쪽 회전
                int nr = r + dr[d];
                int nc = c + dc[d];
                if (map[nr][nc] == 0) { // 미청소 빈칸이면 전진
                    r = nr;
                    c = nc;
                    moved = true;
                    break;
                }
            }

            if (moved) continue;

            // 3) 네 번 다 못 갔으면 후진
            int backDir = (d + 2) % 4;
            int br = r + dr[backDir];
            int bc = c + dc[backDir];

            if (map[br][bc] == 1) { // 뒤가 벽이면 종료
                break;
            } else { // 후진(방향 유지)
                r = br;
                c = bc;
            }
        }

        return cleaned;
    }
}