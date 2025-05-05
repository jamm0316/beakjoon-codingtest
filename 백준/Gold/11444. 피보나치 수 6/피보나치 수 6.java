import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main {

    static final long MOD = 1_000_000_007;
    static final long[][] ORIGIN = {{1, 1}, {1, 0}};  // 상수 행렬

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());

        // F(0)은 0이므로 예외 처리
        if (N == 0) {
            System.out.println(0);
            return;
        }

        long[][] result = pow(ORIGIN, N - 1);
        System.out.println(result[0][0]);  // F(N)
    }

    // 행렬을 거듭제곱하는 메서드 (분할 정복)
    public static long[][] pow(long[][] A, long exp) {
        if (exp == 1 || exp == 0) return A;

        long[][] half = pow(A, exp / 2);
        long[][] result = multiply(half, half);

        if (exp % 2 == 1) {
            result = multiply(result, ORIGIN);
        }

        return result;
    }

    // 2x2 행렬 곱셈
    public static long[][] multiply(long[][] a, long[][] b) {
        long[][] res = new long[2][2];
        res[0][0] = (a[0][0] * b[0][0] + a[0][1] * b[1][0]) % MOD;
        res[0][1] = (a[0][0] * b[0][1] + a[0][1] * b[1][1]) % MOD;
        res[1][0] = (a[1][0] * b[0][0] + a[1][1] * b[1][0]) % MOD;
        res[1][1] = (a[1][0] * b[0][1] + a[1][1] * b[1][1]) % MOD;
        return res;
    }
}