import java.io.*;
import java.util.*;

public class Main {
    static int N, M, curAlcoholInBlood, count;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            int nowAlcohol = Integer.parseInt(st.nextToken());
            arr[i] = nowAlcohol;
            if (i < M) {
                curAlcoholInBlood += nowAlcohol;
            } else {
                curAlcoholInBlood -= arr[i - M];
                curAlcoholInBlood += arr[i];
            }
            if (test()) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean test() {
        if (129 <= curAlcoholInBlood && curAlcoholInBlood <= 138) {
            return true;
        }
        return false;
    }
}
