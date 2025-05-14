import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String word = br.readLine();
        int[] alphabet = new int[26];

        // 초기값을 -1로 설정
        for (int i = 0; i < 26; i++) {
            alphabet[i] = -1;
        }

        // 문자열을 돌면서 처음 등장한 위치 기록
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            int index = ch - 'a';

            if (alphabet[index] == -1) {
                alphabet[index] = i;
            }
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 26; i++) {
            sb.append(alphabet[i]).append(' ');
        }

        System.out.println(sb);
    }
}