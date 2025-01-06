import java.util.Scanner;

public class Main {
    public static int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    public static int lcm(int a, int b, int gcd) {
        return (a * b) / gcd;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();

        int gcd = gcd(a, b);

        int lcm = lcm(a, b, gcd);

        System.out.println(gcd);
        System.out.println(lcm);

        scanner.close();
    }
}