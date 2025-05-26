/**
 * 1. parent[] dp를 만들어 해당 노드들의 부모를 찾아낸다.
 * 2. tree는 양방향으로 만든다.
 * 3. bfs를 이용해 root를 1로 주고, !visited인 노드를 찾아 다니면서 자식노드의 dp[index]에 부모 노드를 넣어준다.*/

import java.io.*;
import java.util.*;

public class Main {
    static StringBuilder sb = new StringBuilder();
    static int N;
    static List<List<Integer>> tree = new ArrayList<>();
    static boolean[] visited;
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        N = Integer.parseInt(br.readLine());
        visited = new boolean[N + 1];
        parent = new int[N + 1];

        for (int i = 0; i <= N; i++) {
            tree.add(new ArrayList<>());
        }

        for (int i = 0; i < N - 1; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            tree.get(start).add(end);
            tree.get(end).add(start);
        }
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : tree.get(now)) {
                if (!visited[next]) {
                    visited[next] = true;
                    parent[next] = now;
                    queue.offer(next);
                }
            }
        }

        for (int i = 2; i <= N; i++) {
            sb.append(parent[i]).append('\n');
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
