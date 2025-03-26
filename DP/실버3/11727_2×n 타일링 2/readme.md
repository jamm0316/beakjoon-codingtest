page link : [https://www.acmicpc.net/problem/11727](https://www.acmicpc.net/problem/11727)

# 풀이전략

**점화식 세우기**

1. `dp[n-1]`에서 `1x2`타일 1개를 세로로 붙히면: `dp[n-1]`
2. `dp[n-2]`에서 `2x1`을 가로로 2개 또는 `2x2`를 1개 붙히면: `dp[n-2] * 2`

따라서, `dp[n] = dp[n-1] + 2 * dp[n-2]`

## 사용된 알고리즘

다이나믹프로그래밍

---

# code

## Java

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 3;

        if (n > 2) {
            for (int i = 3; i <= n; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2] * 2) % 10_007;
            }
        }
        System.out.println(dp[n]);
    }
}
```

## 해결한 오류

### 1. `dp[n-1]`과 `dp[n-2]` 는 곂치는 경우의 수가 없을까?

**없다.**

- dp[n-1]은 이미 잘 쌓아진 dp[n-1] 상태에 1×2 타일을 세로로 하나 더 붙이는 경우다.
- 반면 dp[n-2]는 2×1 타일 두 장을 가로로 붙이거나, 2×2 타일 한 장을 붙여서 두 칸을 한 번에 채우는 경우다.

즉, **사용하는 타일의 종류와 방향, 칸을 채우는 방식이 서로 다르기 때문에 겹치지 않는다.**

→ 따라서 dp[n] = dp[n-1] + 2 * dp[n-2]

---

그런데 “세로 1×2 타일 두 장을 이어 붙이는 경우”는 어떻게 될까?

그건 사실상 dp[n-1]에서 세로 타일 하나 붙이고,

그다음 다시 dp[n-1]을 이용해 또 하나를 붙이는 흐름이기 때문에

이미 dp[n-1]의 반복 과정 안에 포함된 경우다.

즉, **새로운 방식이 아니기 때문에 dp[n-2]에서 따로 고려하지 않는다.**

---

그래서 dp[n-2]에서는 오직

- 가로 2×1 타일 두 장
- 정사각형 2×2 타일 한 장만 고려해서 *2를 해주는 것이다.

---
