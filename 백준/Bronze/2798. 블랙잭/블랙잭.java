import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int target = Integer.parseInt(st.nextToken());
        int[] card = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int result = Integer.MIN_VALUE;

        for (int i = 0; i < card.length; i++) {
            for (int j = i+1; j < card.length; j++) {
                for (int k = j+1; k < card.length; k++) {
                    if (card[i] + card[j] + card[k] <= target) {
                        result = Math.max(card[i] + card[j] + card[k], result);
                    }
                }
            }
        }

        System.out.println(result);
    }
}
