import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int count = 0;
        int powerOf5 = 5;
        while (N >= powerOf5){
            count += N / powerOf5;
            powerOf5 *= 5;
        }
        System.out.println(count);
    }
}

