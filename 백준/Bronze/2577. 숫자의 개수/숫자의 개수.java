import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int A = sc.nextInt();
        int B = sc.nextInt();
        int C = sc.nextInt();

        int result = A * B * C;

        // 숫자 등장 횟수를 저장할 배열
        int[] count = new int[10];

        // 계산 결과를 문자열로 변환
        String str = String.valueOf(result);

        // 각 숫자의 등장 횟수를 카운트
        for (int i = 0; i < str.length(); i++) {
            int digit = str.charAt(i) - '0'; // 문자 → 숫자 변환
            count[digit]++;
        }

        // 결과 출력
        for (int i = 0; i < 10; i++) {
            System.out.println(count[i]);
        }

        sc.close();
    }
}