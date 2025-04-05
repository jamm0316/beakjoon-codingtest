import java.io.*;
import java.util.*;

public class Main {
    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);
            int N = Integer.parseInt(input[1]);
            int x = Integer.parseInt(input[2]);
            int y = Integer.parseInt(input[3]);

            int maxYear = lcm(M, N);
            int answer = -1;

            for (int k = x; k <= maxYear; k += M) {
                if ((k - 1) % N + 1 == y) {
                    answer = k;
                    break;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}