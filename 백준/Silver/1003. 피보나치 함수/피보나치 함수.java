import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        //initialize
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            //[[0, 1], [0, 0], [0, 0]]
            int[][] dp = new int[n + 1][2];

            //initialize dp
            //[[0, 1], [0, 0], [0, 0]]
            dp[0][0] = 1;
            dp[0][1] = 0;

            if (n > 0) {
                //[[0개수, 1개수]]
                //[[0, 1], [1, 0], [0, 0]]
                //[[0, 1], [1, 0], [1, 1], [2, 1] ...]
                dp[1][0] = 0;
                dp[1][1] = 1;
                if (n > 1) {
                    for (int j = 2; j <= n; j++) {
                        dp[j][0] = dp[j - 1][0] + dp[j - 2][0];
                        dp[j][1] = dp[j - 1][1] + dp[j - 2][1];
                    }
                }
            }
            bw.write(dp[n][0] + " " + dp[n][1]);
            bw.newLine();
        }
        bw.close();
        br.close();
    }

}
