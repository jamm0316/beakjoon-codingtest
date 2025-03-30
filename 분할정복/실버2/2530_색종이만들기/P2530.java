import java.util.*;
import java.io.*;

public class Main {
    static int[][] graph;
    static int blueCount;
    static int whiteCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        blueCount = 0;
        whiteCount = 0;

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        divideAndCount(0, 0, N);

        bw.write(String.valueOf(whiteCount));
        bw.newLine();
        bw.write(String.valueOf(blueCount));
        bw.close();
        br.close();
    }

    //분할정복 로직
    static private void divideAndCount(int x, int y, int size) {
        if (isSameColor(x, y, size)) {
            if (graph[x][y] == 0) {
                whiteCount++;
            } else {
                blueCount++;
            }
            return;
        }

        int newSize = size / 2;
        divideAndCount(x, y, newSize);  //좌상단
        divideAndCount(x, y + newSize, newSize);  //우상단
        divideAndCount(x + newSize, y, newSize);  //좌하단
        divideAndCount(x + newSize, y + newSize, newSize);  //우하단
    }

    //같은 색깔인지 판단하는 로직
    static private boolean isSameColor(int x, int y, int size) {
        int color = graph[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (graph[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
