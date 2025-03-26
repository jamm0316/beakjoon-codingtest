import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int T = sc.nextInt(); // 테스트 케이스 수

        for (int t = 0; t < T; t++) {
            int N = sc.nextInt(); // 배열 크기
            int[] arr = new int[N];

            for (int i = 0; i < N; i++) {
                arr[i] = sc.nextInt();
            }

            // 카데인 알고리즘
            int maxSum = arr[0];
            int currentSum = arr[0];

            for (int i = 1; i < N; i++) {
                currentSum = Math.max(arr[i], currentSum + arr[i]); // 지금 값만 취할지, 지금까지 누적할지 선택
                maxSum = Math.max(maxSum, currentSum); // 최대값 갱신
            }

            System.out.println(maxSum); // 각 테스트 케이스별 최대 부분합 출력
        }

        sc.close();
    }
}