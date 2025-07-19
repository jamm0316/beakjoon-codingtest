package graph;

import java.io.*;
import java.util.*;

public class P1707 {
    static int[] color; //각 정점의 색 (0: 미방문, 1: 빨강, -1: 파랑)
    static List<List<Integer>> graph;
    static boolean isTrue;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        for (int i = 0; i < T; i++) {
            st = new StringTokenizer(br.readLine());
            int node = Integer.parseInt(st.nextToken());
            int link = Integer.parseInt(st.nextToken());

            //graph 초기화
            graph = new ArrayList<>();
            for (int k = 0; k <= node; k++) {
                graph.add(new ArrayList<>());
            }

            for (int j = 0; j < link; j++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int end = Integer.parseInt(st.nextToken());
                graph.get(start).add(end);
                graph.get(end).add(start);
            }

            color = new int[node + 1];
            isTrue = true;

            for (int k = 1; k <= node; k++) {
                if (color[k] == 0) {
                    bfs(k);
                }
            }

            sb.append(isTrue ? "YES" : "NO").append('\n');
        }
        System.out.print(sb.toString());
    }

    private static void bfs(int start) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        color[start] = 1;

        while (!queue.isEmpty()) {
            int now = queue.poll();
            for (int next : graph.get(now)) {
                if (color[next] == 0) {
                    queue.offer(next);
                    color[next] = -color[now];
                } else if (color[next] == color[now]) {
                    isTrue = false;
                    return;
                }
            }
        }
    }
}
