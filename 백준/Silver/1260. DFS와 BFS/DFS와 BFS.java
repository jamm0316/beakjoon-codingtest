import java.util.*;

public class Main {
    static ArrayList<Integer>[] graph;
    static boolean[] visited;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int nodes = sc.nextInt();
        int edges = sc.nextInt();
        int startNode = sc.nextInt();

        graph = new ArrayList[nodes + 1];
        for (int i = 1; i <= nodes; i++) {
            graph[i] = new ArrayList<>();
        }

        for (int i = 0; i < edges; i++) {
            int a = sc.nextInt();
            int b = sc.nextInt();
            graph[a].add(b);
            graph[b].add(a);
        }

        for (int i = 1; i <= nodes; i++) {
            Collections.sort(graph[i]);
        }

        visited = new boolean[nodes + 1];
        ArrayList<Integer> dfsResult = new ArrayList<>();
        dfs(startNode, dfsResult);

        System.out.println(String.join(" ", dfsResult.stream()
                .map(String::valueOf)
                .toArray(String[]::new)));

        visited = new boolean[nodes + 1];
        ArrayList<Integer> bfsResult = bfs(startNode);

        System.out.println(String.join(" ", bfsResult.stream()
                .map(String::valueOf)
                .toArray(String[]::new)));
    }

    private static void dfs(int currentNode, ArrayList<Integer> route) {
        visited[currentNode] = true;
        route.add(currentNode);

        for (int neighbor : graph[currentNode]) {
            if (!visited[neighbor]) {
                dfs(neighbor, route);
            }
        }
    }

    private static ArrayList<Integer> bfs(int startNode) {
        ArrayList<Integer> route = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();
        visited[startNode] = true;
        queue.add(startNode);

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
}