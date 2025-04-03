import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> tanghuruMap = new HashMap<>();  //key: 종류, value: 갯수
        int N = Integer.parseInt(br.readLine());
        int[] tanghuru = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < N; right++) {
            tanghuruMap.put(tanghuru[right], tanghuruMap.getOrDefault(tanghuru[right], 0) + 1);

            while (tanghuruMap.size() > 2) {
                tanghuruMap.put(tanghuru[left], tanghuruMap.getOrDefault(tanghuru[left], 0) - 1);
                if (tanghuruMap.get(tanghuru[left]) == 0) {
                    tanghuruMap.remove(tanghuru[left]);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}
