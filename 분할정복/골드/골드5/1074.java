package divideAndConquer;

import java.io.*;
import java.util.*;

public class P1074copy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int r = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int result = findValue(N, r, c);
        System.out.println(result);
    }
    private static int findValue(int N, int r, int c) {
        int size = (1 << N);
        int result = 0;

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
