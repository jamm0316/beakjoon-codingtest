page link : [https://www.acmicpc.net/problem/17404](https://www.acmicpc.net/problem/17404)

---

# 💡 풀이전략
- 구하고자 하는 값
    - RGB 최소값
- 풀이 전략
    1. 원형 제약 때문에 1번과 N번이 같은 색이 되면 안됨.
    2. 그래서 startColor를 정해두고, dp[1][startColor] = cost[1][startColor], 나머지 색은 INF로 초기화
    3. i=2…N까지 일반 RGB거리 전이식으로 채움
    4. 답은 마지막 색 ≠ startColor 중 최소값
    5. 세 번 반복

## 🎨 사용된 알고리즘
다이나믹프로그래밍

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] cost;
    static final int INF = 1_000_000_000;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        cost = new int[N][3];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            cost[i][0] = Integer.parseInt(st.nextToken());
            cost[i][1] = Integer.parseInt(st.nextToken());
            cost[i][2] = Integer.parseInt(st.nextToken());
        }

        int answer = INF;

        for (int start = 0; start < 3; start++) {
            int[][] dp = new int[N][3];

            //1. 시작색 설정
            for (int c = 0; c < 3; c++) {
                dp[0][c] = (c == start) ? cost[0][c] : INF;
            }

            for (int i = 1; i < N; i++) {
                dp[i][0] = cost[i][0] + Math.min(dp[i - 1][1], dp[i - 1][2]);
                dp[i][1] = cost[i][1] + Math.min(dp[i - 1][0], dp[i - 1][2]);
                dp[i][2] = cost[i][2] + Math.min(dp[i - 1][0], dp[i - 1][1]);
            }

            for (int end = 0; end < 3; end++) {
                if (end == start) continue;
                answer = Math.min(answer, dp[N - 1][end]);
            }
        }

        System.out.println(answer);
    }
}
