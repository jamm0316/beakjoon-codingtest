import java.io.*;

public class Main {
    static int N;
    static boolean found = false;

    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        backtrack("");
    }

    private static void backtrack(String str) {
        if (found) return;

        if (str.length() == N) {
            System.out.println(str);
            found = true;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            String next = str + i;
            if (isBad(next)) continue;
            backtrack(next);
        }
    }

    private static boolean isBad(String s) {
        int len = s.length();
        for (int k = 1; k * 2 <= len; k++) {
            String left = s.substring(len - 2 * k, len - k);
            String right = s.substring(len - k);
            if (left.equals(right)) {
                return true;
            }
        }
        return false;
    }
}
