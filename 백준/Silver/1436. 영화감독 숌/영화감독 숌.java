import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int count = 1;
        int series = 666;

        while (true) {
            if (String.valueOf(series).contains("666")) {
                if (count == N) {
                    System.out.println(series);
                    return;
                }
                count++;
            }
            series++;
        }
    }
}
