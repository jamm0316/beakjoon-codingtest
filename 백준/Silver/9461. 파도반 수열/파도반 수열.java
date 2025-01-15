import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 입력: 테스트 케이스 수
        int T = scanner.nextInt();
        int[] testCases = new int[T];
        int maxN = 0;
        
        // 입력: 각 테스트 케이스의 N 값
        for (int i = 0; i < T; i++) {
            testCases[i] = scanner.nextInt();
            if (testCases[i] > maxN) {
                maxN = testCases[i];
            }
        }
        
        // DP 배열 초기화 및 계산 (1 ≤ N ≤ 100)
        long[] P = new long[Math.max(101, maxN + 1)];
        P[1] = P[2] = P[3] = 1; // 초기값 설정
        
        for (int i = 4; i <= maxN; i++) {
            P[i] = P[i - 2] + P[i - 3];
        }
        
        // 각 테스트 케이스에 대해 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int testCase : testCases) {
            sb.append(P[testCase]).append("\n");
        }
        
        System.out.print(sb);
    }
}