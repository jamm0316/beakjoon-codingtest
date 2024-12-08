import java.util.*;
import java.io.*;

public class Main {
    static int[][] graph;
    static int N;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 특정 위치의 값 출력
        System.out.println(findGrapgh(N, r, c));
    }

    private static int findGrapgh(int N, int r, int c) {
        int result = 0;
        int size = 1 << N;

        while (size > 1) {
            size /= 2;

            if (r < size && c < size) {

            } else if (r < size && c >= size) {
                result += size * size;
                c -= size;
            } else if (r >= size && c < size) {
                result += 2 * size * size;
                r -= size;
            } else {
                result += 3 * size * size;
                r -= size;
                c -= size;
            }
        }
        return result;
    }
}