import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[][] board;
    static boolean[][] visited;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        board = new int[N][M];
        
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        int time = 0;
        int lastCheese = 0;
        
        while (true) {
            // 외부 공기 탐색
            visited = new boolean[N][M];
            bfs(0, 0);
            
            // 녹을 치즈 찾기
            List<int[]> toMelt = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    if (board[i][j] == 1 && isExposed(i, j)) {
                        toMelt.add(new int[]{i, j});
                    }
                }
            }
            
            // 더 이상 녹을 치즈가 없으면 종료
            if (toMelt.isEmpty()) {
                break;
            }
            
            // 이번에 녹는 치즈 개수 저장 (마지막 시간 직전의 치즈 개수)
            lastCheese = toMelt.size();
            
            // 치즈 녹이기
            for (int[] pos : toMelt) {
                board[pos[0]][pos[1]] = 0;
            }
            
            time++;
        }
        
        System.out.println(time);
        System.out.println(lastCheese);
    }
    
    // (0,0)부터 BFS로 외부 공기 영역 표시
    static void bfs(int x, int y) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{x, y});
        visited[x][y] = true;
        
        while (!queue.isEmpty()) {
            int[] cur = queue.poll();
            int cx = cur[0];
            int cy = cur[1];
            
            for (int i = 0; i < 4; i++) {
                int nx = cx + dx[i];
                int ny = cy + dy[i];
                
                if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                    if (!visited[nx][ny] && board[nx][ny] == 0) {
                        visited[nx][ny] = true;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
        }
    }
    
    // 해당 치즈가 외부 공기와 접촉했는지 확인
    static boolean isExposed(int x, int y) {
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];
            
            if (nx >= 0 && nx < N && ny >= 0 && ny < M) {
                if (visited[nx][ny]) { // 외부 공기와 인접
                    return true;
                }
            }
        }
        return false;
    }
}