import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] map;
    static List<int[]> candidates = new ArrayList<>();
    static int emptyCnt; //채워야 할 빈칸 수
    static int answer = Integer.MAX_VALUE;

    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 0) emptyCnt++;
                else if (map[i][j] == 2) candidates.add(new int[]{i, j});
            }
        }

        // 빈칸이 아예 없다면 바로 0 반환
        if (emptyCnt == 0) {
            System.out.println(0);
            return;
        }

        // 후보 중 M개 고르는 조합
        int size = candidates.size();
        int[] picked = new int[M];
        comb(0, 0, size, picked);

        System.out.println(answer == Integer.MAX_VALUE ? -1 : answer);
    }

    // 조합: candidates에서 M개 고르기 -> DFS
    static void comb(int depth, int start, int size, int[] picked) {
        if (depth == M) {
            int time = bfs(picked);
            if (time != -1) answer = Math.min(answer, time);
            return;
        }

        for (int i = start; i < size; i++) {
            picked[depth] = i;
            comb(depth + 1, i + 1, size, picked);
        }
    }

    // 멀티 소스 BFS: 선택된 M개를 활성으로 시작
    static int bfs(int[] picked) {
        int[][] dist = new int[N][N];
        for (int i = 0; i < N; i++) Arrays.fill(dist[i], -1);

        Queue<int[]> queue = new LinkedList<>();
        for (int idx : picked) {
            int[] pick = candidates.get(idx);
            dist[pick[0]][pick[1]] = 0;
            queue.offer(new int[]{pick[0], pick[1]});
        }

        int filled = 0;  // 빈칸 몇칸은 채웠는지 확인
        int maxTime = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];
            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];
                if (nx < 0 || N <= nx || ny < 0 || N <= ny ) continue;
                if (map[nx][ny] == 1) continue;  //벽이면 안간다
                if (dist[nx][ny] != -1) continue;  //이미 채워지면 안간다
                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[]{nx, ny});

                if (map[nx][ny] == 0) {
                    filled++;
                    maxTime = Math.max(maxTime, dist[nx][ny]);
                    if (filled == emptyCnt) {
                        return maxTime;
                    }
                }
            }
        }
        return -1; //다 못채운 경우
    }
}
