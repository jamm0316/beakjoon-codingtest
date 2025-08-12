import java.io.*;
import java.util.*;

// BOJ 1647 - 도시 분할 계획 (Prim + List<List<Edge>>)
public class Main {
    static class Edge {
        int to, w;
        Edge(int to, int w) { this.to = to; this.w = w; }
    }

    static class FastScanner {
        private final InputStream in;
        private final byte[] buffer = new byte[1 << 16];
        private int ptr = 0, len = 0;
        FastScanner(InputStream is) { in = is; }
        private int read() throws IOException {
            if (ptr >= len) {
                len = in.read(buffer);
                ptr = 0;
                if (len <= 0) return -1;
            }
            return buffer[ptr++];
        }
        int nextInt() throws IOException {
            int c, sgn = 1, val = 0;
            do { c = read(); } while (c <= ' ');
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sgn;
        }
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();

        List<List<Edge>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            int a = fs.nextInt(), b = fs.nextInt(), w = fs.nextInt();
            graph.get(a).add(new Edge(b, w));
            graph.get(b).add(new Edge(a, w));
        }

        boolean[] visited = new boolean[N + 1];
        PriorityQueue<Edge> pq = new PriorityQueue<>(Comparator.comparingInt(e -> e.w));

        // 시작 정점을 (1, 비용 0)으로 넣어 가상의 0원 간선으로 시작
        pq.offer(new Edge(1, 0));

        long sum = 0;
        int maxEdge = 0;
        int picked = 0;

        while (!pq.isEmpty() && picked < N) {
            Edge cur = pq.poll();
            int u = cur.to;
            if (visited[u]) continue;

            visited[u] = true;
            picked++;

            sum += cur.w;
            if (cur.w > maxEdge) maxEdge = cur.w;

            for (Edge nxt : graph.get(u)) {
                if (!visited[nxt.to]) pq.offer(nxt);
            }
        }

        // MST 비용에서 가장 큰 간선 제거 → 두 마을로 분할한 최소 유지비
        System.out.println(sum - maxEdge);
    }
}