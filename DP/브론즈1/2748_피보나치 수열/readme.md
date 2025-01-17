page link : [https://www.acmicpc.net/problem/2748
](https://www.acmicpc.net/problem/2748
)
---

# 풀이전략

1. dp를 이용하여 각 숫자를 배열에 저장한다.
2. 각 배열에 O(1)으로 접근하여 해당 숫자를 가져와 현재 배열을 최신화 한다.
3. 점화식
    1. `dp[n] = dp[n-1] + dp[n-2]`

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
package dp;

import java.util.*;

public class P2748 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[N + 1];
        if (1 < N) {
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            System.out.println(dp[N]);
        } else {
            System.out.println(N);
        }
    }
}

```

## 해결한 오류

### 1. 범위 설정 문제

기존 코드에서는 조건없이 0, 1의 경우를 미리 설정하고 i=2 부터 순회하므로 만일 N값으로 0이 들어오는 경우 dp[2]가 설정되지 않아 **ArrayIndexOutOfBoundsException**이 나옴.

**기존 코드**

```java
int[] dp = new int[N + 1];
dp[0] = 0;
dp[1] = 1;
for (int i = 2; i < dp.length; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
```

**수정된 코드**

```java
long[] dp = new long[N + 1];
        if (1 < N) {
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            System.out.println(dp[N]);
        } else {
            System.out.println(N);
        }
```

---

- 기존코드
    
    ```python
    import java.util.*;
    import java.io.*;
    
    public class Main {
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int N = input.nextInt();
            int[] dp = new int[N + 1];
            dp[0] = 0;
            dp[1] = 1;
            for (int i = 2; i < dp.length; i++) {
                dp[i] = dp[i - 1] + dp[i - 2];
            }
            System.out.println(dp[N]);
        }
    }
    ```
