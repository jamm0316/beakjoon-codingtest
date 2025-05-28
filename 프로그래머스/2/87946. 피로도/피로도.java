/*
1. 완전 탐색, dfs, 백트래킹
*/
import java.util.*;

class Solution {
    static int N, count, answer;
    static int[][] dungeons;
    static boolean[] visited;
    public int solution(int k, int[][] input) {
        dungeons = input;
        N = dungeons.length;
        visited = new boolean[N];
        dfs(k, 0);
        return answer;
    }
    
    static private void dfs(int tired, int count) {
        answer = Math.max(answer, count);
        
        for (int i =0; i < N; i++) {
            int required = dungeons[i][0];
            int cost = dungeons[i][1];
            if(!visited[i] && tired >= required){
                visited[i] = true;
                
                dfs(tired-cost, count + 1);
                visited[i] = false;
            }
        }
    }
}