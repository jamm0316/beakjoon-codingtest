import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int count = 0;
        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < N; i++) {
            coins.add(Integer.parseInt(br.readLine()));
        }

        coins.sort(Comparator.reverseOrder());

        for (int curCoin : coins) {
            if (curCoin <= target) {
                count += target / curCoin;
                target %= curCoin;
            }
        }

        System.out.println(count);
    }
}
