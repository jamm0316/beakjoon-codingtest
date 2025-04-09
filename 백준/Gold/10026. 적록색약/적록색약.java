import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};
    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        N = Integer.parseInt(br.readLine());
        String[][] normalGraph = new String[N][N];
        String[][] blindnessGraph = new String[N][N];
        boolean[][] normalVisited = new boolean[N][N];
        boolean[][] blindnessVisited = new boolean[N][N];
        int normalCount = 0;
        int blindnessCount = 0;

        //그래프 초기화
        for (int i = 0; i < N; i++) {
            String[] tokens = br.readLine().split("");
            for (int j = 0; j < N; j++) {
                String color = tokens[j];
                normalGraph[i][j] = color;
                blindnessGraph[i][j] = color.equals("R") ? "G" : color;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                //일반인 탐색
                if (!normalVisited[i][j]) {
                    countColorBoundary(normalGraph, normalVisited, i, j);
                    normalCount++;

//                    //debug
//                    System.out.println("nomalCount: " + normalCount + "--------------");
//                    System.out.println("-------[graph]-------");
//                    for (int row = 0; row < N; row++) {
//                        System.out.println(Arrays.toString(normalGraph[row]));
//                    }
//                    System.out.println("-------[visited]-------");
//                    for (int row = 0; row < N; row++) {
//                        System.out.println(Arrays.toString(normalVisited[row]));
//                    }
                }

                //색약탐색
                if (!blindnessVisited[i][j]) {
                    countColorBoundary(blindnessGraph, blindnessVisited, i, j);
                    blindnessCount++;

//                    //debug
//                    System.out.println("blindnessCount: " + blindnessCount + "--------------");
//                    System.out.println("-------[graph]-------");
//                    for (int row = 0; row < N; row++) {
//                        System.out.println(Arrays.toString(blindnessGraph[row]));
//                    }
//                    System.out.println("-------[visited]-------");
//                    for (int row = 0; row < N; row++) {
//                        System.out.println(Arrays.toString(blindnessVisited[row]));
//                    }
                }
            }
        }

        bw.write(normalCount + " " + blindnessCount);
        bw.close();
        br.close();
    }

    static private void countColorBoundary(String[][] graph, boolean[][] visited, int i, int j) {
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{i, j});
        visited[i][j] = true;

        while (!queue.isEmpty()) {
            int[] pos = queue.poll();
            int x = pos[0], y = pos[1];
            String curColor = graph[x][y];
            for (int k = 0; k < 4; k++) {
                int nx = x + dx[k];
                int ny = y + dy[k];

                if (0 <= nx && nx < N && 0 <= ny && ny < N && !visited[nx][ny]) {
                    String nextColor = graph[nx][ny];
                    if (curColor.equals(nextColor)) {
                        queue.offer(new int[]{nx, ny});
                        visited[nx][ny] = true;
                    }
                }
            }
        }
    }
}
