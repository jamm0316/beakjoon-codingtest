import java.util.*;
import java.io.*;

public class Main {
    static class Node implements Comparable<Node> {
        int vertex, cost;

        Node(int vertex, int cost) {
            this.vertex = vertex;
            this.cost = cost;
        }

        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }

    static int N, E;
    static List<List<Node>> graph;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
            graph.get(v).add(new Node(u, w)); // 양방향
        }

        st = new StringTokenizer(br.readLine());
        int v1 = Integer.parseInt(st.nextToken());
        int v2 = Integer.parseInt(st.nextToken());

        int[] from1 = dijkstra(1);
        int[] fromV1 = dijkstra(v1);
        int[] fromV2 = dijkstra(v2);

        long path1 = (long)from1[v1] + fromV1[v2] + fromV2[N];
        long path2 = (long)from1[v2] + fromV2[v1] + fromV1[N];

        long result = Math.min(path1, path2);
        System.out.println(result >= INF ? -1 : result);
    }

    static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, INF);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(start, 0));

        while (!pq.isEmpty()) {
            Node now = pq.poll();

            if (now.cost > dist[now.vertex]) continue;

            for (Node next : graph.get(now.vertex)) {
                if (dist[next.vertex] > dist[now.vertex] + next.cost) {
                    dist[next.vertex] = dist[now.vertex] + next.cost;
                    pq.offer(new Node(next.vertex, dist[next.vertex]));
                }
            }
        }

        return dist;
    }
}
