import java.io.*;
import java.util.*;

public class Main {
    static int count;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = toInt(st.nextToken()), M = toInt(st.nextToken()), K = toInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            int nowCount = 0;
            String line = br.readLine();

            for (int j = 0; j < M; j++) {
                int cur = line.charAt(j) - '0';
                if (cur == 0) {
                    nowCount++;
                }
                if (cur == 1) {
                    nowCount = 0;
                }
                if (nowCount >= K) {
                    count++;
                }
            }
        }
        System.out.println(count);

    }

    private static int toInt(String str) {
        return Integer.parseInt(str);
    }
}
