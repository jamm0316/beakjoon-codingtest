import java.io.*;
import java.util.*;

public class Main {
    static int N, flag;
    static int minDist = Integer.MAX_VALUE;
    static int[][] map, dp;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static HashSet<int[]> edgeSet = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        dp = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        //1. 맵 초기화 및 필요한 정보 추출
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    init(i, j, ++flag);
                }
            }
        }

        //2. edgeSet에서 하나씩 추출해서 BFS 돌기
        List<int[]> edgeList = new ArrayList<>(edgeSet);
        for (int[] each : edgeList) {
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
                Arrays.fill(dp[i], 0);
            }
            bfs(each[0], each[1], map[each[0]][each[1]]);
        }

        System.out.println(minDist);
    }

    private static void init(int x, int y, int flag) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = flag;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == 1) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        map[nx][ny] = flag;
                    } else if (map[nx][ny] == 0) {
                        edgeSet.add(new int[]{now[0], now[1]});
                    }
                }
            }
        }
    }

    private static void bfs(int x, int y, int nowFlag) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + dx[i];
                int ny = now[1] + dy[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        dp[nx][ny] = dp[now[0]][now[1]] + 1;
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    } else if (map[nx][ny] != 0 && map[nx][ny] != nowFlag) {
                        minDist = Math.min(minDist, dp[now[0]][now[1]]);
                    }
                }
            }
        }
    }
}
