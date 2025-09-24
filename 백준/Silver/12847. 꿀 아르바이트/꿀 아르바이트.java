import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        long[] t = new long[n];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) t[i] = Long.parseLong(st.nextToken());

        // m=0이면 일을 안 하므로 수익 0
        if (m == 0) {
            System.out.println(0);
            return;
        }

        // 초기 윈도우 합(0..m-1)
        long window = 0;
        for (int i = 0; i < m; i++) window += t[i];
        long ans = window;

        // 윈도우를 오른쪽으로 밀며 최댓값 갱신
        for (int right = m; right < n; right++) {
            window += t[right];        // 새로 들어온 날 추가
            window -= t[right - m];    // 왼쪽에서 빠지는 날 제거
            if (window > ans) ans = window;
        }

        System.out.println(ans);
    }
}