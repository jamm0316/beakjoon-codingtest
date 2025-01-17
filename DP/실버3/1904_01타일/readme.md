page link : https://www.acmicpc.net/problem/1904

---

# 풀이전략

피보나치 수열과 같은 문제

**점화식**

`dp[i] = dp[i - 1] + dp[i - 2]`

## 사용된 알고리즘

다이나믹 프로그래밍
---

# code

## Java

```java
import java.util.*;

public class Main {
    static final int STATIC_NUMBER = 15746;
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        long[] dp = new long[N + 1];
        if (N <= 1) {
            System.out.println(N);
        } else {
            dp[0] = 0;
            dp[1] = 1;
            dp[2] = 2;
            for (int i = 3; i <= N; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % STATIC_NUMBER;
            }
            System.out.println(dp[N]);
        }
    }
}
```

## 해결한 오류

### 1. dp[i]는 최종결과를 담고 dp[N]은 단순 출력용이다.

기존 코드에서는 최종결과 부분에서 `STATIC_NUMBER` (15746)을 나누어줬는데 그렇게된다면, `dp[i]`의 숫자는 무한정 커질 것임.

**기존코드**

```java
for (int i = 3; i <= N; i++) {
    dp[i] = dp[i - 1] + dp[i - 2];
}
System.out.println(dp[N] % STATIC_NUMBER);
```

그러나, `dp[i]`는 정답지 역할을 하고, 출력시에는 `dp[N]`만 출력하는 것이므로 아래와 같이 `dp[i]`를 계산할 때 `STATIC_NUMBER`의 모듈러를 이용하는 것이 올바른 풀이법이다.

**수정된 코드**

```java
for (int i = 3; i <= N; i++) {
dp[i] = (dp[i - 1] + dp[i - 2]) % STATIC_NUMBER;
}
System.out.println(dp[N]);
```

---

- 기존코드
    
    ```java
    import java.util.*;
    
    public class Main {
        static final int STATIC_NUMBER = 15746;
        public static void main(String[] args) {
            Scanner input = new Scanner(System.in);
            int N = input.nextInt();
            long[] dp = new long[N + 1];
            if (N <= 1) {
                System.out.println(N);
            } else {
                dp[0] = 0;
                dp[1] = 1;
                dp[2] = 2;
                for (int i = 3; i <= N; i++) {
                    dp[i] = dp[i - 1] + dp[i - 2];
                }
                System.out.println(dp[N] % STATIC_NUMBER);
            }
        }
    }
    ```
