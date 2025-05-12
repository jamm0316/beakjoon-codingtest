import java.io.*;
import java.util.*;

public class Main {
    static class Shark {
        int x, y, size, eaten;

        Shark(int x, int y, int size, int eaten) {
            this.x = x;
            this.y = y;
            this.size = size;
            this.eaten = eaten;
        }
    }

    static class Fish implements Comparable<Fish> {
        int x, y, dist;

        Fish(int x, int y, int dist) {
            this.x = x;
            this.y = y;
            this.dist = dist;
        }

        @Override
        public int compareTo(Fish other) {
            if (this.dist != other.dist) return this.dist - other.dist;
            if (this.x != other.x) return this.x - other.x;
            return this.y - other.y;
        }
    }

    static int N, totalTime;
    static int[][] map;
    static Shark shark;
    static int[] dx = {-1, 0, 0, 1};
    static int[] dy = {0, -1, 1, 0};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if (map[i][j] == 9) {
                    shark = new Shark(i, j, 2, 0);
                    map[i][j] = 0;
                }
            }
        }

        while (true) {
            List<Fish> fishList = getFishList(map, shark);
            if (fishList.isEmpty()) break;

            Collections.sort(fishList);
            Fish target = fishList.get(0);

            totalTime += target.dist;
            map[target.x][target.y] = 0;
            shark.x = target.x;
            shark.y = target.y;
            shark.eaten++;

            if (shark.eaten == shark.size) {
                shark.size++;
                shark.eaten = 0;
            }
        }
        System.out.println(totalTime);
    }

    private static List<Fish> getFishList(int[][] map, Shark shark) {
        Queue<int[]> queue = new LinkedList<>();
        boolean[][] visited = new boolean[N][N];
        int[][] dist = new int[N][N];
        List<Fish> fishList = new ArrayList<>();

        queue.offer(new int[]{shark.x, shark.y});
        visited[shark.x][shark.y] = true;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int x = cur[0], y = cur[1];

            for (int i = 0; i < 4; i++) {
                int nx = x + dx[i];
                int ny = y + dy[i];

                if (nx < 0 || ny < 0 || nx >= N || ny >= N) continue;
                if (visited[nx][ny]) continue;
                if (map[nx][ny] > shark.size) continue;

                visited[nx][ny] = true;
                dist[nx][ny] = dist[x][y] + 1;
                queue.offer(new int[]{nx, ny});

                if (map[nx][ny] != 0 && map[nx][ny] < shark.size) {
                    fishList.add(new Fish(nx, ny, dist[nx][ny]));
                }
            }
        }
        return fishList;
    }
}
