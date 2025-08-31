import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        int[] a = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) a[i] = Integer.parseInt(st.nextToken());

        long[] psum = new long[N];
        psum[0] = a[0];
        for (int i = 1; i < N; i++) psum[i] = psum[i - 1] + a[i];
        long S = psum[N - 1];

        long answer = 0;

        // 케이스 A: 벌(0) … 벌(i) … 벌통(N-1)
        //  i는 1..N-2
        for (int i = 1; i <= N - 2; i++) {
            long val = (S - a[0] - a[i]) + (S - psum[i]); // = 2S - a[0] - a[i] - psum[i]
            if (val > answer) answer = val;
        }

        // 케이스 B: 벌통(0) … 벌(i) … 벌(N-1)
        for (int i = 1; i <= N - 2; i++) {
            long val = (S - a[N - 1] - a[i]) + psum[i - 1];
            if (val > answer) answer = val;
        }

        // 케이스 C: 벌(0) … 벌통(k) … 벌(N-1)
        //  k는 1..N-2 중 a[k] 최댓값을 고르면 됨
        long maxMid = 0;
        for (int k = 1; k <= N - 2; k++) {
            if (a[k] > maxMid) maxMid = a[k];
        }
        long valC = S - a[0] - a[N - 1] + maxMid;
        if (valC > answer) answer = valC;

        System.out.println(answer);
    }
}