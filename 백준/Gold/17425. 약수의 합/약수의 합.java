import java.io.*;
import java.util.*;

public class Main {
    static final int MAX = 1_000_000;
    static long[] f = new long[MAX + 1]; // f(i): i의 약수합
    static long[] g = new long[MAX + 1]; // g(i): f(1)+..+f(i)

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        // f(y) 계산
        for (int i = 1; i <= MAX; i++) {
            for (int j = i; j <= MAX; j += i) {
                f[j] += i; // i는 j의 약수
            }
        }

        // g(N) 계산 (누적합)
        for (int i = 1; i <= MAX; i++) {
            g[i] = g[i - 1] + f[i];
        }

        // 입력 처리
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            sb.append(g[N]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
    }
}