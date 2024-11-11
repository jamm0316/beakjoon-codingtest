import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        char character = scanner.next().charAt(0); // 문자 입력 받기
        int asciiValue = (int) character; // ASCII 코드 값으로 변환
        System.out.println(asciiValue); // 출력
        scanner.close();
    }
}
