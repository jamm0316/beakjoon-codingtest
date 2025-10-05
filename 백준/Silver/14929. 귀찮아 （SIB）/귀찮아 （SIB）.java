import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine().trim());

        StringTokenizer st = new StringTokenizer(br.readLine());
        long sumSoFar = 0L;   // 앞까지의 합
        long ans = 0L;        // 정답 (모든 a<b의 x_a * x_b 합)

        for (int i = 0; i < n; i++) {
            long x = Long.parseLong(st.nextToken());
            ans += sumSoFar * x;  // 앞의 모든 값들과 현재 x의 곱을 한 번에 더함
            sumSoFar += x;
        }

        System.out.println(ans);
    }
}