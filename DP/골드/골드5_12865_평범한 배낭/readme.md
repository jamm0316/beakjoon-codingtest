page link : [https://www.acmicpc.net/problem/12865](https://www.acmicpc.net/problem/12865)

---

# 풀이전략

1. 들어오는 값
    1. 물건의 개수 `N`, 가능한 무게 `K`
    2. 물건의 개수 만큼
        1. 물건의 무게 `W`, 물건의 가치 `V`
2. dp[w]를 이용하여
    1. `w`무게로 얻을 수 있는 최대 가치 저장
    2. `w = K, w >= weight; w--` 순회
        1. `dp[w] = Math.max(dp[w], dp[w - weight] + value)`

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
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int weight = Integer.parseInt(st.nextToken());
            int value = Integer.parseInt(st.nextToken());

            for (int w = K; w >= weight; w--) {
                dp[w] = Math.max(dp[w], dp[w - weight] + value);
            }
        }
        System.out.println(dp[K]);
    }
}
```

## 해결한 오류

### 1. 역순으로 순회하는 이유

배낭 문제에서 **역순으로 탐색**하는 이유는 **중복 계산 방지**를 위해서입니다.

**배경**

- 만약 **정순(작은 무게부터 큰 무게로)**으로 탐색하면, 동일한 반복문에서 새롭게 갱신된 `dp[w]` 값이 다시 사용될 수 있습니다. 이렇게 되면 하나의 아이템을 중복으로 사용하는 상황이 발생할 수 있습니다.

**예시**

- `weight` = 3, `value` = 10, `K` = 5일 때, 정순 탐색을 하면:
    1. `w = 3`: `dp[3] = dp[0] + 10` → 갱신됨.
    2. `w = 4`: **이미 갱신된** `dp[3]` **값을 사용**하여 `dp[4] = dp[1] + 10` 대신 `dp[4] = dp[3] + 10`이 되어 잘못된 결과를 초래합니다.

**역순 탐색의 효과:**

- 역순으로 탐색하면, 현재 아이템(`weight`, `value`)을 포함한 결과만 갱신하고, 이전의 상태는 그대로 유지됩니다. 이렇게 하면 같은 아이템을 중복으로 사용하는 문제를 방지할 수 있습니다.

### 2. `dp[w - weight] + value` 값과 비교하는 이유

배낭 문제의 핵심은 다음 두 가지 선택지 중 최적값을 선택하는 것입니다:

1. **현재 아이템을 포함하지 않는 경우**
    - 이 경우, `dp[w]` 값은 그대로 유지됩니다.
    - 즉, 이전까지 계산한 값인 `dp[w]`를 선택합니다.
2. **현재 아이템을 포함하는 경우**
    - 이 경우, “현재 무게에서 아이템의 무게를 뺀 상태의 최대 가치(`dp[w - weight]`) + 현재 아이템의 가치(`value`)“를 더합니다.
    - 즉, 아이템을 넣는다면 무게를 차지하므로, 나머지 무게 상태(`w - weight`)에서의 최적해를 가져와야 합니다.

**계산 식:**

```java
dp[w] = Math.max(dp[w], dp[w - weight] + value);
```

**이 식의 의미:**

- `dp[w]`: 현재 무게에서 아이템을 포함하지 않는 경우의 최대 가치.
- `dp[w - weight] + value`: 아이템을 포함하는 경우의 최대 가치.

최적해를 구하기 위해 이 둘을 비교합니다.
