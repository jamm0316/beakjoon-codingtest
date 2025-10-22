import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] belt = new int[N];
        for (int i = 0; i < N; i++) belt[i] = Integer.parseInt(br.readLine());

        int[] freq = new int[d + 1];
        int distinct = 0;

        for (int i = 0; i < k; i++) {
            int x = belt[i % N];
            if (freq[x]++ == 0) distinct++;
        }

        int ans = distinct + (freq[c] == 0 ? 1 : 0);

        for (int start = 1; start < N; start++) {
            int out = belt[(start - 1) % N];
            if (--freq[out] == 0) distinct--;

            int in = belt[(start + k - 1) % N];
            if (freq[in]++ == 0) distinct++;

            int cur = distinct + (freq[c] == 0 ? 1 : 0);
            if (cur > ans) ans = cur;
        }

        System.out.println(ans);
    }
}
