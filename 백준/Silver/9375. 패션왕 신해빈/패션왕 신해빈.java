import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine());

        while (testCase-- > 0) {
            int n = Integer.parseInt(br.readLine());
            Map<String, Integer> map = new HashMap<>();

            for (int i = 0; i < n; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                String name = st.nextToken();
                String type = st.nextToken();

                map.put(type, map.getOrDefault(type, 0) + 1);
            }

            int result = 1;
            for (int count : map.values()) {
                result *= (count + 1);
            }

            System.out.println(result - 1);
        }
    }
}
