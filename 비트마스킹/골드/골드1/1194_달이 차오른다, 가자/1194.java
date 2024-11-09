import java.io.*;
import java.util.*;

public class Main {
    static class Position {
        int x;
        int y;
        int keys;
        
        public Position(int x, int y, int keys) {
            this.x = x;
            this.y = y;
            this.keys = keys;
        }
    }

    static int N;
    static int M;
    static char[][] maze;
    static boolean[][][] visited;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        maze = new char[N][M];
        visited = new boolean[N][M][(1 << 6)];
        
        Position start = null;
        
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            for (int j = 0; j < M; j++) {
                maze[i][j] = line.charAt(j);
                if (maze[i][j] == '0') {
                    start = new Position(i, j, 0);
                    maze[i][j] = '.';
                }
            }
        }
        
        System.out.println(bfs(start));
    }
    
    static int bfs(Position start) {
        Queue<Position> queue = new LinkedList<>();
        queue.offer(start);
        visited[start.x][start.y][0] = true;
        int move = 0;
        
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                Position cur = queue.poll();
                
                // 출구에 도달한 경우 이동 횟수 반환
                if (maze[cur.x][cur.y] == '1') {
                    return move;
                }

                // 4방향 탐색
                for (int d = 0; d < 4; d++) {
                    int nx = cur.x + dx[d];
                    int ny = cur.y + dy[d];
                    int keys = cur.keys;
                    
                    // 경계 조건 및 방문 여부 확인
                    if (0 <= nx && nx < N && 0 <= ny && ny < M && !visited[nx][ny][keys]) {
                        char cell = maze[nx][ny];
                        
                        if (cell != '#') {  // 벽이 아닌 경우
                            // 열쇠 획득
                            if ('a' <= cell && cell <= 'f') {
                                keys |= (1 << (cell - 'a'));
                            }
                            
                            // 문 통과 조건 확인
                            if (cell < 'A' || cell > 'F' || (keys & (1 << (cell - 'A'))) != 0) {
                                // 모든 조건을 만족한 경우 큐에 추가하고 방문 표시
                                visited[nx][ny][keys] = true;
                                queue.offer(new Position(nx, ny, keys));
                            }
                        }
                    }
                }
            }
            move++;
        }
        return -1;  // 출구에 도달할 수 없는 경우
    }
}
