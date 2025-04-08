import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //col
        int M = Integer.parseInt(st.nextToken());  //row
        int H = Integer.parseInt(st.nextToken());  //height
        int[][][] tomatoBox = new int[H][M][N];  //가장 바깥쪽 대괄호부터 안쪽 대괄호까지
        Queue<int[]> queue = new LinkedList<>();
        int zeroCount = 0;
        int days = -1;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    tomatoBox[i][j][k] = tomato;
                    if (tomato == 1) {
                        queue.offer(new int[]{i, j, k});
                    } else if (tomato == 0) {
                        zeroCount++;
                    }
                }
            }
        }

        if (zeroCount == 0) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            int cycle = queue.size();
            for (int eachCycle = 0; eachCycle < cycle; eachCycle++) {
                int[] pos = queue.poll();
                int z = pos[0], y = pos[1], x = pos[2];
                for (int i = 0; i < 6; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    int nz = z + dz[i];

                    if (0 <= nx && nx < N &&
                            0 <= ny && ny < M &&
                            0 <= nz && nz < H &&
                            tomatoBox[nz][ny][nx] == 0) {
                        tomatoBox[nz][ny][nx] = 1;
                        zeroCount--;
                        queue.offer(new int[]{nz, ny, nx});
                    }
                }
            }
            days++;
        }

        System.out.println(zeroCount != 0 ? -1 : days);
    }
}
