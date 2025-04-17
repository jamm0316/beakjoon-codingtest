page link : [https://www.acmicpc.net/problem/2096](https://www.acmicpc.net/problem/2096)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 각 숫자의 합이 최댓값, 최소값

- 최적화된 풀이전략
    1. 최소 dp, 최대 dp를 슬라이딩 윈도우로 만든다. (`maxDp[2][3]`, `minDp[2][3]`)
    2. 각 단계 별로 최소 dp, 최대 dp를 업데이트 한다.
        1. 순회별로 0번과 1번 index를 cur, prev로 번갈아가며 사용하며 메모리 최적화
    3. 최대값 최소값을 출력한다.
        1. 모든 순회를 마치고 dp의 0 또는 1번째 index가 cur 역할을 수행하고 있다.
            - N이 짝수라면, 홀수 index가 최신값(ex. N=2일 경우, dp[1]이 최신값)
            - N이 홀수라면, 짝수 index가 최신값(ex. N=3일 경우, dp[0]이 최신값)
        2. 따라서 최신 dp의 index는 `(N - 1) % 2`
- 성공한 풀이전략
    1. 최소 dp, 최대 dp를 만든다.
    2. 각 단계 별로 최소 dp, 최대 dp를 업데이트 한다.
    3. 각 dp별 마지막 단계를 순회하며, 최대값 최소값을 출력한다.

## 🎨 사용된 알고리즘
DP, 슬라이딩윈도우

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        int[][] maxDp = new int[2][3];
        int[][] minDp = new int[2][3];
        int[] input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        System.arraycopy(input, 0, maxDp[0], 0, 3);
        System.arraycopy(input, 0, minDp[0], 0, 3);

        for (int i = 1; i < N; i++) {
            input = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
            int cur = i % 2, prev = (i - 1) % 2;

            maxDp[cur][0] = Math.max(maxDp[prev][0], maxDp[prev][1]) + input[0];
            maxDp[cur][1] = Math.max(Math.max(maxDp[prev][0], maxDp[prev][1]), maxDp[prev][2]) + input[1];
            maxDp[cur][2] = Math.max(maxDp[prev][1], maxDp[prev][2]) + input[2];

            minDp[cur][0] = Math.min(minDp[prev][0], minDp[prev][1]) + input[0];
            minDp[cur][1] = Math.min(Math.min(minDp[prev][0], minDp[prev][1]), minDp[prev][2]) + input[1];
            minDp[cur][2] = Math.min(minDp[prev][1], minDp[prev][2]) + input[2];
        }
        int last = (N - 1) % 2;
        int max = Arrays.stream(maxDp[last]).max().getAsInt();
        int min = Arrays.stream(minDp[last]).min().getAsInt();
        bw.write(max + " " + min);
        bw.close();
        br.close();
    }
}

```

# 🪄 해결한 오류

## 1. 메모리 낭비를 없애는 방법: 슬라이딩 윈도우(Sliding Window)

### 슬라이딩 윈도우(Sliding Window)

**📌 정의**

전체 데이터에서 **일부 구간만을 이동하면서 사용하는 알고리즘 기법**

- 고정된 크기의 **창(window)**을 사용해서 데이터를 한 칸씩 밀어가며 계산
- **이전 계산값을 재사용**함으로써 불필요한 연산을 줄이는 것이 핵심
- 주로 **연속된 구간핪, 최소/최대, 구간 조건 만족 여부** 등을 빠르게 구할 때 사용

**📍대표적 사용 예시**

1. 투 포인터 기법 - 배열 내 구간의 합, 조건 만족 여부 체크
2. DP에서 이전 상태만 필요한 경우 - 필요한 행(혹은 값)만 유지
3. 그래프, 문자열 탐색의 최적화 - 캐시처럼 구간을 유지하며 전진

---

### 🪄 슬라이딩 윈도우가 효과적인 이유

- 전체 줄이 최대 100,000개 → int[N][3]은 메모리 낭비
- 하지만 사실 `i-1` 행 값만 있으면 `i`행 구할 수 있음
- 따라서 2 * 3만 있으면 충분함.

---

### **✅ 코드에서 슬라이딩 윈도우가 적용된 부분**

```java
int[][] maxDp = new int[2][3];
int[][] minDp = new int[2][3];
```

N * 3 전체 배열을 만들지 않고, 딱 **2줄짜리 배열만 번갈아가며 사용**하므로 **메모리 절약에 최적화**

---

**⚙️ 작동방식**

여기서는 `row`의 갯수와 `index`의 개념이 혼용되어 사용된다.

따라서 짝수와 홀수가 헷갈릴 수 있는다.

아래 개념을 확실히 잡고 가면 덜 헷갈릴 것이다.

1. 슬라이딩 윈도우로 사용되고 있는 `dp`의 `index`는 일단 `0`과 `1`밖에 없다.
2. 각 순회하는 `row`(`i`)는 짝수이거나 홀수 둘 중하나다.
3. `N`은 짝수이거나 홀수 둘 중 하나다.

- **모든 row 순회 마다, 슬라이딩 윈도우에서 데이터가 갱신된 부분과 이전 데이터 부분 나누기**
    
    ```java
    int cur = i % 2, prev = (i - 1) % 2;
    ```
    
    위 처럼 `cur`, `prev`는 슬라이딩 윈도우의 `index` 역할을 하고, `i`는 실제 `input`의 `index`이다. 
    
    `i`가 `1`인 경우를 예로 들어보자.
    
    이 경우, 현재 `2`번째 `row`를 탐색 중이다. 
    
    슬라이딩 윈도우에서도 `2`번째 `row`인 `1`번 `index`가 갱신된 `row`가 된다.
    
    따라서 `i = 1` 일 경우 `dp[1]`이 갱신된 값(`cur`)이다.
    
    이어서 `i = 2` 일 경우 `dp[1]`은 `prev`로 사용되고, `dp[0]`이 `cur`가 되어야한다.
    
    즉, 아래와 같은 일반화가 가능하다.
    
    > `i` 가 홀수 일 때, `cur`은 `1`, `prev`은 `0`
    `i` 가 짝수 일 때, `cur`은 `0`, `prev`은 `1`
    > 

따라서, 아래와 같은 공식을 사용할 수 있다.

`cur = i % 2` (홀수면 1, 짝수면 0 반환)

`prev = (i - 1) % 2` (홀수면 0, 짝수면 1 반환)

- **모든 순회가 끝나면, 가장 최근 갱신된 슬라이딩 윈도우 인덱스 찾기**
    
    위와 같은 방식으로 모든 탐색이 끝난 후 `dp`의 최근 갱신된 `row`를 알아야 그 `row`중 최대, 최소값을 구할 수 있다.
    
    1. 이 때, `N`은 전체 `row의 갯수`이고 이 것을 `index`로 바꿔야하므로 `-1`을 해준다.
    2. 이 것은 다시 말해 위 순회에서 마지막 row의 index인 `i`와 같고, `cur = i % 2` 였으므로 아래와 같은 공식이 나오게 된다.
        
        ```java
        int last = (N - 1) % 2;
        ```
        
    3. 헷갈린다면, 예시를 들어보자.
        1. N = 3일 때,
            
            `i = 1`부터 각 순회별 최신 `dp`
            
            - i = 1: dp[1]
            - i = 2: dp[0]
        2. 따라서, `(3 - 1) % 2 = 0` 이 성립한다.
