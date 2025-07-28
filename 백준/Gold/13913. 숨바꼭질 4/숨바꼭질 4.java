import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    static int[] dist = new int[100001];
    static int[] parent = new int[100001];
    static final int[] dx = {-1, 1, 2};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        bfs();

        // 시간 출력
        sb.append(dist[K]).append('\n');

        // 경로 추적 (역순으로 넣고, 출력할 땐 정순)
        List<Integer> path = new ArrayList<>();
        int current = K;
        while (current != N) {
            path.add(current);
            current = parent[current];
        }
        path.add(N); // 시작점 포함
        Collections.reverse(path);

        for (int p : path) {
            sb.append(p).append(" ");
        }

        System.out.println(sb);
    }

    static void bfs() {
        Queue<Integer> queue = new LinkedList<>();
        Arrays.fill(dist, -1);
        dist[N] = 0;

        queue.offer(N);

        while (!queue.isEmpty()) {
            int now = queue.poll();

            for (int i = 0; i < 3; i++) {
                int next = (i == 2) ? now * dx[i] : now + dx[i];

                if (next >= 0 && next <= 100000 && dist[next] == -1) {
                    dist[next] = dist[now] + 1;
                    parent[next] = now;
                    queue.offer(next);

                    if (next == K) return;
                }
            }
        }
    }
}