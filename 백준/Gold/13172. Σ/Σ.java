import java.io.*;
import java.util.*;

public class Main {
    static final int MOD = 1_000_000_007;

    // 페르마의 소정리로 역원 구하기: a^(MOD - 2) % MOD
    static long modInverse(long b) {
        return modPow(b, MOD - 2);
    }

    static long modPow(long base, long exp) {
        long result = 1;
        base %= MOD;
        while (exp > 0) {
            if ((exp & 1) == 1) result = (result * base) % MOD;
            base = (base * base) % MOD;
            exp >>= 1;
        }
        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int M = Integer.parseInt(br.readLine());

        long result = 0;

        for (int i = 0; i < M; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long Ni = Long.parseLong(st.nextToken());
            long Si = Long.parseLong(st.nextToken());

            long inverse = modInverse(Ni);
            long expect = (Si % MOD) * inverse % MOD;

            result = (result + expect) % MOD;
        }

        System.out.println(result);
    }
}