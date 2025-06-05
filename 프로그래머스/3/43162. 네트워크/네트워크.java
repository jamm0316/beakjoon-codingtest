/*

*/
import java.util.*;

class Solution {
    static boolean[] visited;
    static int result;
    public int solution(int n, int[][] computers) {
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i, computers);
                result++;
            }
        }
        return result;
    }
    
    private void dfs(int start, int[][] computers) {
        visited[start] = true;
        for (int i = 0; i < computers[start].length; i++) {
            if (!visited[i] && computers[start][i] == 1) {
                dfs(i, computers);
            }
        }
    }
}
