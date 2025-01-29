import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int[] graph = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        bw.write(String.valueOf(bfs(N, graph)));
        bw.close();
        br.close();
    }

    private static int bfs(int N, int[] graph) {
        boolean[] visited = new boolean[N];
        Queue<int[]> queue = new LinkedList<>();

        queue.offer(new int[]{0, 0});
        visited[0] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int position = current[0];
            int jumps = current[1];

            if (position == N - 1) {
                return jumps;
            }

            for (int nextPos = position + 1;
                 nextPos <= position + graph[position] && nextPos < N;
                 nextPos++) {
                if (!visited[nextPos]) {
                    visited[nextPos] = true;
                    queue.offer(new int[]{nextPos, jumps + 1});
                }
            }
        }
        return -1;
    }
}
