import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static long B;
    static int[][] A;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        B = Long.parseLong(st.nextToken());
        A = new int[N][N];

        for (int i = 0; i < N; i++) {
            A[i] = Arrays.stream(br.readLine().split(" ")).mapToInt(k -> {
                return Integer.parseInt(k) % 1000;
            }).toArray();
        }

        int[][] result = pow(A, B);

        StringBuilder sb = new StringBuilder();
        for (int[] row : result) {
            for (int num : row) {
                sb.append(num % 1000).append(" ");
            }
            sb.append('\n');
        }
        System.out.println(sb.toString());
    }

    static private int[][] pow(int[][] matrix, long exp) {
        if (exp == 1L) {
            return matrix;
        }

        int[][] half = pow(matrix, exp / 2);
        int[][] res = multiply(half, half);

        if (exp % 2 == 1) {
            res = multiply(res, A);
        }
        return res;
    }

    static int[][] multiply(int[][] m1, int[][] m2) {
        int[][] result = new int[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                int sum = 0;
                for (int k = 0; k < N; k++) {
                    sum += m1[i][k] * m2[k][j];
                }
                result[i][j] = sum % 1000;
            }
        }

        return result;
    }
}
