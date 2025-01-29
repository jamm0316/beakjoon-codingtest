import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int T = Integer.parseInt(st.nextToken());
        for (int t = 0; t < T; t++) {
            int nodes = Integer.parseInt(new StringTokenizer(br.readLine()).nextToken());
            int[] graph = new int[nodes + 1];
            boolean[] visited = new boolean[nodes + 1];

            //initialize
            st = new StringTokenizer(br.readLine());
            for (int node = 1; node <= nodes; node++) {
                graph[node] = Integer.parseInt(st.nextToken());
            }

            //dfs
            int count = 0;
            for (int node = 1; node <= nodes; node++) {
                if (!visited[node]) {
                    dfs(node, graph, visited);
                    count++;
                }
            }

            //result
            bw.write(String.valueOf(count));
            bw.newLine();
        }
        bw.close();
        br.close();
    }

    private static void dfs(int node, int[] graph, boolean[] visited) {
        visited[node] = true;
        int nextNode = graph[node];
        if (!visited[nextNode]) {
            dfs(nextNode, graph, visited);
        }
    }
}
