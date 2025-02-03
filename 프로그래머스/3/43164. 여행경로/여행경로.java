import java.util.*;

class Solution {
    public List<String> solution(String[][] tickets) {
        Map<String, PriorityQueue<String>> graph = new HashMap<>();
        
        for (String[] ticket : tickets) {
            graph.putIfAbsent(ticket[0], new PriorityQueue<>());
            graph.get(ticket[0]).offer(ticket[1]);
        }

        List<String> route = new LinkedList<>();
        dfs(graph, "ICN", route);
        Collections.reverse(route);
        return route;
    }

    private void dfs(Map<String, PriorityQueue<String>> graph, String current, List<String> route) {
        PriorityQueue<String> destinations = graph.get(current);

        while (destinations != null && !destinations.isEmpty()) {
            String next = destinations.poll();
            dfs(graph, next, route);
        }

        route.add(current);
    }
}