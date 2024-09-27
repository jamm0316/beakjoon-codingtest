package Brute_Force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] data = parseData();
        int N = data[0];
        int K = data[1];

        int minNumber = findMinNumber(N, K);
        int result = minNumber;
        System.out.println(result);
    }

    private static int findMinNumber(int n, int k) {
        int count = 1;
        for (int i = 1; i <= n; i++) {
            if (count == k) {
                return i;
            }

            if (n % i == 0) {
                count++;
            }
        }
        return 0;
    }

    private static int[] parseData() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        String[] tokens = inputData.split(" ");
        int[] number = Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .toArray();

        return number;
    }
}
