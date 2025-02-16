page link : [https://www.acmicpc.net/problem/11055](https://www.acmicpc.net/problem/11055)

---

# 풀이전략
1. brute force로 이중 for문을 돈다.
    
    ```java
    for (int i = 1; i < N; i++) {
        for (int j = 0; j < i; j++ {
        }
    }
    ```
    
    1. `sequence[j] < sequence[i]`  (순열을 이룰 경우)
        1. `dp[i] = Math.max(dp[i], dp[j] + sequence[i]`
2. dp배열 중 가장 큰값을 반환한다.

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
        int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int[] dp = new int[N];

        //initialize
        for (int i = 0; i < N; i++) {
            dp[i] = sequence[i];
        }

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + sequence[i]);
                }
            }
        }
        System.out.println(Arrays.stream(dp).max().getAsInt());
    }
}

```

## 해결한 오류

### 1. 이전 모든 부분 수열을 탐색

증가하는 부분 수열의 경우 현재 이전의 모든 수열을 탐색하여 `dp[i]`가 최대가 되는 경우를 선택해야 한다.

기존 코드처럼 `minIdx`를 설정해 두는 것의 맹점은 각 숫자(`i`)마다 최소 `index`가 다를 수 있다는 점이다.

ex) 60 90 50 70 100

위 예시의 경우 `60 + 90 + 100` 과 `60 + 70 + 100`을 비교해야하는데, 최소 `index`를 사용하면 위와 같은 경우를 비교할 수 없다.

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
            int[] sequence = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int[] dp = new int[N];
    
            //initialize
            int minIdx = 0;
            dp[0] = sequence[0];
            if (sequence[0] > sequence[1]) {
                dp[1] = sequence[1];
                minIdx = 1;
            } else {
                dp[1] = dp[0] + sequence[1];
            }
    
            for (int i = 2; i < N; i++) {
                if (sequence[i] > sequence[i - 1]) {
                    dp[i] = sequence[i] + dp[i - 1];
                } else {
                    dp[i] = sequence[i] + dp[minIdx];
                    minIdx = i;
                }
            }
    
            System.out.println(Arrays.stream(dp).max().getAsInt());
        }
    }
    
    ```
