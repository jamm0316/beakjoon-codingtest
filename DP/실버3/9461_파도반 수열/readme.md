page link : [https://www.acmicpc.net/problem/9461](https://www.acmicpc.net/problem/9461)

---

# 풀이전략

**점화식**

`dp[i] = dp[i - 3] + dp[i - 2]`

dp[i]가 int타입을 초과할 수 있으므로 `long[] dp`로 선언

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] queries = new int[N];
        long[] dp = new long[101];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            queries[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;
        for (int i = 4; i < 101; i++) {
            dp[i] = dp[i - 3] + dp[i - 2];
        }

        for (int query : queries) {
            sb.append(dp[query]).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}
```
