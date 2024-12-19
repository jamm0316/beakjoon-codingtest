# 💡 풀이전략

작은 숫자부터 계산 시작

1. 숫자 i를 1로 만드는 방법
    1. i - 1
    2. i/2
    3. i/3
    
    위 방법 중 최소 횟수 선택
    
2. 예시
    1. dp[6] = dp[3] + 1
        1. 3을 2로 나눴던 수는 이미 계산해 두었으니, 6을 2로 나누는 경우만 +1 한다.

## 🎨 사용된 알고리즘

> [!tip]
> 다이나믹 프로그래밍
> 
---

# code

## Java

```java
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int[] dp = new int[N + 1];

        dp[1] = 0;

        for (int i = 2; i < dp.length; i++) {
            dp[i] = dp[i - 1] + 1;

            if (i % 2 == 0) {
                dp[i] = Math.min(dp[i], dp[i/2] + 1);
            }

            if (i % 3 == 0) {
                dp[i] = Math.min(dp[i], dp[i/3] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}
```
