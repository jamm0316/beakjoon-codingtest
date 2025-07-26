import java.io.*;
import java.util.*;

public class Main {
    static int N, flag;
    static int minDist = Integer.MAX_VALUE;
    static int[][] map;
    static boolean[][] visited;
    static List<Set<Long>> edgeList = new ArrayList<>();
    static int[] DIRECTION_X = {-1, 1, 0, 0};
    static int[] DIRECTION_Y = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == 1) {
                    edgeList.add(init(i, j, ++flag));
                }
            }
        }

        for (int i = 0; i < edgeList.size(); i++) {
            for (int j = 0; j < N; j++) {
                Arrays.fill(visited[j], false);
            }
            bfs(edgeList.get(i), i + 1);
        }

        System.out.println(minDist);
    }

    private static HashSet<Long> init(int x, int y, int flag) {
        HashSet<Long> edges = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y, flag});
        visited[x][y] = true;
        map[x][y] = flag;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DIRECTION_X[i];
                int ny = now[1] + DIRECTION_Y[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    visited[nx][ny] = true;

                    if (map[nx][ny] == 0) {
                        edges.add((long) nx * N + ny);
                    } else {
                        map[nx][ny] = now[2];
                        queue.offer(new int[]{nx, ny, flag});
                    }
                }
            }
        }
        return edges;
    }

    private static void bfs(Set<Long> set, int curFlag) {
        Queue<int[]> queue = new LinkedList<>();

        Iterator<Long> iterator = set.iterator();
        while (iterator.hasNext()) {
            Long edge = iterator.next();
            int x = (int) (edge / N);
            int y = (int) (edge % N);
            queue.offer(new int[]{x, y, 0});
            visited[x][y] = true;
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DIRECTION_X[i];
                int ny = now[1] + DIRECTION_Y[i];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == 0) {
                        queue.offer(new int[]{nx, ny, now[2] + 1});
                        visited[nx][ny] = true;
                    } else if (map[nx][ny] != curFlag) {
                        minDist = Math.min(minDist, now[2] + 1);
                        return;
                    }
                }
            }
        }
    }
}
