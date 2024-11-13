page link : [https://www.acmicpc.net/problem/16938](https://www.acmicpc.net/problem/16938)

# 💡 풀이전략

1. 입력값 이해
    
    N: 문제 개수, L: 최소난이도, R: 최대난이도, X: 최소 난이도차이
    
    N개의 문제의 각각의 난이도
    
2. 출력값 이해
    - 캠프에 사용할 문제를 고르는 방법의 수
3. 풀이전략
    1. 최소, 최대 범위 밖인 것은 제외

## 🎨 사용된 알고리즘

> [!tip]
> Bitmasking

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int minLevel;
    static int maxLevel;
    static int gapLevel;
    static int problems[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 입력값 받기
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        minLevel = Integer.parseInt(st.nextToken());
        maxLevel = Integer.parseInt(st.nextToken());
        gapLevel = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        problems = new int[N];
        for (int i = 0; i < N; i++) {
            problems[i] = Integer.parseInt(st.nextToken());
        }

        System.out.println(bitMaskSelectProblems());
    }

    static int bitMaskSelectProblems() {
        int count = 0;

        // 모든 가능한 문제 조합을 비트마스크로 탐색
        for (int i = 0; i < (1 << N); i++) {
            int sum = 0;
            int max = Integer.MIN_VALUE;
            int min = Integer.MAX_VALUE;
            int numProblems = 0;

            // 선택된 문제들에 대해 계산
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) { // j번째 문제 선택
                    sum += problems[j];
                    max = Math.max(max, problems[j]);
                    min = Math.min(min, problems[j]);
                    numProblems++;
                }
            }

            // 조건을 만족하는지 확인
            if (numProblems >= 2 && sum >= minLevel && sum <= maxLevel && (max - min) >= gapLevel) {
                count++;
            }
        }

        return count;
    }
}
```

## 해결한 오류

### 1. 비트마스킹을 이용한 브루트포스 해결법

(1) 비트마스킹이로 모든 경우의 수를 초기화하고

(2) 각 경우의 수에 대하여 필요한 계산을 수행 후

(3) 조건에 맞으면 count++을 진행

```jsx
// (1) 모든 가능한 문제 조합을 비트마스크로 탐색
for (int i = 0; i < (1 << N); i++) {
    int sum = 0;
    int max = Integer.MIN_VALUE;
    int min = Integer.MAX_VALUE;
    int numProblems = 0;

    // (2) 선택된 문제들에 대해 계산
    for (int j = 0; j < N; j++) {
        if ((i & (1 << j)) != 0) { // j번째 문제 선택
            sum += problems[j];
            max = Math.max(max, problems[j]);
            min = Math.min(min, problems[j]);
            numProblems++;
        }
    }

    // (3) 조건을 만족하는지 확인
    if (numProblems >= 2 && sum >= minLevel && sum <= maxLevel && (max - min) >= gapLevel) {
        count++;
    }
}
```
