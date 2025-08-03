import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<List<Integer>> charPos = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                charPos.add(new ArrayList<>());
            }

            //각 문자별 인덱스 저장
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);
                charPos.get(c - 'a').add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for (int i = 0; i < 26; i++) {
                List<Integer> list = charPos.get(i);
                if (list.size() < K) continue;

                for (int j = 0; j <= list.size() - K; j++) {
                    int start = list.get(j);
                    int end = list.get(j + K - 1);
                    int length = end - start + 1;

                    min = Math.min(min, length);
                    max = Math.max(max, length);
                }
            }

            if (max == -1) {
                sb.append(-1).append('\n');
            } else {
                sb.append(min).append(" ").append(max).append('\n');
            }
        }

        System.out.println(sb);
    }
}
