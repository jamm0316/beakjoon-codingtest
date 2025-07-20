import java.io.*;
import java.util.*;

public class Main {
    static int N, L, R, moveDays;
    static List<List<City>> map = new ArrayList<>();
    static boolean[][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class City {
        int x, y, population;

        public City(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        L = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            map.add(new ArrayList<>());
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                int population = Integer.parseInt(st.nextToken());
                map.get(i).add(new City(i, j, population));
            }
        }

        while (true) {
            boolean isMoved = false;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<City> union = bfs(i, j);
                        if (union.size() > 1) { // 연합이 2개 이상일 때만 인구 이동
                            redistributePopulation(union);
                            isMoved = true;
                        }
                    }
                }
            }

            if (!isMoved) break;
            moveDays++;
        }
        System.out.println(moveDays);
    }

    private static List<City> bfs(int x, int y) {
        Queue<City> queue = new LinkedList<>();
        List<City> union = new ArrayList<>();

        queue.offer(map.get(x).get(y));
        union.add(map.get(x).get(y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            City now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    int diff = Math.abs(now.population - map.get(nx).get(ny).population);
                    if (L <= diff && diff <= R) {
                        visited[nx][ny] = true;
                        queue.offer(map.get(nx).get(ny));
                        union.add(map.get(nx).get(ny));
                    }
                }
            }
        }
        return union;
    }

    private static void redistributePopulation(List<City> union) {
        int totalPop = 0;
        for (City c : union) {
            totalPop += c.population;
        }
        int avgPop = totalPop / union.size();
        for (City c : union) {
            c.population = avgPop;
        }
    }
}