page link : [https://www.acmicpc.net/problem/9251](https://www.acmicpc.net/problem/9251)

---

# 풀이전략

1. dp table 정의
    1. `dp[i][j]`는 `A[0:i]`와 `B[0:j]`까지의 최장 공통 부분 수열의 길이 저장
2. 점화식
    1. `A[i-1] == B[j-1]`일 때:
        
        `dp[i][j] = dp[i-1][j-1] + 1`
        
        현재 char가 서로 같으면 직전에 같았던 문자열의 갯수에 + 1
        
    2. `A[i-1] != B[j-1]`일 때:
        
        `dp[i][j] = max(dp[i-1][j], dp[i][j-1])`
        
        같지 않으면 현재에서 직전, 직전에서 현재 중 더 큰 갯수
        
3. 초기화
    1. `dp[0][j]`와 `dp[i][0]`은 모두 `0`으로 초기화
4. 결과 반환
    1. `dp[len(A)][len(B)]`가 최종 LCS 길이

## 사용된 알고리즘

다이나믹 프로그래밍

---

# code

## Java

```java
package dp;

import java.io.*;

public class P9251 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String A = br.readLine();
        String B = br.readLine();

        int result = findLCSLength(A, B);
        System.out.println(result);

    }

    private static int findLCSLength(String A, String B) {
        int lenA = A.length();
        int lenB = B.length();

        int[][] dp = new int[lenA + 1][lenB + 1];

        for (int i = 1; i <= lenA; i++) {
            for (int j = 1; j <= lenB; j++) {
                if (A.charAt(i - 1) == B.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }

        return dp[lenA][lenB];
    }
}

```

## 해결한 오류

### 1. dp 2차원 배열의 역할

이전까지 최대 수열 길이를 저장하여 현재 char와 비교하고 최대 수열의 수를 저장
![image.png](attachment:8e164d92-4042-407d-85d2-4624ebb7f546:image.png)
