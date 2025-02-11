page link : [https://www.acmicpc.net/problem/1003](https://www.acmicpc.net/problem/1003)

---

# 풀이전략

1. 2차원 배열을 만들어 0과 1의 갯수를 저장한다 `int[][]`
2. 전체 테스트 케이스 N을 받는다.
3. dp 사용
    1. 테스트 케이스에서 들어오는 n을 받는다.
    2. n번을 순회하며 조건문을 만든다.
        - 점화식을 만든다
            - `dp[j][0] = dp[j - 1][0] + dp[j - 2][0];`
            `dp[j][1] = dp[j - 1][1] + dp[j - 2][1];`
        - 조건문을 통해 1과 0을 업데이트한다.

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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int[][] dp = new int[n + 1][2];
            dp[0][0] = 1;
            dp[0][1] = 0;
            if (n > 0) {
                dp[1][0] = 0;
                dp[1][1] = 1;
                if (n > 1) {
                    for (int j = 2; j <= n; j++) {
                        dp[j][0] = dp[j - 1][0] + dp[j - 2][0];
                        dp[j][1] = dp[j - 1][1] + dp[j - 2][1];
                    }
                }
            }
            bw.write(dp[n][0] + " " + dp[n][1]);
            bw.newLine();
        }
        bw.close();
        br.close();
    }
}

```

## 해결한 오류

### 1. 시간복잡도 최적화

**dp 사용 시 시간 복잡도**: `O(n)`

**재귀 사용 시 시간복잡도**: `O(2^n)`

→ `n ≥ 30` 이면 연산횟수가 **1,073,741,824(약 10억)회**로 **1초**.

→ `n ≥ 28` 이면 연산횟수가 **268,436,456(약 2억 6천)회**로 **0.25초**.

즉, 위 문제의 경우 **n이 최대 40**이므로 

연산횟수는 **1,099,511,627,776(1조)회**로

**약 1024초, 17분이 소요된다.**

따라서, 재귀함수로는 풀이할 수 없다.

---

- 기존코드
    
    ```python
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
    
            for (int i = 0; i < N; i++) {
                int[] dp = new int[2];
                st = new StringTokenizer(br.readLine());
                int n = Integer.parseInt(st.nextToken());
                dfs(n, dp);
                bw.write(dp[0] + " " + dp[1]);
                bw.newLine();
            }
            bw.close();
            br.close();
        }
    
        private static int dfs(int n, int[] dp) {
            if (n == 0) {
                dp[0] += 1;
                return 0;
            } else if (n == 1) {
                dp[1] += 1;
                return 1;
            } else {
                return dfs(n - 1, dp) + dfs(n - 2, dp);
            }
        }
    }
    
    ```
