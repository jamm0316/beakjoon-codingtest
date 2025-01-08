import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] queries = new int[N];
        List<Integer> dp = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = Integer.parseInt(st.nextToken());
        }

        dp.add(0, 1);
        dp.add(1, 1);
        dp.add(2, 2);
        dp.add(3, 4);
        for (int query : queries) {
            if (query > 3) {
                for (int i = 4; i <= query; i++) {
                    dp.add(i, dp.get(i - 1) + dp.get(i - 2) + dp.get(i - 3));
                }
                System.out.println(dp.get(query));
            } else {
                System.out.println(dp.get(query));
            }
        }
    }
}
