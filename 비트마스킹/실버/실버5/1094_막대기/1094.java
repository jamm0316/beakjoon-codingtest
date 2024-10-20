import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int x = input.nextInt();
        int count = 0;
        while (x > 0) {
            x = x & (x - 1);
            count++;
        }
        System.out.println(count);
    }
}
