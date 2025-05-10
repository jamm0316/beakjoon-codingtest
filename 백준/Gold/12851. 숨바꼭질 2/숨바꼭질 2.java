import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int pos;
        int time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }

        @Override
        public String toString() {
            return "Node{pos: " + pos + ", time: " + time + "}";
        }
    }

    static int N, K, cases, minTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Node> queue = new LinkedList<>();
        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);

        queue.offer(new Node(N, 0));
        visited[N] = 0;
        minTime = Integer.MAX_VALUE;
        cases = 0;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            if (now.time > minTime) {
                continue;
            }

            if (now.pos == K) {
                if (now.time < minTime) {
                    minTime = now.time;
                    cases = 1;
                } else if (now.time == minTime) {
                    cases++;
                }
                continue;
            }
            for (int next : new int[]{now.pos + 1, now.pos - 1, now.pos * 2}) {
                if (next < 0 || 100_000 < next) continue;

                if (visited[next] == -1 || visited[next] >= now.time + 1) {
                    visited[next] = now.time + 1;
                    queue.offer(new Node(next, now.time + 1));
                }
            }
        }

        sb.append(minTime).append('\n').append(cases);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
