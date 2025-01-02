page link : [https://www.acmicpc.net/problem/11053](https://www.acmicpc.net/problem/11053)

---

# 풀이전략

1. `dp`는 각 수열의 숫자가 마지막 수열의 마지막 숫자라 가정했을 때, 이보다 작은 수일 경우의 `count` 갯수
2. `sequence`를 1번부터 순회하며 이보다 작은 수를 재 순회하여, 작은 수가 있을 시 `dp[i]`를 `dp[j]+1`로 갱신 한다.
    1. 따라서 `j`번째 수가 `i`번째 수보다 작을 시, `j`번째 수까지 `count`된 수열에 `+1` 을 해주는 것이므로, 최적해를 구할 수 있다.


## 사용된 알고리즘

다이나믹프로그래밍

---

# code

## Java

```java
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] sequence = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int[] dp = new int[N];
        Arrays.fill(dp, 1);

        for (int i = 1; i < N; i++) {
            for (int j = 0; j < i; j++) {
                if (sequence[j] < sequence[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
        }
        int lis = Arrays.stream(dp).max().getAsInt();
        System.out.println(lis);
    }
}

```
