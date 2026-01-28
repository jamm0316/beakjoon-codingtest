import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        if (N == 1) {
            System.out.println(br.readLine());
            return;
        }

        List<String> result = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            map.put(line, map.getOrDefault(line, 0) + 1);
            max = Math.max(map.get(line), max);
        }


        for (String key : map.keySet()) {
            if (map.get(key) == max) {
                result.add(key);
            }
        }
        result.sort(Comparator.naturalOrder());
        System.out.println(result.get(0));
    }
}
