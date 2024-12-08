import java.util.*;
import java.io.*;

public class Main {
    static int N;
    static int[][] cost;
    static boolean[] visited;
    static int minCost;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][N];
        visited = new boolean[N];
        minCost = Integer.MAX_VALUE;

        for (int i = 0; i < N; i++) {
            String[] location = br.readLine().split(" ");
            cost[i] = Arrays.stream(location)
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        visited[0] = true;
        backtrack(0, 0, 1, 0);
        
        System.out.println(minCost);
    }

    private static void backtrack(int curCity, int curCost, int count, int start) {
        // 다음으로 넘어가기 전 현재 최소보다 크면 탐색안함
        if (curCost >= minCost) {
            return;
        }

        // 그렇지 않으면 모든 조건 만족한다는 조건 하에 최소값 갱신
        if (count == N) {
            if (cost[curCity][start] != 0) {
                minCost = Math.min(curCost + cost[curCity][start], minCost);
            }
            return;
        }

        for (int nextCity = 0; nextCity < N; nextCity++) {
            if (!visited[nextCity] && cost[curCity][nextCity] != 0) {
                visited[nextCity] = true;
                backtrack(nextCity, curCost + cost[curCity][nextCity], count + 1, start);

                //다음 계산을 위해 초기화
                visited[nextCity] = false;
            }
        }
    }
}
