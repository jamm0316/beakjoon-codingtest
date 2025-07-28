import java.io.*;
import java.util.*;

public class Main {

    static int N, K, count;
    static int dist[] = new int[100_000 + 1];
    static int parent[] = new int[100_000 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        Stack<Integer> path = new Stack<>();
        for (int i = K; i != N; i = parent[i]) {
            path.push(i);
        }
        path.push(N);

        System.out.println(dist[K]);
        while (!path.isEmpty()) {
            System.out.print(path.pop() + " ");
        }
    }

    private static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(N);
        Arrays.fill(dist, -1);
        dist[N] = 0;

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int next : new int[]{now - 1, now + 1, now * 2}) {
                if (next >= 0 && next <= 100_000 && dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    parent[next] = now;
                    queue.add(next);

                    if (next == K) return;
                }
            }
        }
    }
}