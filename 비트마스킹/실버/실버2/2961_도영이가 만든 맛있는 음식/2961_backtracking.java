import java.io.*;
import java.util.*;

public class Main {
    static int query;
    static int minNum = Integer.MAX_VALUE;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        query = Integer.parseInt(br.readLine().trim());
        arr = new int[query][2];
        for (int i = 0; i < query; i++) {
            String[] taste = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(taste[0]);
            arr[i][1] = Integer.parseInt(taste[1]);
        }

        findMinDifference(0, 1, 0, 0); //index, 신맛, 쓴맛, 재료사용횟수

        System.out.println(minNum);
    }

    public static void findMinDifference(int idx, int sour, int bitter, int used) {
        if (idx == query) {
            if (used > 0) {
                int difference = Math.abs(sour - bitter);
                minNum = Math.min(minNum, difference);
            }
            return;
        }

        findMinDifference(idx + 1, sour * arr[idx][0], bitter + arr[idx][1], used + 1);

        findMinDifference(idx + 1, sour, bitter, used);
    }
}
