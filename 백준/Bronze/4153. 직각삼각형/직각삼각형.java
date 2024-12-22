import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuffer sb = new StringBuffer();
        boolean isRunning = true;

        while (isRunning) {
            int [] array = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            int sum = Arrays.stream(array).sum();

            if (sum == 0) {
                isRunning = false;
            } else {
                Arrays.sort(array);

                if (is90(array)) {
                    sb.append("right").append("\n");
                } else {
                    sb.append("wrong").append("\n");
                }
            }
        }
        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();

        }

        private static boolean is90(int[] array) {
            return Math.pow(array[0], 2) + Math.pow(array[1], 2) == Math.pow(array[2], 2);
        }
    }
