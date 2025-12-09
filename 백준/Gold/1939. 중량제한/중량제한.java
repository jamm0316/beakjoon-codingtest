import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.List;

public class Main {

    // 다리 정보를 저장할 노드 클래스
    static class Edge {
        int to;
        int weight;

        Edge(int to, int weight) {
            this.to = to;
            this.weight = weight;
        }
    }

    static int N, M;
    static List<Edge>[] graph;
    static int start, end;
    static int maxWeight = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N: 섬 개수, M: 다리 개수
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        // 인접 리스트 초기화
        graph = new ArrayList[N + 1];
        for (int i = 1; i <= N; i++) {
            graph[i] = new ArrayList<>();
        }

        // 다리 정보 입력
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken()); // 중량 제한

            graph[a].add(new Edge(b, c));
            graph[b].add(new Edge(a, c));

            // 가능한 최대 중량 갱신 (이분 탐색 upper bound로 사용)
            if (c > maxWeight) {
                maxWeight = c;
            }
        }

        // 시작 섬, 도착 섬
        st = new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        end = Integer.parseInt(st.nextToken());

        // 이분 탐색으로 최대 중량 찾기
        int result = binarySearchMaxWeight();
        System.out.println(result);
    }

    // 이분 탐색: 운반 가능한 최대 중량을 찾는 함수
    private static int binarySearchMaxWeight() {
        int left = 1;
        int right = maxWeight;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2; // 현재 시도해볼 중량

            // mid 무게로 start -> end 이동 가능한지 체크
            if (canMove(mid)) {
                // mid 무게로 이동 가능 → 더 무거운 무게도 가능한지 오른쪽으로 확장
                answer = mid;       // 일단 현재 mid는 가능한 값
                left = mid + 1;
            } else {
                // mid 무게로 이동 불가능 → 더 가벼운 무게로 줄이기
                right = mid - 1;
            }
        }

        return answer;
    }

    // BFS로 "weight 이상을 버티는 다리만 사용해서" start에서 end까지 갈 수 있는지 체크
    private static boolean canMove(int weight) {
        boolean[] visited = new boolean[N + 1];
        Queue<Integer> queue = new ArrayDeque<>();

        queue.offer(start);
        visited[start] = true;

        while (!queue.isEmpty()) {
            int current = queue.poll();

            // 도착 섬에 도달하면 성공
            if (current == end) {
                return true;
            }

            // 현재 섬에서 갈 수 있는 다음 섬들을 탐색
            for (Edge edge : graph[current]) {
                // 1) 아직 방문하지 않았고
                // 2) 이 다리가 현재 시도 중량(weight) 이상을 버틸 수 있을 때만 사용
                if (!visited[edge.to] && edge.weight >= weight) {
                    visited[edge.to] = true;
                    queue.offer(edge.to);
                }
            }
        }

        // 큐가 빌 때까지 end에 도달하지 못했으면 실패
        return false;
    }
}