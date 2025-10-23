import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int[] alphabet = new int[26];
        for (char c : arr) alphabet[c - 'A']++;

        int odd = 0, oddIdx = -1;
        for (int i = 0; i < 26; i++) {
            if (alphabet[i] % 2 != 0) {
                odd++;
                oddIdx = i;
            }
        }

        if (odd > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder sb = new StringBuilder(arr.length / 2);
        for (int i = 0; i < 26; i++) {
            int pairs = alphabet[i] / 2;
            for (int j = 0; j < pairs; j++) {
                sb.append((char) ('A' + i));
            }
        }

        String mid = (odd == 1) ? String.valueOf((char) ('A' + oddIdx)) : "";

        StringBuilder right = new StringBuilder(sb).reverse();
        sb.append(mid).append(right);
        System.out.println(sb);
    }
}
