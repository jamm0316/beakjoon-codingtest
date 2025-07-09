import java.io.*;
import java.util.*;

public class P1197 {
    static class Node {
        int next;
        int cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "Node{next: " + next + ", cost: " + cost + "}";
        }
    }

    static List<List<Node>> tree = new ArrayList<>();
    static boolean[] visited;
    static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int V = Integer.parseInt(st.nextToken()), E = Integer.parseInt(st.nextToken());
        visited = new boolean[V + 1];

        for (int i = 0; i <= V; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                    cost = Integer.parseInt(st.nextToken());

            tree.get(start).add(new Node(end, cost));
            tree.get(end).add(new Node(start, cost));
        }

        System.out.println(prim(1));
    }

    private static int prim(int start) {
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));

        int totalCost = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();

            if (visited[now.next]) continue;
            visited[now.next] = true;
            totalCost += now.cost;

            for (Node nextNode : tree.get(now.next)) {
                if (!visited[nextNode.next]) {
                    queue.offer(new Node(nextNode.next, nextNode.cost));
                }
            }
        }
        return totalCost;
    }
}
