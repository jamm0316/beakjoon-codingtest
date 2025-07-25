import java.io.*;
import java.util.*;

public class Main {
    private static final int SEA = 0;
    private static final int LAND = 1;
    private static final int[] DIRECTIONS_X = {-1, 1, 0, 0};
    private static final int[] DIRECTIONS_Y = {0, 0, -1, 1};
    private static int N, islandId;
    private static int minDist = Integer.MAX_VALUE;
    private static int[][] map;
    private static boolean[][] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];
        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            map[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        }

        // 섬 식별
        List<Set<Long>> islandEdges = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && map[i][j] == LAND) {
                    islandEdges.add(identifyIsland(i, j, ++islandId));
                }
            }
        }

        // 각 섬에서 BFS
        for (int i = 0; i < islandEdges.size(); i++) {
            bfsFromIsland(islandEdges.get(i), i + 1);
        }

        System.out.println(minDist);
    }

    private static Set<Long> identifyIsland(int x, int y, int currentId) {
        Set<Long> edges = new HashSet<>();
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        map[x][y] = currentId;

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now[0] + DIRECTIONS_X[i];
                int ny = now[1] + DIRECTIONS_Y[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == LAND) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                        map[nx][ny] = currentId;
                    } else if (map[nx][ny] == SEA) {
                        edges.add((long) now[0] * N + now[1]);
                    }
                }
            }
        }
        return edges;
    }

    private static void bfsFromIsland(Set<Long> edges, int currentId) {
        Queue<int[]> queue = new LinkedList<>();
        visited = new boolean[N][N];
        for (Long edge : edges) {
            int x = (int) (edge / N);
            int y = (int) (edge % N);
            queue.offer(new int[]{x, y, 0}); // {x, y, distance}
            visited[x][y] = true;
        }

        while (!queue.isEmpty()) {
            int[] now = queue.poll();
            int x = now[0], y = now[1], dist = now[2];

            for (int i = 0; i < 4; i++) {
                int nx = x + DIRECTIONS_X[i];
                int ny = y + DIRECTIONS_Y[i];
                if (nx >= 0 && nx < N && ny >= 0 && ny < N && !visited[nx][ny]) {
                    if (map[nx][ny] == SEA) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny, dist + 1});
                    } else if (map[nx][ny] != currentId) {
                        minDist = Math.min(minDist, dist);
                        return; // 다른 섬에 도달하면 즉시 종료
                    }
                }
            }
        }
    }
}
