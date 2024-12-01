import java.util.*;
import java.io.*;

public class Main {
    static int nodes;
    static int links;
    static int startNode;
    static List<Integer>[] graph;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        nodes = Integer.parseInt(st.nextToken());
        links = Integer.parseInt(st.nextToken());
        startNode = Integer.parseInt(st.nextToken());
        graph = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < links; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a].add(b);
            graph[b].add(a); // 양방향 그래프
        }

        for (int i = 1; i <= nodes; i++) {
            graph[i].sort(Comparator.naturalOrder());
        }

        boolean[] visited = new boolean[nodes + 1];

        display(dfs(startNode, new ArrayList<>(), visited));

        visited = new boolean[nodes + 1];
        display(bfs(startNode, visited));


    }

    private static List<Integer> dfs(int nextNode, List<Integer> route, boolean[] visited) {
        visited[nextNode] = true;
        route.add(nextNode);

        for (int neighbor : graph[nextNode]) {
            if (!visited[neighbor]) {
                dfs(neighbor, route, visited);
            }
        }
        return route;
    }

    private static List<Integer> bfs(int nextNode, boolean[] visited) {
        List<Integer> route = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        queue.add(nextNode);
        visited[nextNode] = true;

        while (!queue.isEmpty()) {
            int currentNode = queue.poll();
            route.add(currentNode);

            for (int neighbor : graph[currentNode]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
        return route;
    }


        private static void display(List<Integer> finalGraph) {
        String[] array = finalGraph.stream()
                .map(String::valueOf)
                .toArray(String[]::new);

        System.out.println(String.join(" ", array));
    }
}
