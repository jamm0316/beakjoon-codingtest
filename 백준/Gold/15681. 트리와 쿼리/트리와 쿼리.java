import java.io.*;
import java.util.*;

public class Main {
    static int N, R, Q;
    static boolean[] visited;
    static int[] subtreeSize;
    static List<List<Integer>> graph = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        N = toInt(st.nextToken());
        R = toInt(st.nextToken());
        Q = toInt(st.nextToken());

        visited = new boolean[N + 1];
        subtreeSize = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = toInt(st.nextToken()), end = toInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            int root = toInt(br.readLine());
            sb.append(subtreeSize[root]).append("\n");
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int toInt(String str) {
        return Integer.parseInt(str);
    }

    private static int dfs(int node) {
        visited[node] = true;
        int size = 1;
        for (int next : graph.get(node)) {
            if (!visited[next]) {
                size += dfs(next);
            }
        }

        subtreeSize[node] = size;
        return size;
    }
}
