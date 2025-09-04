import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());

        // Ai가 한 줄에 다 안 올 수도 있으니 토큰 고갈 시 라인 재읽기 방식
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] A = new int[N];
        for (int i = 0; i < N; i++) {
            while (!st.hasMoreTokens()) st = new StringTokenizer(br.readLine());
            A[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int B = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        long answer = 0L;

        for (int i = 0; i < N; i++) {
            // 총감독 1명
            answer += 1;

            // 남은 인원
            int remain = A[i] - B;
            if (remain > 0) {
                // 올림 나눗셈: (remain + C - 1) / C
                answer += (remain + (long)C - 1) / (long)C;
            }
        }

        System.out.println(answer);
    }
}