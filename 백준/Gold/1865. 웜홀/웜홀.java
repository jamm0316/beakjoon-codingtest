import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int from, to, time;
        Edge(int from, int to, int time) {
            this.from = from;
            this.to = to;
            this.time = time;
        }
    }

    static int TC, N, M, W;
    static List<Edge> edges;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static final int INF = 500_000_000;

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();

        TC = Integer.parseInt(br.readLine());

        while (TC-- > 0) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            W = Integer.parseInt(st.nextToken());

            edges = new ArrayList<>();

            // 도로: 양방향
            for (int i = 0; i < M; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, time));
                edges.add(new Edge(to, from, time));
            }

            // 웜홀: 단방향, 음수 간선
            for (int i = 0; i < W; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int time = Integer.parseInt(st.nextToken());

                edges.add(new Edge(from, to, -time));
            }

            // **가상 시작점 0번에서 모든 노드로 0 비용 간선 추가**
            for (int i = 1; i <= N; i++) {
                edges.add(new Edge(0, i, 0));
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
            for (Edge edge : edges) {
                if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.time) {
                    dist[edge.to] = dist[edge.from] + edge.time;
                }
            }
        }

        // N+1번째에 갱신 발생 시 → 음수 사이클 존재
        for (Edge edge : edges) {
            if (dist[edge.from] != INF && dist[edge.to] > dist[edge.from] + edge.time) {
                return true;
            }
        }

        return false;
    }
}