page link : [https://www.acmicpc.net/problem/9465](https://www.acmicpc.net/problem/9465)

---

# 💡 풀이전략
- 핵심 아이디어
    - 스티커가 2 x n 배열일 때, 각 위치에서 선택 가능한 최댓값을 이전상태를 참고해서 구한다.
    - `dp[0][i]`: `i`번째 배열의 위쪽 스티커를 선택했을 때 얻을 수 있는 최댓값
        - 위쪽 `i`번째 스티커를 선택하면, 아래쪽 `i - 1` 또는 `i - 2`를 선택할 수 있다.
    - `dp[1][i]`: `i`번째 배열의 아랫쪽 스티커를 선택했을 때 얻을 수 있는 최댓값
        - 아래쪽 `i`번째 스티커를 선택하면, 위쪽 `i - 1` 또는 `i - 2`를 선택할 수 있다.
- 점화식
    - `dp[0][i] = max(dp[1][i-1], dp[1][i-2]) + sitcker[0][i]`
    - `dp[1][i] = max(dp[0][i-1], dp[0][i-2]) + sitcker[1][i]`
    
    (이전 열 또는 그 전 열의 대각선에서 온 것 중 최대값 + 현재점수)

## 🎨 사용된 알고리즘
다이나믹프로그래밍

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            int[][] stickers = new int[2][N + 1];
            int[][] dp = new int[2][N + 1];

            for (int i = 0; i < 2; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 1; j <= N; j++) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            dp[0][1] = stickers[0][1];
            dp[1][1] = stickers[1][1];

            for (int i = 2; i <= N; i++) {
                dp[0][i] = Math.max(dp[1][i - 1], dp[1][i - 2]) + stickers[0][i];
                dp[1][i] = Math.max(dp[0][i - 1], dp[0][i - 2]) + stickers[1][i];
            }

            sb.append(Math.max(dp[0][N], dp[1][N])).append('\n');
        }

        System.out.print(sb);
    }
}
```
