import java.io.*;
import java.util.*;

public class Main {

    static int N, K;
    static int dist[] = new int[100_000 + 1];
    static int parent[] = new int[100_000 + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        sb.append(dist[K]).append('\n');

        List<Integer> path = new ArrayList<>();
        int current = K;
        while (current != N) {
            path.add(current);
            current = parent[current];
        }
        path.add(N);
        Collections.reverse(path);

        for (int p : path) {
            sb.append(p).append(" ");
        }

        System.out.println(sb);
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