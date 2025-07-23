import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int x, y, time;
        String type;

        public Node(int x, int y, int time, String type) {
            this.x = x;
            this.y = y;
            this.time = time;
            this.type = type;
        }

        @Override
        public String toString() {
            return "Node:{x: " + x + ", y: " + y + ", type: " + type + "}";
        }
    }

    static int N, M;
    static char[][] map;
    static boolean[][] visited;
    static Queue<Node> queue = new LinkedList<>();
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];
        visited = new boolean[N][M];

        bfs();
    }

    private static void bfs() throws IOException {
        for (int i = 0; i < N; i++) {
            String input = br.readLine();
            for (int j = 0; j < M; j++) {
                map[i][j] = input.charAt(j);

                if (map[i][j] == '*') {
                    queue.offer(new Node(i, j, 0, "water"));
                }
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if (map[i][j] == 'S') {
                    queue.offer(new Node(i, j, 0, "hedgehog"));
                    visited[i][j] = true;
                }
            }
        }

        while (!queue.isEmpty()) {
            Node cur = queue.poll();

            for (int i = 0; i < 4; i++) {
                int nx = cur.x + dx[i];
                int ny = cur.y + dy[i];

                if (nx < 0 || ny < 0 || N <= nx || M <= ny) continue;

                if (cur.type.equals("water")) {
                    if (map[nx][ny] == '.') {
                        map[nx][ny] = '*';
                        queue.offer(new Node(nx, ny, 0, "water"));
                    }
                } else if (cur.type.equals("hedgehog")) {
                    if ((map[nx][ny] == '.' || map[nx][ny] == 'D') && !visited[nx][ny]) {
                        if (map[nx][ny] == 'D') {
                            System.out.println(cur.time + 1);
                            return;
                        }

                        visited[nx][ny] = true;
                        queue.offer(new Node(nx, ny, cur.time + 1, "hedgehog"));
                    }
                }
            }
        }
        System.out.println("KAKTUS");
    }
}
