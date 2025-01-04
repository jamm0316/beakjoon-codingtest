page link : [https://www.acmicpc.net/problem/1912](https://www.acmicpc.net/problem/1912)

---

# 풀이전략

1. 동적 계획법 사용
    - 각 위치 i에서 끝나는 최대 연속합 저장
    - 이를 통해 O(N) 시간 복잡도로 문제해결 가능
2. 점화식
    1. dp[i]를 i번째 요소 마지막으로 하는 최대 연속합으로 정의
    2. 점화식
        
        $dp[i] = max(dp[i-1] + sequence[i], sequence[i])$
        
        이전 연속합에 현재 값을 더한 것과, 현재 값 자체 중 더 큰 값을 선택
        
    3. 초기값
        
        `dp[0] = seqeucne[0]`
        
3. 최적화
    1. dp 배열을 사용할 필요 없이, 현재와 이전 값을 저장하는 변수 두개만 사용하여 공간 복잡도를 O(1)로 줄일 수 있음.

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        int N = Integer.parseInt(st.nextToken());
        int[] sequence = Arrays.stream(br.readLine().split(" "))
				        .mapToInt(Integer::parseInt)
				        .toArray();
       
       int maxSum = sequence[0];
       int currentSum = sequence[0];
       
       for (int i = 1; i < N; i++) {
           currentSum = Math.max(currentSum + sequence[i], sequence[i]);
           maxSum = Math.max(maxSum, currentSum);
       }
       
       System.out.println(maxSum);
    }
}
```

## 해결한 오류

### 1. 시간 복잡도 O(N^2) → O(N)

기존에는 for 문에서 while문을 다시 순회하며 모든 경우의 수에 대해 탐색함.

이 경우 이중 순회문을 사용하여 O(N^2)의 시간복잡도가 나오므로 비효율 적임.

다이나믹 프로그래밍 기법을 이용하면 시간 복잡도를 O(N)까지 줄일 수 있음.

현재 합: `currentSum` 을 이용.

`현재 합 + sequence[현재]`, `sequence[현재]` 중 더 큰 값을 현재합으로 사용.

이렇게 하면, 현재까지의 합보다 현재 `sequence`의 값이 더 클때 해당 위치에서 다시 수열의 합을 계산할 수 있음. 따라서, 모든 경우의 수를 탐색하지 않아도 최적해를 구할 수 있음.

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
            int[] sequence = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int[] dp = new int[N];
    
            for (int i = 0; i < N; i++) {
                int count = i;
                int maxScore = 0;
                int eachScore = 0;
                while (count < N) {
                    eachScore += sequence[count];
                    maxScore = Math.max(maxScore, eachScore);
                    count++;
                }
                dp[i] = maxScore;
            }
            int result = Arrays.stream(dp).max().getAsInt();
            System.out.println(result);
        }
    }
    
    ```
