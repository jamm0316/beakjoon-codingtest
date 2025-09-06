import java.io.*;
import java.util.*;

public class Main {
    static long countOnesUpTo(long n) {
        if (n < 0) return 0L; // A가 1일 때 A-1 = 0, 그보다 작으면 0 처리
        long total = 0L;
        // 10^16 < 2^54 이므로 0..60 비트면 충분
        for (int i = 0; i <= 60; i++) {
            long block = 1L << (i + 1);   // 주기 길이 2^(i+1)
            long half  = 1L << i;         // 주기 내 1의 연속 길이 2^i
            long cycles = (n + 1) / block;
            long rem    = (n + 1) % block;
            total += cycles * half + Math.max(0L, rem - half);
        }
        return total;
        // 증명 스케치:
        // i번째 비트는 [0..n]에서 각 완전 주기마다 정확히 2^i번 1이 나오고,
        // 남은 rem 구간에서는 half를 넘는 부분만큼 1이 추가된다.
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long A = Long.parseLong(st.nextToken());
        long B = Long.parseLong(st.nextToken());

        long ans = countOnesUpTo(B) - countOnesUpTo(A - 1);
        System.out.println(ans);
    }
}