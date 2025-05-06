import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int to, dist;

        Node(int to, int dist) {
            this.to = to;
            this.dist = dist;
        }
    }

    static int V;
    static List<List<Node>> graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        V = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < V; i++) {
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
        Node result = bfs(farNode.to);
        System.out.println(result.dist);
    }

    private static Node bfs(int start) {
        boolean[] visited = new boolean[V + 1];
        int[] dist = new int[V + 1];
        Queue<Integer> queue = new LinkedList<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (Node next : graph.get(now)) {
                if (!visited[next.to]) {
                    visited[next.to] = true;
                    dist[next.to] = dist[now] + next.dist;
                    queue.offer(next.to);
                }
            }
        }

        int maxDist = 0;
        int maxNode = start;
        for (int i = 1; i <= V; i++) {
            if (dist[i] > maxDist) {
                maxDist = dist[i];
                maxNode = i;
            }
        }

        return new Node(maxNode, maxDist);
    }
}