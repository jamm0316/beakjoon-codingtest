page link : [https://www.acmicpc.net/problem/9095](https://www.acmicpc.net/problem/9095)

---

# 풀이전략

1. 동적프로그래밍 배열정의
    1. `dp[i]` → 숫자 i를 1,2,3의 합으로 나타내는 방법의 수로 정의
2. 점화식 설정
    1. `dp[i] = dp[i-1] + dp[i-2] + dp[i-3]`
    2. 여기서
        1. `dp[i-1]`은 마지막에 1을 추가한 경우,
        2. `dp[i-2]`은 마지막에 2을 추가한 경우,
        3. `dp[i-3]`은 마지막에 3을 추가한 경우
3. 기저조건
    1. dp[1] = 1
    2. dp[2] = 2
    3. dp[3] = 4

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class P9095copy {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int maxN = 11;
        int N = Integer.parseInt(st.nextToken());
        int[] dp = new int[maxN + 1];

        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int i = 4; i < dp.length; i++) {
            dp[i] = dp[i - 1] + dp[i - 2] + dp[i - 3];
        }

        for (int i = 0; i < N; i++) {
            System.out.println(dp[Integer.parseInt(
                    new StringTokenizer(br.readLine()).nextToken())]);
        }
    }
}
```

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            int[] queries = new int[N];
            List<Integer> dp = new ArrayList<>();
    
            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                queries[i] = Integer.parseInt(st.nextToken());
            }
    
            dp.add(0, 1);
            dp.add(1, 1);
            dp.add(2, 2);
            dp.add(3, 4);
            for (int query : queries) {
                if (query > 3) {
                    for (int i = 4; i <= query; i++) {
                        dp.add(i, dp.get(i - 1) + dp.get(i - 2) + dp.get(i - 3));
                    }
                    System.out.println(dp.get(query));
                } else {
                    System.out.println(dp.get(query));
                }
            }
        }
    }
    
    ```
