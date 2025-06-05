import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        long input = arr[0];
        long output = arr[1];
        int count = 1;
        while (input < output) {
            String outputStr = String.valueOf(output);
            if (Long.parseLong(outputStr.substring(outputStr.length() - 1)) == 1) {
                String newOutputStr = outputStr.substring(0, outputStr.length() - 1);
                output = Long.parseLong(newOutputStr);
                count++;
            } else if (output % 2 == 0) {
                output /= 2;
                count++;
            } else {
                break;
            }
        }

        System.out.println(input == output ? count : -1);
    }
}
