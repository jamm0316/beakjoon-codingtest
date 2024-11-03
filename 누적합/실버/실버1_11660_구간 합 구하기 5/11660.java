import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        // 배열의 길이와 쿼리 개수 입력
        int N = scanner.nextInt();
        int K = scanner.nextInt();
        
        // 배열 생성
        int[][] A = new int[N][N];
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                A[i][j] = scanner.nextInt();
            }
        }
        
        // prefix_sum 배열 생성 및 초기화
        int[][] prefixSum = new int[N + 1][N + 1];
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= N; j++) {
                prefixSum[i][j] = A[i - 1][j - 1] 
                                + prefixSum[i - 1][j] 
                                + prefixSum[i][j - 1] 
                                - prefixSum[i - 1][j - 1];
            }
        }
        
        // 쿼리 처리
        int[][] queries = new int[K][4];
        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 4; j++) {
                queries[i][j] = scanner.nextInt();
            }
        }
        
        // 결과 계산 및 출력
        for (int i = 0; i < K; i++) {
            int x1 = queries[i][0];
            int y1 = queries[i][1];
            int x2 = queries[i][2];
            int y2 = queries[i][3];
            int result = prefixSum[x2][y2] 
                       - prefixSum[x1 - 1][y2] 
                       - prefixSum[x2][y1 - 1] 
                       + prefixSum[x1 - 1][y1 - 1];
            System.out.println(result);
        }
        
        scanner.close();
    }
}
