import java.io.*;
import java.util.*;

public class Main {
    static final int MAX_A = 1_000_000; // A_i ≤ 1e6
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] arr = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) arr[i] = Integer.parseInt(st.nextToken());

        int need = (9 * M + 9) / 10; // ceil(9M/10)

        int[] cnt = new int[MAX_A + 1];

        // 초기 윈도우 채우면서 매 증가 순간 검사
        for (int i = 0; i < M; i++) {
            int v = arr[i];
            if (++cnt[v] >= need) {
                System.out.println("YES");
                return;
            }
        }

        // 슬라이딩
        for (int r = M; r < N; r++) {
            int out = arr[r - M];
            int in  = arr[r];
            // 나가는 값 감소
            cnt[out]--;
            // 들어오는 값 증가 후 즉시 검사
            if (++cnt[in] >= need) {
                System.out.println("YES");
                return;
            }
        }

        System.out.println("NO");
    }
}