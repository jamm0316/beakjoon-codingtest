page link : [https://www.acmicpc.net/problem/1446](https://www.acmicpc.net/problem/14465)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 최소 연속된 신호등 수리 갯수
- 풀이전략
    1. index = 0 ~ N -K 까지 순회하면서 연속된 숫자 확인
    2. 해당 숫자 중 고장난 신호등 숫자가 가장 적은 숫자 최신화
    3. minCount 출력

## 🎨 사용된 알고리즘
슬라이딩 윈도우

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, K, B, minCount;
    static boolean[] arr;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        arr = new boolean[N + 1];

        for (int i = 1; i <= B; i++) {
            int each = Integer.parseInt(br.readLine());
            arr[each] = true;
        }
        int count = 0;
        // 1 2 3 4 5 6 7 8 9 10
        for (int i = 1; i <= K; i++) {
            if (arr[i]) {
                count++;
            }
        }
        minCount = count;

        for (int i = 1; i <= N - K; i++) {
            boolean left = arr[i];
            boolean right = arr[K + i];
            if (left) {
                count--;
            }
            if (right) {
                count++;
            }
            minCount = Math.min(count, minCount);
        }

        System.out.println(minCount);
    }
}
```
