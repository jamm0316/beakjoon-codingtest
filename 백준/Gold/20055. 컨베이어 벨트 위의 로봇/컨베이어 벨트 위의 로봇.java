import java.io.*;
import java.util.*;

public class Main {
    static int N, K;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int LEN = 2 * N;
        int[] A = new int[LEN];
        boolean[] robot = new boolean[LEN];

        st = new StringTokenizer(br.readLine());
        int zero = 0;
        for (int i = 0; i < LEN; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            if (A[i] == 0) zero++;
        }

        int step = 0;

        while (true) {
            step++;

            rotateRight(A);
            rotateRight(robot);
            
            if (robot[N -1]) robot[N - 1] = false;

            for (int i = N - 2; i >= 0; i--) {
                if (robot[i] && !robot[i + 1] && A[i + 1] > 0) {
                    robot[i] = false;
                    if (i + 1 != N -1) robot[i + 1] = true;
                    A[i + 1]--;
                    if (A[i + 1] == 0) zero++;
                }
            }
            
            if (robot[N - 1]) robot[N - 1] = false;

            if (!robot[0] && A[0] > 0) {
                robot[0] = true;
                A[0]--;
                if (A[0] == 0) zero++;
            }

            if (zero >= K) {
                System.out.println(step);
                break;
            }
        }
    }

    private static void rotateRight(int[] arr) {
        int last = arr[arr.length - 1];
        for (int i = arr.length - 1; i >= 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }

    private static void rotateRight(boolean[] arr) {
        boolean last = arr[arr.length - 1];
        for (int i = arr.length - 1; i >= 1; i--) {
            arr[i] = arr[i - 1];
        }
        arr[0] = last;
    }
}
