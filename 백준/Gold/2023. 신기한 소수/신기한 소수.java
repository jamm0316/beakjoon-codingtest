import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        int[] starts = {2, 3, 5, 7};
        for (int s : starts) {
            dfs(s, 1);
        }
        System.out.println(sb);
    }

    //num: 현재 까지 만든 수, len: 현재 자리수 길이
    static private void dfs(int num, int len) {
        if (len == N) {
            sb.append(num).append('\n');
            return;
        }

        //다음 자릿수 후보(홀수만)
        int[] candidate = {1, 3, 5, 7, 9};
        for (int d : candidate) {
            int next = num * 10 + d;
            if (isPrime(next)) {
                dfs(next, len + 1);
            }
        }
    }

    //소수 판정
    static private boolean isPrime(int x) {
        if (x < 2) return false;
        if (x == 2 || x == 3) return true;
        if (x % 2 == 0 || x % 3 == 0) return false;

        for (int i = 5; (long) i * i <= x; i += 6) {
            if (x % i == 0 || x % (i + 2) == 0) return false;
        }
        return true;
    }
}
