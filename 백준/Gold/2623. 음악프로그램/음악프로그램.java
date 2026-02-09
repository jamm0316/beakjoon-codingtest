import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<Integer>[] graph = new HashSet[N + 1];
        int[] indegree = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            graph[i] = new HashSet<>();
        }

        for (int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int cnt = Integer.parseInt(st.nextToken());

            int[] singers = new int[cnt];
            for (int j = 0; j < cnt; j++) {
                singers[j] = Integer.parseInt(st.nextToken());
            }

            for (int j = 0; j < cnt - 1; j++) {
                if (!graph[singers[j]].contains(singers[j + 1])) {
                    graph[singers[j]].add(singers[j + 1]);
                    indegree[singers[j + 1]]++;
                }
            }
        }

        Queue<Integer> queue = new LinkedList<>();

        for (int i = 1; i <= N; i++){
            if (indegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> result = new ArrayList<>();

        while (!queue.isEmpty()) {
            int cur = queue.poll();
            result.add(cur);
         
            for (int next : graph[cur]) {
            indegree[next]--;
                if (indegree[next] == 0) {
                    queue.offer(next);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        if (result.size() != N) {
            System.out.println(0);
        } else {
            for (int singer : result) {
                sb.append(singer).append('\n');
            }
            System.out.println(sb.toString());
        }
    }
}