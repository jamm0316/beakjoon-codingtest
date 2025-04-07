import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    static int M;
    static int[][] tomatoBox;
    static boolean[][] visited;
    static int zeroCount;
    static Queue<int[]> queue;
    static int days = -1;  //queue에 마지막으로 들어가는 날짜는 빼줌

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());  //col
        M = Integer.parseInt(st.nextToken());  //row
        tomatoBox = new int[M][N];
        visited = new boolean[M][N];
        queue = new LinkedList<>();

        // tomatoBox 초기화
        for (int i = 0; i < M; i++) {  //row
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {  //col
                tomatoBox[i][j] = Integer.parseInt(st.nextToken());
                //queue, visited 초기화
                if (tomatoBox[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                    visited[i][j] = true;
                } else if (tomatoBox[i][j] == 0) {
                    zeroCount++;
                } else if (tomatoBox[i][j] == -1) {
                    visited[i][j] = true;
                }
            }
        }

        // 모두 익은 상태로 들어왔는지 체크
        if (zeroCount == 0) {
            System.out.println(0);
            return;
        }

        // 모두 익은 상태가 아니라면 순회
        while (!queue.isEmpty()) {
            int allCycle = queue.size();
            for (int cycle = 0; cycle < allCycle; cycle++) {
                int[] curTomato = queue.poll();
                int x = curTomato[0];
                int y = curTomato[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    if (0 <= nx && nx < M
                            && 0 <= ny && ny < N
                            && !visited[nx][ny]) {
                        tomatoBox[nx][ny] = 1;
                        visited[nx][ny] = true;
                        zeroCount--;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            days++;
        }

        // 모두 익었는지 체크
        if (zeroCount > 0) {
            System.out.println(-1);
            return;
        }
        
        System.out.println(days);
    }
}
