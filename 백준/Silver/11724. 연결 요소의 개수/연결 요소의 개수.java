import java.util.*;
import java.io.*;

public class Main {
    static List<List<Integer>> graph;
    static List<Boolean> visited;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        graph = new ArrayList<>();
        visited = new ArrayList<>();
        int links = 0;

        // initialize Array
        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
            visited.add(false);
        }

        // initialize Graph
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        // dfs
        for (int i = 1; i <= N; i++) {
            if (!visited.get(i)) {
                visited.set(i, true);
                findUnvisitedLinks(i);
                links++;
            }
        }

        System.out.println(links);
    }

    private static void findUnvisitedLinks(int start) {
        for (int neighbor : graph.get(start)) {
            if (!visited.get(neighbor)) {
                visited.set(neighbor, true);
                findUnvisitedLinks(neighbor);
            }
        }
    }
}
