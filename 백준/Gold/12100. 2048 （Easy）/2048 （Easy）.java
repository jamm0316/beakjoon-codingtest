import java.io.*;
import java.util.*;

public class Main {
    static int N, result = 0;
    static int[][] board;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        board = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0, board);
        System.out.println(result);
    }

    static void dfs(int depth, int[][] map) {
        if (depth == 5) {
            result = Math.max(result, getMax(map));
            return;
        }

        for (int dir = 0; dir < 4; dir++) {
            int[][] nextMap = move(dir, map);
            dfs(depth + 1, nextMap);
        }
    }

    static int[][] move(int dir, int[][] map) {
        int[][] newMap = new int[N][N];

        for (int i = 0; i < N; i++) {
            LinkedList<Integer> list = new LinkedList<>();

            // 한 줄 가져오기
            for (int j = 0; j < N; j++) {
                int value = 0;
                if (dir == 0) {           // 위
                    value = map[j][i];
                } else if (dir == 1) {    // 아래
                    value = map[N - 1 - j][i];
                } else if (dir == 2) {    // 왼쪽
                    value = map[i][j];
                } else if (dir == 3) {    // 오른쪽
                    value = map[i][N - 1 - j];
                }
                if (value != 0) list.add(value);
            }

            // 합치기
            LinkedList<Integer> merged = new LinkedList<>();
            while (!list.isEmpty()) {
                int cur = list.poll();
                if (!list.isEmpty() && cur == list.peek()) {
                    merged.add(cur * 2);
                    list.poll(); // 다음 블록은 합쳐졌으니 건너뜀
                } else {
                    merged.add(cur);
                }
            }

            // 남은 칸 0으로 채우기
            while (merged.size() < N) merged.add(0);

            // 다시 보드에 넣기
            for (int j = 0; j < N; j++) {
                if (dir == 0) {           // 위
                    newMap[j][i] = merged.get(j);
                } else if (dir == 1) {    // 아래
                    newMap[N - 1 - j][i] = merged.get(j);
                } else if (dir == 2) {    // 왼쪽
                    newMap[i][j] = merged.get(j);
                } else if (dir == 3) {    // 오른쪽
                    newMap[i][N - 1 - j] = merged.get(j);
                }
            }
        }

        return newMap;
    }

    static int getMax(int[][] map) {
        int maxVal = 0;
        for (int[] row : map) {
            for (int val : row) {
                maxVal = Math.max(maxVal, val);
            }
        }
        return maxVal;
    }
}