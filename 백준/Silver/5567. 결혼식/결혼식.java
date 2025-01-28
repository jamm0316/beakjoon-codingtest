import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt((new StringTokenizer(br.readLine())).nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        int result = bfs(graph, visited);
        bw.write(String.valueOf(result));
        bw.close();
        br.close();
    }

    private static int bfs (List<List<Integer>> graph, boolean[] visited) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});
        visited[1] = true;
        int count = 0;

        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int curNode = cur[0];
            int curDepth = cur[1];

            List<Integer> curList = graph.get(curNode);
            curDepth++;
            for (int i : curList) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (curDepth <= 2) {
                        count++;
                    }
                    queue.offer(new int[]{i, curDepth});
                }
            }
        }
        return count;
    }
}
