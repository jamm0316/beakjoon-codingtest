import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 학생 수
        int M = Integer.parseInt(st.nextToken()); // 비교 결과 수

        List<Integer>[] graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        int[] indegree = new int[N + 1];

        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            graph[A].add(B);   // A -> B
            indegree[B]++;     // B로 들어오는 간선 1 증가
        }

        Deque<Integer> queue = new ArrayDeque<>();

        for (int i = 1; i <= N; i++) {
            if (indegree[i] == 0) {
                queue.add(i);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!queue.isEmpty()) {
            int current = queue.poll(); // 앞에 설 수 있는 학생 하나 꺼내기
            sb.append(current).append(' ');

            for (int next : graph[current]) {
                indegree[next]--;          // current를 처리했으니, next의 indegree 감소
                if (indegree[next] == 0) { // 이제 앞에 설 사람이 없으면
                    queue.add(next);       // 큐에 추가
                }
            }
        }

        System.out.println(sb.toString().trim());
    }
}