import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(st.nextToken());

        for (int t = 0; t < T; t++) {
            st = new StringTokenizer(br.readLine());
            int countries = Integer.parseInt(st.nextToken());
            int airPlane = Integer.parseInt(st.nextToken());

            List<List<Integer>> graph = new ArrayList<>();
            for (int i = 0; i <= countries; i++) {
                graph.add(new ArrayList<>());
            }

            for (int i = 0; i < airPlane; i++) {
                st = new StringTokenizer(br.readLine());
                int start = Integer.parseInt(st.nextToken());
                int destination = Integer.parseInt(st.nextToken());
                graph.get(start).add(destination);
                graph.get(destination).add(start);
            }

            boolean[] visited = new boolean[countries + 1];
            int result = bfs(1, graph, visited);

            bw.write(String.valueOf(result));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static int bfs(int start, List<List<Integer>> graph, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int result = 0;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            for (int country : graph.get(current)) {
                if (!visited[country]) {
                    visited[country] = true;
                    queue.offer(country);
                    result++;
                }
            }
        }
        return result;
    }
}
