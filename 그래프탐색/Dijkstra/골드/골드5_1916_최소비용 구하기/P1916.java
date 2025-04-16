import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int nextCity, cost;

        Node(int nextCity, int cost) {
            this.nextCity = nextCity;
            this.cost = cost;
        }
    }
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());  //도시 갯수
        int M = Integer.parseInt(br.readLine());  //버스 노선 수

        List<List<Node>> busGraph = new ArrayList<>(N + 1);

        //graph 초기화
        for (int i = 0; i <= N; i++) {
            busGraph.add(new ArrayList<>());
        }

        //graph 값 받기
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int startNode = Integer.parseInt(st.nextToken());
            int endNode = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            busGraph.get(startNode).add(new Node(endNode, cost));
        }

        //시, 종착점 초기화
        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

				//dist 초기화
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

				//dijkstra 구현(PriorityQueue)
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));

        while (!queue.isEmpty()) {
            Node cur = queue.poll();
            if (cur.cost > dist[cur.nextCity]) continue;  //현재비용이 더 크면 skip

            for (Node next : busGraph.get(cur.nextCity)) {
                if (dist[next.nextCity] > dist[cur.nextCity] + next.cost) {
                    dist[next.nextCity] = dist[cur.nextCity] + next.cost;
                    queue.offer(new Node(next.nextCity, dist[next.nextCity]));
                }
            }
        }

        System.out.println(dist[end]);
    }
}
