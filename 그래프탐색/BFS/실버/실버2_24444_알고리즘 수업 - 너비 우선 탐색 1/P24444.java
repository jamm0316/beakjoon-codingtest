import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int links = Integer.parseInt(st.nextToken());
        int startNode = Integer.parseInt(st.nextToken());
        List<List<Integer>> graph = new ArrayList<>();
        boolean[] visited = new boolean[N + 1];

        for (int i = 0; i <= N; i++) {
            graph.add(i, new ArrayList<>());
        }

        for (int i = 1; i <= links; i++) {
            int[] EachLinks = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int start = EachLinks[0];
            int end = EachLinks[1];
            graph.get(start).add(end);
            graph.get(end).add(start);
        }

        for (List<Integer> each : graph) {
            each.sort(Comparator.naturalOrder());
        }

        int[] sequence = bfs(graph, visited, startNode, N);

        for (int i = 1; i < sequence.length; i++) {
            bw.write(String.valueOf(sequence[i]));
            if (i < sequence.length - 1) {
                bw.newLine();
            }
        }
        bw.close();
        br.close();
    }

    private static int[] bfs(List<List<Integer>> graph, boolean[] visited, int startNode, int N) {
        Queue<Integer> queue = new LinkedList<>();
        int count = 1;
        int[] sequence = new int[N + 1];

        //initialize
        sequence[startNode] = count;
        queue.offer(startNode);
        visited[startNode] = true;

        //bfs
        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            for (int neighbor : graph.get(curNode)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    sequence[neighbor] = ++count;
                    queue.offer(neighbor);
                }
            }
        }
        return sequence;
    }
}
