import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int minLevel;
    static int maxLevel;
    static int gapLevel;
    static int problems[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        minLevel = Integer.parseInt(st.nextToken());
        maxLevel = Integer.parseInt(st.nextToken());
        gapLevel = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        problems = new int[N];
        for (int i = 0; i < N; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bitMaskSelectProblems());
    }

    static int bitMaskSelectProblems() {
        int count = 0;

        // 모든 가능한 문제 조합을 비트마스크로 탐색
        for (int i = 0; i < (1 << N); i++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int numProblems = 0;

            // 선택된 문제들에 대해 계산
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) { // j번째 문제 선택
                    sum += problems[j];
                    max = Math.max(max, problems[j]);
                    min = Math.min(min, problems[j]);
                    numProblems++;
                }
            }

            // 조건을 만족하는지 확인
            if (numProblems >= 2 && sum >= minLevel && sum <= maxLevel && (max - min) >= gapLevel) {
                count++;
            }
        }

        return count;
    }
}
