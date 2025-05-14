import java.io.*;
import java.util.*;

public class P11779 {
    static class Node {
        int end, cost;
        Node(int end, int cost) {
            this.end = end;
            this.cost = cost;
        }
    }

    static int N, M;
    static List<List<Node>> graph;
    static int[] dist, prev;
    static final int INF = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        graph = new ArrayList<>();
        for (int i = 0; i <= N; i++) graph.add(new ArrayList<>());

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to   = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end   = Integer.parseInt(st.nextToken());

        dist = new int[N + 1];
        prev = new int[N + 1];
        Arrays.fill(dist, INF);

        dijkstra(start);

        // 결과 출력
        StringBuilder sb = new StringBuilder();
        sb.append(dist[end]).append('\n');

        LinkedList<Integer> path = new LinkedList<>();
        for (int at = end; at != 0; at = prev[at]) {
            path.addFirst(at);
        }

        sb.append(path.size()).append('\n');
        for (int city : path) {
            sb.append(city).append(' ');
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }

    static void dijkstra(int start) {
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.cost));
        pq.offer(new Node(start, 0));
        dist[start] = 0;

        while (!pq.isEmpty()) {
            Node now = pq.poll();
            if (dist[now.end] < now.cost) continue;

            for (Node next : graph.get(now.end)) {
                int newCost = dist[now.end] + next.cost;
                if (dist[next.end] > newCost) {
                    dist[next.end] = newCost;
                    prev[next.end] = now.end;
                    pq.offer(new Node(next.end, newCost));
                }
            }
        }
    }
}
