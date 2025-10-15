import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int end;
        int dist;

        Node(int end, int dist) {
            this.end = end;
            this.dist = dist;
        }
    }
    static int N, E;
    static int V1, V2;
    static List<List<Node>> map = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N+1; i++) {
            map.add(new ArrayList<>());
        }

        for (int i = 0; i < E; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            int dist = Integer.parseInt(st.nextToken());
            map.get(start).add(new Node(end, dist));
            map.get(end).add(new Node(start, dist));
        }

        st = new StringTokenizer(br.readLine());
        V1 = Integer.parseInt(st.nextToken());
        V2 = Integer.parseInt(st.nextToken());

        //각 정점에서 부터 최소거리 dp 구하기
        int[] from1 = dijkstra(1);
        int[] fromV1 = dijkstra(V1);
        int[] fromV2 = dijkstra(V2);

        long path1 = (long) from1[V1] + fromV1[V2] + fromV2[N];  // 1 -> V1 -> V2 -> N
        long path2 = (long) from1[V2] + fromV2[V1] + fromV1[N];  // 1 -> V2 -> V1 -> N
        //둘 중 더 작은 값 출력
        long result = Math.min(path1, path2);

        //dijkstra에서 연결되지 않은 부분은 MAX_VALUE로 남아있기 떄문
        System.out.println(result >= Integer.MAX_VALUE ? -1 : result);
    }

    private static int[] dijkstra(int start) {
        int[] dist = new int[N + 1];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[start] = 0;

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> a.dist - b.dist);
        pq.offer(new Node(start, 0));  //현재 까지 노드, 누적 거리

        while (!pq.isEmpty()) {
            Node cur = pq.poll();
            //현재 계산한 최소거리 > dist에 등록된 최소거리보다 작을 때만 진행
            if (cur.dist > dist[cur.end]) continue;

            for (Node next : map.get(cur.end)) {
                //다음의 최소거리 > 현재의 최소거리 + 다음의 거리 일때만 진행
                if (dist[next.end] > dist[cur.end] + next.dist) {
                    dist[next.end] = dist[cur.end] + next.dist;  //다음 노드 최소 거리 갱신
                    pq.offer(new Node(next.end, dist[next.end]));  //다음 노드 탐색 진행
                }
            }
        }

        return dist;
    }
}
