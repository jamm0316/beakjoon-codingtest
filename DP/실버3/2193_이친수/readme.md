page link : [https://www.acmicpc.net/problem/2193](https://www.acmicpc.net/problem/2193)

---

# 풀이전략

점화식

`dp[i] = dp[i - 2] + dp[i - 1];`

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
package dp;

import java.util.*;

public class P2193 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[N + 1];

        if (N <= 1) {
            System.out.println(N);
        } else {
            dp[1] = 1;
            for (int i = 2; i <= N; i++) {
                dp[i] = dp[i - 2] + dp[i - 1];
            }
            System.out.println(dp[N]);
        }
    }
}
```

## 해결한 오류

### 1. 위 점화식의 경우 47만 되어도 int 범위 초과

46: 1,836,311,903

47: 2,971,215,073 (초과)

`int` 변수 범위: -2, 147,483,648 ~ -2, 147,483,647

---
