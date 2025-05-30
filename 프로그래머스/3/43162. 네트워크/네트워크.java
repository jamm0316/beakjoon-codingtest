/*
1. dfs로 모든 노드 확인
2. 순회할 때 visited를 기준으로 있으면 dfs 없으면 dfs 안함
3. network++ 실시
*/
import java.util.*;

class Solution {
    static int N, network;
    static int[][] computers;
    static boolean[] visited;
    public int solution(int n, int[][] input) {
        N = n;
        computers = input;
        visited = new boolean[N];
        
        for (int i = 0 ; i < N; i++) {
            if (!visited[i]) {
                dfs(i);
                network++;
            }
        }
        return network;
    }
    
    static private void dfs (int start) {
        for (int i=0; i < N; i++) {
            if (!visited[i] && computers[start][i] == 1) {
                visited[i] = true;
                dfs(i);
            }
        }
    }
}
