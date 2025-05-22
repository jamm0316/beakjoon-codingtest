page link : [https://www.acmicpc.net/problem/30805](https://www.acmicpc.net/problem/30805)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 사전 순 가장 나중인 수열의 크기와 수열
- 핵심 아이디어
    
    모든 공통 부분 수열 중 사전순으로 가장 나중인 수열 찾기
    
    1. LCS 처럼 길이를 기준으로 판단하면 틀림
    2. 사전순 후순위르 ㄹ기준으로 탐색해야 함
    3. 따라서 사전순 기준으로 큰 수부터 시도하며, 공통 부분 수열이 가능한 경우에만 다음 단계로 이동하는 DFS + 백트래킹 방식으로 접근
- 풀이 방법
    1. 입력받고 배열선언
        1. A, B 수열을 입력 받고 dfs()를 위한 배열과 결과 변수 선언
    2. DFS로 공통 수열 탐색
        1. 현재 위치에서 1 ~ 100 중 사전순으로 큰 수부터 시도
        2. A와 B에 고옽으로 존재한다면 path에 추가하고, 다음 인덱스로 재귀 호출
    3. 조건 만족 시 즉시 종료
        1. 사전순으로 가장 큰 수부터 시도하므로, 가장 먼저 찾은 수열이 정답
        2. found 플래그를 두어 첫 수열을 찾은 순간 탐색 종료

## 🎨 사용된 알고리즘
백트래킹, DFS, 그리디

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] A, B;
    static int N, M;
    static List<Integer> result = new ArrayList<>();
    static boolean found = false;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        A = new int[N];
        for (int i = 0; i < N; i++) A[i] = sc.nextInt();
        M = sc.nextInt();
        B = new int[M];
        for (int i = 0; i < M; i++) B[i] = sc.nextInt();

        dfs(0, 0, new ArrayList<>());

        System.out.println(result.size());
        if (!result.isEmpty()) {
            for (int num : result) {
                System.out.print(num + " ");
            }
            System.out.println();
        }
    }

    static void dfs(int ai, int bi, List<Integer> path) {
        if (found) return;

        // 뒤에서부터 큰 수부터 붙이기
        for (int num = 100; num >= 1; num--) {
            int nextA = findNextIndex(A, ai, num);
            int nextB = findNextIndex(B, bi, num);

            if (nextA != -1 && nextB != -1) {
                List<Integer> nextPath = new ArrayList<>(path);
                nextPath.add(num);
                dfs(nextA + 1, nextB + 1, nextPath);
                if (found) return;
            }
        }

        // 공통 수열을 하나 찾았다면 저장
        if (!path.isEmpty() && !found) {
            result = path;
            found = true;
        }
    }

    static int findNextIndex(int[] arr, int start, int target) {
        for (int i = start; i < arr.length; i++) {
            if (arr[i] == target) return i;
        }
        return -1;
    }
}
```

# 🪄 해결한 오류

## 1. LCS 방식 정렬 방법 수정

LCS 정렬 방식을 길이 우선으로 하여 잘못된 답 도출, 사전순을 기준으로 탐색하는 방식으로 전환

**기존코드**

```java
static List<Integer> compare(List<Integer> a, List<Integer> b) {
    int len = Math.min(a.size(), b.size());
    for (int i = 0; i < len; i++) {
        if (!a.get(i).equals(b.get(i))) {
            return a.get(i) > b.get(i) ? a : b;
        }
    }
    return a.size() >= b.size() ? a : b;
}
```

**수정된 코드**

```java
static void dfs(int ai, int bi, List<Integer> path) {
    if (found) return;

    // 뒤에서부터 큰 수부터 붙이기
    for (int num = 100; num >= 1; num--) {
        int nextA = findNextIndex(A, ai, num);
        int nextB = findNextIndex(B, bi, num);

        if (nextA != -1 && nextB != -1) {
            List<Integer> nextPath = new ArrayList<>(path);
            nextPath.add(num);
            dfs(nextA + 1, nextB + 1, nextPath);
            if (found) return;
        }
    }

    // 공통 수열을 하나 찾았다면 저장
    if (!path.isEmpty() && !found) {
        result = path;
        found = true;
    }
}
```

---

- 기존코드
    
    ```java
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) {
            Scanner sc = new Scanner(System.in);
    
            int N = sc.nextInt();
            int[] A = new int[N];
            for (int i = 0; i < N; i++) A[i] = sc.nextInt();
    
            int M = sc.nextInt();
            int[] B = new int[M];
            for (int i = 0; i < M; i++) B[i] = sc.nextInt();
    
            List<Integer>[][] dp = new List[N + 1][M + 1];
    
            for (int i = 0; i <= N; i++) {
                for (int j = 0; j <= M; j++) {
                    dp[i][j] = new ArrayList<>();
                }
            }
    
            for (int i = 1; i <= N; i++) {
                for (int j = 1; j <= M; j++) {
                    if (A[i - 1] == B[j - 1]) {
                        dp[i][j] = new ArrayList<>(dp[i - 1][j - 1]);
                        dp[i][j].add(A[i - 1]);
                    } else {
                        dp[i][j] = compare(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
    
            List<Integer> result = dp[N][M];
            System.out.println(result.size());
            if (!result.isEmpty()) {
                for (int num : result) {
                    System.out.print(num + " ");
                }
                System.out.println();
            }
        }
    
        static List<Integer> compare(List<Integer> a, List<Integer> b) {
            int len = Math.min(a.size(), b.size());
            for (int i = 0; i < len; i++) {
                if (!a.get(i).equals(b.get(i))) {
                    return a.get(i) > b.get(i) ? a : b;
                }
            }
            return a.size() >= b.size() ? a : b;
        }
    }
    ```
