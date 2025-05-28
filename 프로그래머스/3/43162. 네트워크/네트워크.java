/*
1. static 전체 네트워크 수, visited
2. computers 순환하면서 
  - !visited[start]일 때 start를 dfs 인자로 넘겨줌
  - 이 조건에 들어오면 network++;
*/

import java.util.*;

class Solution {
    static int network;
    static boolean[] visited;
    static int N;
    static int[][] computers;
    public int solution(int n, int[][] input) {
        visited = new boolean[n];
        N = n;
        computers = input;
        
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                network++;
            }
        }
        
        return network;
    }
    
    static private void dfs(int start) {
        visited[start] = true;
        for (int i = 0; i < computers[start].length; i++) {
            if (!visited[i] && computers[start][i] == 1) {
                dfs(i);
            }
        }
    }
}