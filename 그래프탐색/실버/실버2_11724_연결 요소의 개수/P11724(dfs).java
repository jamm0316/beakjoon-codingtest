import java.util.*;
import java.io.*;

public class Main {
    static List<List<Integer>> graph;
    static boolean[] visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>(N + 1);
        visited = new boolean[N + 1];
        int links = 0;

        // 그래프와 방문 배열 초기화
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }

        // 간선정보 저장
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // dfs
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) {
                visitConnectedComponentUsingDfs(i);
                links++;
            }
        }

        System.out.println(links);
    }

    private static void visitConnectedComponentUsingDfs(int start) {
        visited[start] = true;
        for (int neighbor : graph.get(start)) {
            if (!visited[neighbor]) {
                visited[neighbor] = true;
                visitConnectedComponentUsingDfs(neighbor);
            }
        }
    }
}
