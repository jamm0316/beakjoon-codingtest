page link : [https://www.acmicpc.net/problem/11047](https://www.acmicpc.net/problem/11047)

---

# 💡 풀이전략

1. 아이디어
    1. 큰 가치의 동전부터 사용하면 동전의 갯수 최소화 가능
    2. 동전을 내림차순 정렬
    3. targetMoney가 현재 조회하는 동전의 가치보다 클 경우 해당 동전을 사용할 수 있을 만큼 사용하여 count에 누적
    4. 그 동전으로 나눈 나머지를 targetMoney로 최신화

## 🎨 사용된 알고리즘

> [!tip]
> greedy(그리디): 동전이 큰 것부터 갯수를 최적화하면서 최적해를 찾을 수 있음

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner input = new Scanner(System.in);

        int query = input.nextInt();
        int targetMoney = input.nextInt();
        List<Integer> coins = new ArrayList<>();

        for (int i = 0; i < query; i++) {
            coins.add(input.nextInt());
        }

        coins.sort(Comparator.reverseOrder());

        int count = 0;
        for (int i = 0; i < query; i++) {
            if (coins.get(i) <= targetMoney) {
                count += targetMoney / coins.get(i);
                targetMoney %= coins.get(i);
            }
        }
        System.out.println(count);
    }
}
```
