import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] s = br.readLine().toCharArray();

        int[] cnt = new int[26];
        for (char c : s) cnt[c - 'A']++;

        int odd = 0, oddIdx = -1;
        for (int i = 0; i < 26; i++) {
            if ((cnt[i] & 1) == 1) { odd++; oddIdx = i; }
        }
        if (odd > 1) {
            System.out.println("I'm Sorry Hansoo");
            return;
        }

        StringBuilder left = new StringBuilder(s.length / 2);
        for (int i = 0; i < 26; i++) {
            int pairs = cnt[i] / 2;
            for (int k = 0; k < pairs; k++) left.append((char)('A' + i));
        }

        String mid = (odd == 1) ? String.valueOf((char)('A' + oddIdx)) : "";

        StringBuilder right = new StringBuilder(left).reverse();
        System.out.print(left.toString() + mid + right.toString());
    }
}