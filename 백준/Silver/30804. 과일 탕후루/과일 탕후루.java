import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] S = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            S[i] = Integer.parseInt(st.nextToken());
        }

        Map<Integer, Integer> countMap = new HashMap<>();
        int left = 0;
        int maxLength = 0;

        for (int right = 0; right < N; right++) {
            countMap.put(S[right], countMap.getOrDefault(S[right], 0) + 1);

            while (countMap.size() > 2) {
                countMap.put(S[left], countMap.get(S[left]) - 1);
                if (countMap.get(S[left]) == 0) {
                    countMap.remove(S[left]);
                }
                left++;
            }

            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}