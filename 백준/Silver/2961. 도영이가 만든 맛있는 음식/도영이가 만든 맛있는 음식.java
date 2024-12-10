import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[][] ingredient = new int[N][2];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            ingredient[i][0] = Integer.parseInt(st.nextToken());
            ingredient[i][1] = Integer.parseInt(st.nextToken());
        }

        int minValue = findMinTaste(N, ingredient);

        System.out.println(minValue);
    }

    private static int findMinTaste(int N, int[][] ingredient) {
        int query = 1 << N;
        int minValue = Integer.MAX_VALUE;

        for (int i = 1; i < query; i++) {
            int sour = 1;
            int bitter = 0;

            for (int j = 0; j < N; j++) {
                if ((i & 1 << j) != 0) {
                    sour *= ingredient[j][0];
                    bitter += ingredient[j][1];
                }
            }
            minValue = Math.min(minValue, Math.abs(sour - bitter));
        }
        return minValue;
    }
}
