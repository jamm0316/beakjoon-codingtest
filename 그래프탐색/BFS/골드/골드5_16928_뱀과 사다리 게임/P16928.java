import java.io.*;
import java.util.*;

public class Main {
    static int[] board = new int[101];
    static boolean[] visited = new boolean[101];
    static int[] dice = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //board 초기화
        for (int i = 0; i <= 100; i++) {
            board[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            board[from] = to;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});  //시작점, 주사위 횟수
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int moves = current[1];

            if (pos == 100) {
                System.out.println(moves);
                return;
            }

            for (int i = 0; i < 6; i++) {
                int next = pos + dice[i];
                if (next > 100) continue;

                next = board[next]; // 사다리나 뱀이 있으면 이동

                //사다리나 뱀을 타고 간 곳이 이미 지나쳐온 곳이라면 더이상 탐색 안함
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, moves + 1});
                }
            }
        }
    }
}
