import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static String DNA;
    static int[] dx = new int[4];
    static int[] dxTest = new int[4];;  //A’, ‘C’, ‘G’, ‘T’
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DNA = br.readLine();
        dx = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            addChar(DNA.charAt(i));
        }
        if (isCount()) {
            count++;
        }

        for (int i = M; i < N; i++) {
            removeChar(DNA.charAt(i - M));
            addChar(DNA.charAt(i));
            if (isCount()) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void addChar(char c) {
        if (c == 'A') {
            dxTest[0]++;
        } else if (c == 'C') {
            dxTest[1]++;
        } else if (c == 'G') {
            dxTest[2]++;
        } else if (c == 'T') {
            dxTest[3]++;
        }
    }

    private static void removeChar(char c) {
        if (c == 'A') {
            dxTest[0]--;
        } else if (c == 'C') {
            dxTest[1]--;
        } else if (c == 'G') {
            dxTest[2]--;
        } else if (c == 'T') {
            dxTest[3]--;
        }
    }

    private static boolean isCount() {
        for (int i = 0; i < 4; i++) {
            if (dx[i] > dxTest[i]) {
                return false;
            }
        }
        return true;
    }
}
