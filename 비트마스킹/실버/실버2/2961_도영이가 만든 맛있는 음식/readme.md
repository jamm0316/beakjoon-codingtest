page link : [https://www.acmicpc.net/problem/2961](https://www.acmicpc.net/problem/2961)


---

# 💡 풀이전략

- 모든 조건을 탐색해야하므로 브루트 포스문제.
- 그러나, 부분집합에 대해 선택과 비선택의 조건을 줄 수 있으므로, 백트레킹 적용 가능.
- 백트레킹의 가지치기가 단순히 선택, 비선택이므로 비트마스킹 이용가능.

## 🎨 사용된 알고리즘

> [!tip]
> BackTracking
> Bitmask

---

# code

## Java_Bitmask

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N;
    static int[][] taste;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        taste = new int[N][2];

        //dataParsing
        for (int i = 0; i < N; i++) {
            String[] query = br.readLine().split(" ");
            int[] parsedQuery = Arrays.stream(query)
                    .mapToInt(s -> Integer.parseInt(s))
                    .toArray();
            taste[i][0] = parsedQuery[0];
            taste[i][1] = parsedQuery[1];
        }

        int minNum = Integer.MAX_VALUE;

        //bitMask
        for (int i = 1; i < (1 << N); i++) {
            int sour = 1;
            int bitter = 0;
            for (int j = 0; j < N; j++) {
                if ((i & (1 << j)) != 0) {
                    sour *= taste[j][0];
                    bitter += taste[j][1];
                }
            }
            minNum = Math.min(minNum, Math.abs(sour - bitter));

        }
        System.out.println(minNum);
    }
}
```

## Java_BackTracking

```java
import java.io.*;
import java.util.*;

public class Main {
    static int query;
    static int minNum = Integer.MAX_VALUE;
    static int[][] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        query = Integer.parseInt(br.readLine().trim());
        arr = new int[query][2];
        for (int i = 0; i < query; i++) {
            String[] taste = br.readLine().split(" ");
            arr[i][0] = Integer.parseInt(taste[0]);
            arr[i][1] = Integer.parseInt(taste[1]);
        }

        findMinDifference(0, 1, 0, 0); //index, 신맛, 쓴맛, 재료사용횟수

        System.out.println(minNum);
    }

    public static void findMinDifference(int idx, int sour, int bitter, int used) {
        if (idx == query) {
            if (used > 0) {
                int difference = Math.abs(sour - bitter);
                minNum = Math.min(minNum, difference);
            }
            return;
        }

        findMinDifference(idx + 1, sour * arr[idx][0], bitter + arr[idx][1], used + 1);

        findMinDifference(idx + 1, sour, bitter, used);
    }
}

```
