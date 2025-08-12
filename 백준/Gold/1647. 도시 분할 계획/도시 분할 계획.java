import java.io.*;
import java.util.*;

// BOJ 1647 도시 분할 계획
public class Main {
    static class Edge {
        int a, b, w;
        Edge(int a, int b, int w) { this.a = a; this.b = b; this.w = w; }
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
            do { c = read(); } while (c <= ' ');    // skip spaces
            if (c == '-') { sgn = -1; c = read(); }
            while (c > ' ') { val = val * 10 + (c - '0'); c = read(); }
            return val * sgn;
        }
    }

    static int[] parent, size;

    static int find(int x) {
        while (x != parent[x]) {
            parent[x] = parent[parent[x]]; // path halving
            x = parent[x];
        }
        return x;
    }

    static boolean union(int a, int b) {
        a = find(a); b = find(b);
        if (a == b) return false;
        if (size[a] < size[b]) { int t = a; a = b; b = t; }
        parent[b] = a;
        size[a] += size[b];
        return true;
    }

    public static void main(String[] args) throws Exception {
        FastScanner fs = new FastScanner(System.in);
        int N = fs.nextInt();
        int M = fs.nextInt();

        Edge[] edges = new Edge[M];
        for (int i = 0; i < M; i++) {
            int a = fs.nextInt();
            int b = fs.nextInt();
            int w = fs.nextInt();
            edges[i] = new Edge(a, b, w);
        }

        Arrays.sort(edges, Comparator.comparingInt(e -> e.w));

        parent = new int[N + 1];
        size = new int[N + 1];
        for (int i = 1; i <= N; i++) { parent[i] = i; size[i] = 1; }

        long sum = 0;          // 안전하게 long
        int maxEdge = 0;
        int picked = 0;

        for (Edge e : edges) {
            if (union(e.a, e.b)) {
                sum += e.w;
                if (e.w > maxEdge) maxEdge = e.w;
                if (++picked == N - 1) break; // MST 완성
            }
        }

        System.out.println(sum - maxEdge);
    }
}