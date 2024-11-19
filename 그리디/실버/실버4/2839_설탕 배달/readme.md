page link : [https://www.acmicpc.net/problem/11399](https://www.acmicpc.net/problem/11399)

---

# 💡 풀이전략

N이 5의 배수가 되기 전까지 3을 뺀다.

N이 5가 되고나면, N / 3을 count에 더해서 최적해를 구한다.

## 🎨 사용된 알고리즘

> [!tip]
> greedy: 각 세션마다 최적해를 구하는 방법을 통해, 결과를 도출할 수 있다.

---

# code

## Java

```java
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int N = input.nextInt();
        int count = 0;
        while (N >= 0) {
            if (N % 5 == 0) {
                count += N / 5;
                System.out.println(count);
                return;
            }
            N -= 3;
            count++;
        }
        System.out.println(-1);
    }
}
```
