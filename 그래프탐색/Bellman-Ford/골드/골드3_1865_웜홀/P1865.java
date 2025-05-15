import java.io.*;
import java.util.*;

public class P1865 {
    static class City {
        int start, end, time;
        City(int start, int end, int time) {
            this.start = start;
            this.end = end;
            this.time = time;
        }
    }

    static int TC, N, M, W;
    static List<City> cities;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            cities = new ArrayList<>();

            // 도로: 양방향
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                cities.add(new City(start, end, time));
                cities.add(new City(end, start, time));
            }

            // 웜홀: 단방향, 음수 간선
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                cities.add(new City(start, end, -time));
            }

            // 가상 시작점 0번에서 모든 노드로 0 비용 간선 추가
            for (int i = 1; i <= N; i++) {
                cities.add(new City(0, i, 0));
            }

            sb.append(bellmanFord(N + 1) ? "YES" : "NO").append('\n');
        }

        System.out.print(sb);
    }

    static boolean bellmanFord(int totalNodes) {
        int[] dist = new int[totalNodes];
        Arrays.fill(dist, INF);
        dist[0] = 0; // 가상 시작점

        // N번 반복 (0 포함해서 totalNodes - 1)
        for (int i = 1; i < totalNodes; i++) {
            for (City city : cities) {
                if (dist[city.start] != INF && dist[city.end] > dist[city.start] + city.time) {
                    dist[city.end] = dist[city.start] + city.time;
                }
            }
        }

        // N+1번째에 갱신 발생 시 → 음수 사이클 존재
        for (City edge : cities) {
            if (dist[edge.start] != INF && dist[edge.end] > dist[edge.start] + edge.time) {
                return true;
            }
        }

        return false;
    }
}
