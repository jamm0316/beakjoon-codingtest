page link : [https://www.acmicpc.net/problem/2156](https://www.acmicpc.net/problem/2156)

---

# 풀이전략

1. 문제 분석
    1. 최대로 마실수 있는 포도주 양
    2. 조건: 연속되어있는 3잔 마시면 안됨.
2. dp배열의 정의
    1. `dp[i]`를 `i`번째 잔까지 마셨을 때 최대로 마실 수 있는 포도주의 양으로 정의
3. 점화식
    
    **현재까지의 최대 구하는 법**
    
    - 현재 잔 안마신다 + `i-1` 최대
    - 현재 잔 마신다 + `i-2` 최대
    - 이전잔 현재 잔 마신다 + `i-3`최대
    
    ---
    
    1. 현재 잔을 마시지 않는 경우: `dp[i] = dp[i-1]`
    2. 현재 잔을 마시는 경우
        1. 이전 잔을 포함하고 현재잔을 마시는 경우
            
            `dp[i] = dp[i-2] + wine[i]`
            
        2. 이전 두잔을 포함하지 않고 현재잔을 마시는 경우
            
            `dp[i] = dp[i-3] + wine[i-1] + wine[i]`
            
    3. 최종점화식
        
        `dp[i] = max(dp[i-1], dp[i-2] + wine[i], dp[i-3] + wine[i-1] + wine[i])`
        
4. 초기조건
    1. `dp[0] = wine[0]`
    2. `dp[1] = wine[0] + wine[1]`
    3. `dp[2] = max(wine[0] + wine[1], wine[0] + wine[2], wine[1] + wine[2])`

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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] wine = new int[N];
        int[] dp = new int[N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            wine[i] = Integer.parseInt(st.nextToken());
        }

        dp[0] = wine[0];
        if (N > 1) {
            dp[1] = wine[0] + wine[1];
        }

        if (N > 2) {
            dp[2] = Math.max(dp[1], Math.max(wine[2] + dp[0], wine[1] + wine[2]));
        }

        for (int i = 3; i < N; i++) {
            dp[i] = Math.max(dp[i - 1], Math.max(wine[i] + dp[i - 2], wine[i] + wine[i - 1] + dp[i - 3]));
        }

        System.out.println(dp[N - 1]);
    }
}

```
