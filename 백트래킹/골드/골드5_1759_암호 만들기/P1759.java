import java.io.*;
import java.util.*;

public class Main {
    static int L, C;
    static char[] charArr;
    static StringBuilder sb = new StringBuilder();
    static final String vowels = "aeiou";

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

        Arrays.sort(charArr); // 오름차순 정렬

        backtrack(0, 0, 0, new StringBuilder());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtrack(int start, int vowelCnt, int consonantCnt, StringBuilder result) {
        if (result.length() == L) {
            // 최소 1개의 모음과 최소 2개의 자음 체크
            if (vowelCnt >= 1 && consonantCnt >= 2) {
                sb.append(result).append('\n');
            }
            return;
        }

        for (int i = start; i < C; i++) {
            char c = charArr[i];
            result.append(c);
            if (vowels.indexOf(c) >= 0) {
                // 모음
                backtrack(i + 1, vowelCnt + 1, consonantCnt, result);
            } else {
                // 자음
                backtrack(i + 1, vowelCnt, consonantCnt + 1, result);
            }
            result.deleteCharAt(result.length() - 1); // 백트래킹
        }
    }
}
