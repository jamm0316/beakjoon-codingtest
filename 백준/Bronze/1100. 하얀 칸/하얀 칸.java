import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char[][] board = new char[8][8];
        int count = 0;

        // 체스판 입력 받기
        for (int i = 0; i < 8; i++) {
            String line = scanner.nextLine();
            for (int j = 0; j < 8; j++) {
                board[i][j] = line.charAt(j);
                // 하얀 칸 (i + j) % 2 == 0 이고 말이 있는 경우 'F' 카운트
                if ((i + j) % 2 == 0 && board[i][j] == 'F') {
                    count++;
                }
            }
        }

        // 결과 출력
        System.out.println(count);
        scanner.close();
    }
}