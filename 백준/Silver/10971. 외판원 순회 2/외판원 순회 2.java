import java.util.*;

public class Main {
    static int N;                 // 도시의 수
    static int[][] W;             // 비용 행렬
    static boolean[] visited;     // 방문 여부
    static int minCost = Integer.MAX_VALUE; // 최소 비용

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력 처리
        N = scanner.nextInt();
        W = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                W[i][j] = scanner.nextInt();
            }
        }
        visited = new boolean[N]; // 방문 배열 초기화
        
        visited[0] = true; // 시작 도시는 방문 처리
        dfs(0, 0, 1, 0);
        
        System.out.println(minCost);
    }

    static void dfs(int current, int cost, int count, int start) {
        if (cost >= minCost) {
            return;
        }

        if (count == N) {
            if (W[current][start] != 0) { // 출발점으로 돌아갈 수 있는 경우
                minCost = Math.min(minCost, cost + W[current][start]);
            }
            return;
        }

        for (int next = 0; next < N; next++) {
            if (!visited[next] && W[current][next] != 0) {
                visited[next] = true; // 방문 처리
                dfs(next, cost + W[current][next], count + 1, start);
                visited[next] = false; // 방문 해제 (백트래킹)
            }
        }
    }
}