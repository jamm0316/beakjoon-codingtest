import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Map<String, Integer> freq = new HashMap<>();
        for (int i = 0; i < N; i++) {
            String word = br.readLine();
            if (word.length() < M) continue;
            freq.put(word, freq.getOrDefault(word, 0) + 1);
        }

        List<String> words = new ArrayList<>(freq.keySet());

        words.sort((a, b) -> {
            int fa = freq.get(a);
            int fb = freq.get(b);

            if (fa != fb) return Integer.compare(fb, fa);                 // 빈도 내림차순
            if (a.length() != b.length()) return Integer.compare(b.length(), a.length()); // 길이 내림차순
            return a.compareTo(b);                                        // 사전순 오름차순
        });

        StringBuilder sb = new StringBuilder();
        for (String w : words) {
            sb.append(w).append('\n');
        }
        System.out.print(sb.toString());
    }
}