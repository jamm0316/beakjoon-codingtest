import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int nextCity, cost;
        Node (int nextCity, int cost) {
            this.nextCity = nextCity;
            this.cost = cost;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Node{");
            sb.append("nextCity=").append(nextCity);
            sb.append(", cost=").append(cost);
            sb.append('}');
            return sb.toString();
        }
    }

    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<Node>> graph = new ArrayList<>(N + 1);
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            graph.get(from).add(new Node(to, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        while (!queue.isEmpty()) {
            Node curCity = queue.poll();

            //현재 비용이 기존에 저장된 비용보다 더 크면 skip
            if (curCity.cost > dist[curCity.nextCity]) continue;


            for (Node neighbor : graph.get(curCity.nextCity)) {
                if (dist[neighbor.nextCity] > dist[curCity.nextCity] + neighbor.cost) {
                    dist[neighbor.nextCity] = dist[curCity.nextCity] + neighbor.cost;
                    queue.offer(new Node(neighbor.nextCity, dist[neighbor.nextCity]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
