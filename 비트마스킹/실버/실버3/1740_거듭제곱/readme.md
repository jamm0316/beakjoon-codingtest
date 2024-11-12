page link : [https://www.acmicpc.net/problem/1740](https://www.acmicpc.net/problem/1740)

---

# 💡 풀이전략

1. 이진수와 3의 제곱수 관계
    - 각 자연수 N을 2진수로 표현
    - 기 2진수의 각 비트를 3진수의 각 자리수로 해석
    - 예를들어 N=4일 때 2진수는 100, 3진수로 해석한다면 9
2. N을 2진수로 변환 후, 각 비트를 3의 제곱수로 변환
    - N을 2진수로 변환 후, 각 비트가 1인 위치의 3의 제곱수 합산
    - 즉, N번째로 작은 수 구하는 방법과 동일

## 🎨 사용된 알고리즘

> [!tip]
> bitmasking

---

# code

## Java

```java
package bitmask;

import java.io.*;

public class P1740 {
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        long N = Long.parseLong((br.readLine().trim()));
        
        long result = 0;
        long power = 1;

        while (N > 0) {
            if ((N & 1) == 1) {
                result += power;
            }

            power *= 3;

            N >>= 1;
        }
        System.out.println(result);
    } 
}
```

## 해결한 오류

### 1. Long형태로 받은 이유

주어진 조건에서 N의 최대값을 `500,000,000,000` 이하의 자연수로 제시.

`int`의 최대 값은 `2,147,483,648`이므로 `int`형태로는 최대값을 초과.

따라서, `9,223,372,036,854,775,807`까지 가능한 `long`변수를 사용.
