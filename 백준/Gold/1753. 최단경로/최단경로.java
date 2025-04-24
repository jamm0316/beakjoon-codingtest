import java.io.*;
import java.util.*;

public class Main {
    static class Node{
        int endVertex;
        int cost;
        Node(int end, int cost) {
            this.endVertex = end;
            this.cost = cost;
        }

        @Override
        public String toString(){
            return "Node{endVertex: " + endVertex + ", cost: " + cost + "}";
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int vertex = Integer.parseInt(st.nextToken()), edge = Integer.parseInt(st.nextToken());
        int root = Integer.parseInt(br.readLine());

        //그래프 초기화
        List<List<Node>> graph = new ArrayList<>();
        for (int i = 0; i <= vertex; i++) {
            graph.add(new ArrayList<>());
        }

        //단방향 그래프 초기화
        for (int i = 0; i < edge; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()), cost = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, cost));
        }

        //dijkstra
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(root, 0));
        int[] dist = new int[vertex + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[root] = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (dist[now.endVertex] < now.cost) continue;

            for (Node next : graph.get(now.endVertex)) {
                if (dist[next.endVertex] > dist[now.endVertex] + next.cost) {
                    dist[next.endVertex] = dist[now.endVertex] + next.cost;
                    queue.offer(new Node(next.endVertex, dist[next.endVertex]));
                }
            }
        }

        for (int i = 1; i < dist.length; i++) {
            if (dist[i] == Integer.MAX_VALUE) {
                sb.append("INF");
            } else {
                sb.append(dist[i]);
            }
            sb.append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
