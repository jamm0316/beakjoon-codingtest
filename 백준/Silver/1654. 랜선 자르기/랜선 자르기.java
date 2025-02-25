import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] numbers = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int K = numbers[0];
        int N = numbers[1];
        int[] cables = new int[K];

        for (int i = 0; i < K; i++) {
            cables[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(cables);

        long left = 1;
        long right = cables[K - 1];
        long maxCable = 0;
        while (left <= right) {
            int count = 0;
            long mid = (left + right) / 2;

            for (int cable : cables) {
                count += cable / mid;
            }

            if (count >= N) {
                maxCable = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        System.out.println(maxCable);
    }
}
