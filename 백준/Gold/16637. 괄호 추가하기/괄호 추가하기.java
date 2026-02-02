import java.io.*;
import java.util.*;

public class Main {

    static int N;
    static String expr;
    static long[] nums;
    static char[] ops;
    static int numCnt, opCnt;
    static long best = Long.MIN_VALUE;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());
        expr = br.readLine().trim();

        numCnt = (N + 1) / 2;     // 최대 10
        opCnt = N / 2;            // 최대 9
        nums = new long[numCnt];
        ops = new char[opCnt];

        int ni = 0, oi = 0;
        for (int i = 0; i < N; i++) {
            char c = expr.charAt(i);
            if (i % 2 == 0) nums[ni++] = c - '0';
            else ops[oi++] = c;
        }

        dfs(0, nums[0]);

        System.out.println(best);
    }

    static void dfs(int idx, long cur) {
        if (idx == opCnt) {
            best = Math.max(best, cur);
            return;
        }

        long val1 = calc(cur, ops[idx], nums[idx + 1]);
        dfs(idx + 1, val1);

        if (idx + 1 < opCnt) {
            long inside = calc(nums[idx + 1], ops[idx + 1], nums[idx + 2]); // (b op c)
            long val2 = calc(cur, ops[idx], inside);                        // cur op (inside)
            dfs(idx + 2, val2);
        }
    }

    static long calc(long a, char op, long b) {
        if (op == '+') return a + b;
        if (op == '-') return a - b;
        return a * b; // '*'
    }
}