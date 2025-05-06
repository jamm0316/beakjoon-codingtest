import java.io.*;
import java.util.*;

public class P1167 {
    static class Node {
        int end;
        int dist;

        Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        public String toString() {
            return "Node{next: " + end + ", dist: " + dist + "}";
        }
    }

    static int N;
    static List<List<Node>> graph = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            while (true) {
                int to = Integer.parseInt(st.nextToken());
                if (to == -1) break;
                int dist = Integer.parseInt(st.nextToken());
                graph.get(from).add(new Node(to, dist));
            }
        }
        Node farNode = bfs(1);
        Node result = bfs(farNode.end);
        System.out.println(result.dist);
    }

    private static Node bfs(int start) {
        boolean[] visited = new boolean[N + 1];
        int[] dp = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node next : graph.get(now)) {
                if (!visited[next.end]) {
                    visited[next.end] = true;
                    dp[next.end] = dp[now] + next.dist;
                    queue.offer(next.end);
                }
            }
        }

        int maxDist = 0;
        int maxNode = start;
        for (int i = 0; i <= N; i++) {
            if (dp[i] > maxDist) {
                maxDist = dp[i];
                maxNode = i;
            }
        }
        return new Node(maxNode, maxDist);
    }
}
