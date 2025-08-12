import java.io.*;
import java.util.*;

public class Main {
    static class Edge {
        int start, end, cost;

        Edge(int start, int end, int cost) {
            this.start = start;
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M;
    static Edge[] edges;
    static int[] parent, size;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        edges = new Edge[M];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            edges[i] = new Edge(start, end, cost);
        }

        Arrays.sort(edges, Comparator.comparing(e -> e.cost));

        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            parent[i] = i;
            size[i] = 1;
        }

        long sum = 0;
        int maxEdge = 0;
        int picked = 0;

        for (Edge e : edges) {
            if (union(e.start, e.end)) {
                sum += e.cost;
                if (e.cost > maxEdge) maxEdge = e.cost;
                if (++picked == N -1) break;
            }
        }

        System.out.println(sum - maxEdge);
    }

    static boolean union(int start, int end) {
        start = find(start);
        end = find(end);

        if (start == end) return false;
        if (size[start] < size[end]) {
            int t = start;
            start = end;
            end = t;
        }
        parent[end] = start;
        size[start] += size[end];
        return true;
    }

    static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]];
            x = parent[x];
        }
        return x;
    }
}
