import java.io.*;
import java.util.*;

public class P16234 {
    static int N, L, R, moveDays, maxTeam;
    static List<List<City>> map = new ArrayList<>();
    static boolean[][] visited;
    static boolean isMove = true;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    static class City {
        int x, y, population;

        public City(int x, int y, int population) {
            this.x = x;
            this.y = y;
            this.population = population;
        }

        @Override
        public String toString() {
            return "City(" + x + ", " + y + "){인구수: " + population + "}";
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

        while (isMove) {
            isMove = false;
            for (int i = 0; i < N; i++) {
                Arrays.fill(visited[i], false);
            }

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (!visited[i][j]) {
                        List<City> union = findUnion(i, j);
                        if (union.size() > 1) {
                            movePopulation(union);
                            isMove = true;
                        }
                    }
                }
            }

            if (!isMove) break;
            moveDays++;
        }
        System.out.println(moveDays);
    }

    private static List<City> findUnion(int x, int y) {
        List<City> union = new ArrayList<>();
        Queue<City> queue = new LinkedList<>();

        union.add(map.get(x).get(y));
        queue.offer(map.get(x).get(y));
        visited[x][y] = true;

        while (!queue.isEmpty()) {
            City now = queue.poll();
            for (int i = 0; i < 4; i++) {
                int nx = now.x + dx[i];
                int ny = now.y + dy[i];
                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    City next = map.get(nx).get(ny);
                    int diff = Math.abs(now.population - next.population);
                    if (L <= diff && diff <= R) {
                        queue.offer(map.get(nx).get(ny));
                        union.add(map.get(nx).get(ny));
                        visited[nx][ny] = true;
                    }
                }
            }
        }

        return union;
    }

    private static void movePopulation(List<City> union) {
        int totalPopulation = 0;
        for (City c : union) {
            totalPopulation += c.population;
        }

        int avgPopulation = totalPopulation / union.size();
        for (City c : union) {
            c.population = avgPopulation;
        }
    }
}
