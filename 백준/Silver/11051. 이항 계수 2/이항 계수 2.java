import java.util.Scanner;

public class Main {
    static final int MOD = 10007;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int K = sc.nextInt();
        sc.close();

        System.out.println(binomialCoefficient(N, K));
    }

    static int binomialCoefficient(int N, int K) {
        if (K == 0 || K == N) return 1;
        return (factorial(N) * modInverse(factorial(K)) % MOD) * modInverse(factorial(N - K)) % MOD;
    }

    static int factorial(int n) {
        int result = 1;
        for (int i = 2; i <= n; i++) {
            result = (result * i) % MOD;
        }
        return result;
    }

    static int modInverse(int x) {
        return power(x, MOD - 2);
    }

    static int power(int base, int exp) {
        int result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }
}