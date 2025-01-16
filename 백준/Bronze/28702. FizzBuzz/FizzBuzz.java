import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //3, 5 => fizzbuzz
        //3 => fizz
        //5 => buzz
        //3,5 x -> i
        // 9  10  11 12(fizz)
        for (int i = 0; i < 3; i++) {
            String str = st.nextToken();
            if (!str.equals("FizzBuzz") && !str.equals("Fizz") && !str.equals("Buzz")) {
                if ((Integer.parseInt(str) + (3 - i)) % 5 == 0 && (Integer.parseInt(str) + (3 - i)) % 3 == 0) {
                    System.out.println("FizzBuzz");
                    break;
                } else if ((Integer.parseInt(str) + (3 - i)) % 3 == 0) {
                    System.out.println("Fizz");
                    break;
                } else if ((Integer.parseInt(str) + (3 - i)) % 5 == 0) {
                    System.out.println("Buzz");
                    break;
                } else {
                    System.out.println(Integer.parseInt(str) + (3 - i));
                    break;
                }
            }
            st = new StringTokenizer(br.readLine());
        }
    }
}
