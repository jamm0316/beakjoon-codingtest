page link : [https://www.acmicpc.net/problem/2501](https://www.acmicpc.net/problem/2501)

---

# 💡 풀이전략

- 구하고자하는 값
    - N의 약수 중 K번째 작은 수

---

1. N의 약수 조건식을 만든다.
    1. 1 ~ N까지 N % i = 0 이면 약수다.
2. count = 0 변수를 선언한다.
3. N의 약수를 찾으면 count += 1한다.
4. count == K 이면 약수를 반환한다.

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

# code

## Java

```java
package Brute_Force;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        int[] data = parseData();
        int N = data[0];
        int K = data[1];

        int minNumber = findMinNumber(N, K);
        int result = minNumber;
        System.out.println(result);
    }

    private static int findMinNumber(int n, int k) {
        int count = 1;
        for (int i = 1; i <= n; i++) {
            if (count == k) {
                return i;
            }

            if (n % i == 0) {
                count++;
            }
        }
        return 0;
    }

    private static int[] parseData() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputData = br.readLine();
        String[] tokens = inputData.split(" ");
        int[] number = Arrays.stream(tokens)
                .mapToInt(Integer::parseInt)
                .toArray();

        return number;
    }
}
```

## Python

```python
def find_min_number(N, K):
    count = 0
    for i in range(1, N + 1):
        if N % i == 0:
            count += 1
        if count == K:
            return i
    return 0

N, K = map(int, input().split())
print(find_min_number(N, K))

```
