import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] taste;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        taste = new int[N][2];

        //dataParsing
        for (int i = 0; i < N; i++) {
            String[] query = br.readLine().split(" ");
            int[] parsedQuery = Arrays.stream(query)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            taste[i][0] = parsedQuery[0];
            taste[i][1] = parsedQuery[1];
        }

        int minNum = Integer.MAX_VALUE;

        //bitMask
        for (int i = 1; i < (1 << N); i++) {
            int sour = 1;
            int bitter = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sour *= taste[j][0];
                    bitter += taste[j][1];
                }
            }
            minNum = Math.min(minNum, Math.abs(sour - bitter));

        }
        System.out.println(minNum);
    }
}
