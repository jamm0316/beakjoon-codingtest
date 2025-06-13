import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int next;
        int cost;

        Node(int next, int cost) {
            this.next = next;
            this.cost = cost;
        }

        @Override
        public String toString() {
            return "{next: " + next + ", cost: " + cost + "}";
        }
    }
    static StringTokenizer st;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());
        List<List<Node>> busInfo = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            busInfo.add(new ArrayList<>());
        }

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken()), end = Integer.parseInt(st.nextToken()),
                    cost = Integer.parseInt(st.nextToken());
            busInfo.get(start).add(new Node(end, cost));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken()), end  = Integer.parseInt(st.nextToken());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, Integer.MAX_VALUE);
        dp[0] = 0;
        dp[start] = 0;
        Queue<Node> queue = new PriorityQueue<>((a, b) -> a.cost - b.cost);
        queue.offer(new Node(start, 0));
        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.cost > dp[now.next]) continue;
            for (Node nextNode : busInfo.get(now.next)) {
                if (dp[nextNode.next] > nextNode.cost + now.cost) {
                    dp[nextNode.next] = nextNode.cost + now.cost;
                    queue.offer(new Node(nextNode.next, dp[nextNode.next]));
                }
            }
        }
        System.out.println(dp[end]);
    }
}
