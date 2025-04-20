import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();  // 테스트 케이스 수

        for (int i = 0; i < T; i++) {
            int H = sc.nextInt();  // 층 수
            int W = sc.nextInt();  // 각 층 방 수
            int N = sc.nextInt();  // N번째 손님

            int floor = N % H;
            int room = N / H + 1;

            // 만약 N이 H의 배수라면 꼭대기 층(H층)에 배정
            if (floor == 0) {
                floor = H;
                room = N / H;
            }

            // 층수 * 100 + 호수로 출력 형식 맞추기
            System.out.println(floor * 100 + room);
        }

        sc.close();
    }
}