import java.util.HashSet;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        HashSet<Integer> remainders = new HashSet<>(); // 서로 다른 나머지를 저장할 HashSet

        // 10개의 숫자를 입력받아 처리
        for (int i = 0; i < 10; i++) {
            int num = scanner.nextInt();
            remainders.add(num % 42); // 42로 나눈 나머지를 HashSet에 추가
        }

        // HashSet의 크기를 출력
        System.out.println(remainders.size());

        scanner.close();
    }
}
