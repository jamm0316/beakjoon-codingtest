import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int L, C;
    static char[] charArr;
    static StringBuilder sb = new StringBuilder();
    static String vowel = "aeiou";

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        charArr = new char[C];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < C; i++) {
            charArr[i] = st.nextToken().charAt(0);
        }

        Arrays.sort(charArr);

        backtrack(0, 0, 0, new StringBuilder());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtrack(int start, int vowelCnt, int notVowelCnt, StringBuilder result) {
        if (result.length() == L) {
            if (vowelCnt >= 1 && notVowelCnt >= 2) {
                sb.append(result).append("\n");
                return;
            }
        }

        for (int i = start; i < C; i++) {
            char c = charArr[i];
            result.append(c);
            if (vowel.indexOf(c) >= 0) {
                backtrack(i + 1, vowelCnt + 1, notVowelCnt, result);
            } else {
                backtrack(i + 1, vowelCnt, notVowelCnt + 1, result);
            }
            result.deleteCharAt(result.length() - 1);
        }
    }
}
