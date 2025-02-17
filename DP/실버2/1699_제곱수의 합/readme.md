page link : [https://www.acmicpc.net/problem/1699](https://www.acmicpc.net/problem/1699)

---

# 풀이전략

1. `dp[i] = i`로 `1^2`로 만들 수 있는 최악의 항의 갯수로 초기화
2. 이전에 구한 값(제곱근으로 만들 수 있는 항의 갯수) 중 현재 계산하고자 하는 제곱근을 더했을 때(`+1`) 도출되는 최소 제곱근 갯수.
3. `dp`를 `N`회, 그리고 `j * j < i (Math.sqrt(i))`회 순회
4. 점화식
    1. `dp[i] = Math.min(dp[i], dp[i - j * j])`
    2. 위의 경우 `i-j^2`라는 숫자에서 구한 최소 항의 갯수에 `j^2`라는 제곱수를 더했을 때, 항의 갯수를 구하는 공식
    3. 왜? 예를 들어 `i = 13`인 경우, `j`는 `1 ~ 3`이 가능
        1. 1 → 12의 최소항 + 1항(1^2) = 4
        2. 2 → 9의 최소항 + 1항(2^2) = 2
        3. 3 → 4의 최소항 + 1항(3^2) = 2
        
        위 경우 `dp[13] = 2` 가 됨.
        
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
        int[] dp = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            dp[i] = i;
            for (int j = 1; j <= (int) Math.sqrt(i); j++) {
                dp[i] = Math.min(dp[i], dp[i - (int) Math.pow(j, 2)] + 1);
            }
        }
        System.out.println(dp[N]);
    }
}

```

## 해결한 오류

### 1. 바텀업 방식 사용

탑 다운 방식 사용시 `N = 12` 일 때 오류

12는 `2^2 * 3` 으로 최소항이 3개이다.

그러나 탑다운 방식을 사용하면

`12 - 3^2 = 3` 

`3 - 1^2 = 2`

`2 - 1^2 = 1`

`1 - 1^2 = 0`

위 처럼 4개가 나오게 된다.

그러나 바텀업 방식을 사용하면

`dp[1] = 1, dp[2] =2 dp[3] =3, dp[4] = 1….`

위 dp 배열을 이용해

`dp[12] = dp[11] + dp[1] = 3 + 1 = 4`

`dp[12] = dp[8] + dp[2] = 2 + 1 = 3`

`dp[12] = dp[3] + dp[3] = 3 + 1 = 4`

으로 `3`이라는 최소항을 구할 수 있게된다.

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
            int[] dp = new int[(int) Math.sqrt(N) + 1];
    
            while (N > 0) {
                int sqrt = (int) Math.sqrt(N);
                dp[sqrt] += 1;
                N -= (int) Math.pow(sqrt, 2);
            }
    
            System.out.println(Arrays.stream(dp).sum());
        }
    }
    
    ```
