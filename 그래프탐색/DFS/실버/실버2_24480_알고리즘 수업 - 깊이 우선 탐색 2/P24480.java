import java.io.*;
import java.util.*;

public class Main {
    static boolean[] visited;
    static int[] sequence;
    static int depth = 1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int nodes = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        visited = new boolean[nodes + 1];
        sequence = new int[nodes + 1];

        //initialize graph nodes
        for (int i = 0; i <= nodes; i++) {
            graph.add(new ArrayList<>());
        }

        //initialize graph links
        for (int i = 0; i < links; i++) {
            int[] input = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = input[0];
            int end = input[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        //sorting desc
        for (int i = 0; i <= nodes; i++) {
            graph.get(i).sort(Comparator.reverseOrder());
        }

        //dfs
        dfs(graph, startNode);
        for (int i = 1; i < sequence.length; i++) {
            bw.write(String.valueOf(sequence[i]));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static void dfs(List<List<Integer>> graph, int node) {
        visited[node] = true;
        sequence[node] = depth++;

        for (int next : graph.get(node)) {
            if (!visited[next]) {
                dfs(graph, next);
            }
        }
    }
}
