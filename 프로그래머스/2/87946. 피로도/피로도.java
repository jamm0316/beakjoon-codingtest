import java.util.*;

class Solution {
    static int answer = 0;
    static int N;
    public int solution(int k, int[][] dungeons) {
        N = dungeons.length;
        boolean[] visited = new boolean[N];
        dfs(k, dungeons, visited, 0);
        return answer;
    }
    
    static private void dfs(int tired, int[][] dungeons, boolean[] visited, int count) {
        answer = Math.max(answer, count);
        
        for (int i = 0; i < N; i++) {
            int required = dungeons[i][0];
            int cost = dungeons[i][1];
            
            if (!visited[i] && tired >= required) {
                visited[i] = true;
                dfs(tired - cost, dungeons, visited, count + 1);
                visited[i] = false;
            }
        }
    }
}