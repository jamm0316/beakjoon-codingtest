import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int next;

        Node(int next) {
            this.next = next;
        }

        @Override
        public String toString() {
            return "Node{next : " + next + "}";
        }
    }

    static List<List<Node>> graph = new ArrayList<>();
    static int[] subtreeSize;
    static boolean[] visited;
    static int N, R, Q;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());
        Q = Integer.parseInt(st.nextToken());
        visited = new boolean[N + 1];
        subtreeSize = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end));
            graph.get(end).add(new Node(start));
        }

        dfs(R);

        for (int i = 0; i < Q; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            sb.append(subtreeSize[node]).append("\n");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static int dfs(int node) {
        visited[node] = true;
        int size = 1;

        for (Node child : graph.get(node)) {
            if (!visited[child.next]) {
                size += dfs(child.next);
            }
        }

        subtreeSize[node] = size;
        return size;
    }
}
