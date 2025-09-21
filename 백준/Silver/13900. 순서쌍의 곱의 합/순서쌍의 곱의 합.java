import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine().trim());
        StringTokenizer st = new StringTokenizer(br.readLine());

        long prefixSum = 0L; // 지금까지 본 수들의 합
        long answer = 0L;    // 모든 서로 다른 두 수 곱의 합

        for (int i = 0; i < N; i++) {
            long x = Long.parseLong(st.nextToken());
            // 새로 들어온 x가 이전 원소들과 만드는 모든 곱의 합을 추가
            answer += x * prefixSum;
            // 누적합 갱신
            prefixSum += x;
        }

        System.out.println(answer);
    }
}