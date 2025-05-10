import java.io.*;
import java.util.*;

public class P12851 {
    static class Node {
        int pos;
        int time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
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
        queue.offer(new Node(N, 0));
        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        visited[N] = 0;
        minTime = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            // 현재 시간이 최소 시간보다 크면 더 이상 진행 안함.
            if (now.time > minTime) continue;

            // 현재 위치가 도달 위치라면,
            // 현재 시간이 minTime 보다 작으면 cases = 1
            // 현재 시간이 minTime과 같으면 cases++
            if (now.pos == K) {
                if (now.time < minTime) {
                    minTime = now.time;
                    cases = 1;
                } else if (now.time == minTime) {
                    cases++;
                }
                continue;
            }

            // +1, -1, *2 순환
            for (int next : new int[]{now.pos - 1, now.pos + 1, now.pos * 2}) {
                // 범위 밖이면 continue
                if (next < 0 || 100_000 < next) continue;

                // 방문한 적 없거나, 방문 노드의 시간이 현재 + 1보다 크거나 같으면 검증
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
