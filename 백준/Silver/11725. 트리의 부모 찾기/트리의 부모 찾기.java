import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static List<List<Integer>> tree;
    static boolean[] visited;
    static int[] parent;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        tree = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        parent = new int[N + 1];
        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            tree.get(start).add(end);
            tree.get(end).add(start);
        }
        bfs(1);

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void bfs(int root) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(root);
        visited[root] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = now;
                    queue.offer(next);
                }
            }
        }
    }
}
