import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int end;
        int dist;
        StringBuilder sb = new StringBuilder();

        Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }

        @Override
        public String toString() {
            sb.append("Node{end: ").append(end).append(", dist: ").append(dist).append("}");
            return sb.toString();
        }
    }

    static int N, M, X;
    static List<List<Node>> graph, reverseGraph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        //그래프 초기화
        graph = new ArrayList<>();
        reverseGraph = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            reverseGraph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                    dist = Integer.parseInt(st.nextToken());
            graph.get(start).add(new Node(end, dist));
            reverseGraph.get(end).add(new Node(start, dist));
        }

        int[] goParty = dijkstra(graph, X);
        int[] comeHome = dijkstra(reverseGraph, X);

        int max = 0;
        for (int i = 1; i <= N; i++) {
            max = Math.max(max, goParty[i] + comeHome[i]);
        }

        System.out.println(max);
    }

    static private int[] dijkstra(List<List<Node>> graph, int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (dist[now.end] < now.dist) continue;

            for (Node next : graph.get(now.end)) {
                if (dist[next.end] > now.dist + next.dist) {
                    dist[next.end] = now.dist + next.dist;
                    queue.offer(new Node((next.end), dist[next.end]));

                }
            }
        }
        return dist;
    }
}
