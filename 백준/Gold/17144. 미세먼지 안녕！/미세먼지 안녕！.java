import java.io.*;
import java.util.*;

public class Main {
    static class Dust {
        int x, y, size;
        Dust(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }
    }

    static class AirConditioner {
        int x, y;
        AirConditioner(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int R, C, T;
    static int[][] graph;
    static Queue<Dust> queue = new LinkedList<>();
    static AirConditioner top, bottom;
    static int[] dx = {-1, 1, 0, 0};  // 상하좌우
    static int[] dy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        T = Integer.parseInt(st.nextToken());
        graph = new int[R][C];

        for (int i = 0; i < R; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < C; j++) {
                int val = Integer.parseInt(st.nextToken());
                graph[i][j] = val;
                if (val == -1) {
                    if (top == null) top = new AirConditioner(i, j);
                    else bottom = new AirConditioner(i, j);
                } else if (val > 0) {
                    queue.offer(new Dust(i, j, val));
                }
            }
        }

        while (T-- > 0) {
            spreadDust();
            circulateAir();
            updateQueue();
        }

        System.out.println(countDust());
    }

    private static void spreadDust() {
        int[][] temp = new int[R][C];

        int qSize = queue.size();
        for (int i = 0; i < qSize; i++) {
            Dust dust = queue.poll();
            int amount = dust.size / 5;
            int count = 0;

            for (int d = 0; d < 4; d++) {
                int nx = dust.x + dx[d];
                int ny = dust.y + dy[d];

                if (nx >= 0 && nx < R && ny >= 0 && ny < C && graph[nx][ny] != -1) {
                    temp[nx][ny] += amount;
                    count++;
                }
            }

            graph[dust.x][dust.y] -= amount * count;
        }

        // 확산된 값 반영
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                graph[i][j] += temp[i][j];
    }

    private static void circulateAir() {
        moveTop();
        moveBottom();

        // 공기청정기 위치 복구
        graph[top.x][top.y] = -1;
        graph[bottom.x][bottom.y] = -1;
    }

    private static void moveTop() {
        int x = top.x;

        // 위로
        for (int i = x - 1; i > 0; i--) graph[i][0] = graph[i - 1][0];
        // 왼쪽으로
        for (int i = 0; i < C - 1; i++) graph[0][i] = graph[0][i + 1];
        // 아래로
        for (int i = 0; i < x; i++) graph[i][C - 1] = graph[i + 1][C - 1];
        // 오른쪽으로
        for (int i = C - 1; i > 1; i--) graph[x][i] = graph[x][i - 1];
        graph[x][1] = 0;
    }

    private static void moveBottom() {
        int x = bottom.x;

        // 아래로
        for (int i = x + 1; i < R - 1; i++) graph[i][0] = graph[i + 1][0];
        // 왼쪽으로
        for (int i = 0; i < C - 1; i++) graph[R - 1][i] = graph[R - 1][i + 1];
        // 위로
        for (int i = R - 1; i > x; i--) graph[i][C - 1] = graph[i - 1][C - 1];
        // 오른쪽으로
        for (int i = C - 1; i > 1; i--) graph[x][i] = graph[x][i - 1];
        graph[x][1] = 0;
    }

    private static void updateQueue() {
        queue.clear();
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (graph[i][j] > 0) queue.offer(new Dust(i, j, graph[i][j]));
    }

    private static int countDust() {
        int sum = 0;
        for (int i = 0; i < R; i++)
            for (int j = 0; j < C; j++)
                if (graph[i][j] > 0) sum += graph[i][j];
        return sum;
    }
}