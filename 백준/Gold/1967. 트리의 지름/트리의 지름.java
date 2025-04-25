import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int child;
        int cost;

        Node(int child, int cost) {
            this.child = child;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{child: " + child + ", cost: " + cost + "}";
        }
    }
    static int N;
    static List<List<Node>> graph;
    static int[] dist;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 1; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                    dist = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, dist));
            graph.get(end).add(new Node(start, dist));
        }
        int farthestFrom1 = dijkstra(1);
        int farthestFromPrevFarthest = dijkstra(farthestFrom1);
        System.out.println(dist[farthestFromPrevFarthest]);
    }

    static private int dijkstra(int start) {
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));
        dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0;
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (dist[now.child] < now.cost) continue;

            for (Node next : graph.get(now.child)) {
                if (dist[next.child] > dist[now.child] + next.cost) {
                    dist[next.child] = dist[now.child] + next.cost;
                    queue.offer(new Node(next.child, dist[next.child]));
                }
            }
        }

        int maxNode = Arrays.stream(dist).max().getAsInt();
        int idx = 0;
        for (int i = 0; i <= N; i++) {
            if (dist[i] == maxNode) {
                queue.offer(new Node(i, 0));
                idx = i;
            }
        }

        return idx;
    }
}
