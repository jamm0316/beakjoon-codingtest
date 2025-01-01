[<back](https://www.notion.so/25239624ade64d8c86a9398a8d33a409?pvs=21)

---

page link : [https://www.acmicpc.net/problem/11726](https://www.acmicpc.net/problem/11726)

---

# 풀이전략

1. 맨 오른 쪽에 `—` 가 오는 경우 : dp[i - 2]와 동일
2. 맨 오른 쪽에 `|` 가 오는 경우 : dp[i - 1]와 동일
3. 따라서 dp[i] = dp[i - 1] + dp[i-2]
</aside>

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] dp = new int[n + 1];
        
        dp[1] = 1;

        if (n > 1) {
            dp[2] = 2;
        }
        
        for (int i = 3; i < dp.length; i++) {
                dp[i] = (dp[i - 1] + dp[i - 2]) % 10007;
        }
        
        System.out.println(dp[n]);
    }
}

```
