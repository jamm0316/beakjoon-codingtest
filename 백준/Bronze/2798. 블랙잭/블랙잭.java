import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[] cards = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        int result = findMaxNumberOf3Cards(N, M, cards);
        System.out.println(result);
    }

    private static int findMaxNumberOf3Cards(int N, int M, int[] cards) {
        int result = Integer.MIN_VALUE;
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                for (int k = j + 1; k < N; k++) {
                    int eachScore = cards[i] + cards[j] + cards[k];
                    if (eachScore <= M) {
                        result = Math.max(result, eachScore);
                    }
                }
            }
        }
        return result;
    }
}
