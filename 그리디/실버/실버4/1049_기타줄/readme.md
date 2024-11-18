page link : [https://www.acmicpc.net/problem/1049](https://www.acmicpc.net/problem/1049)

---

# 💡 풀이전략

1. 입력값을 받으면서 최소 패키지 가격, 최소 낱개 가격 최신화
2. 3가지 케이스 비교
    1. 풀패키지로 구매
    2. 패키지 + 낱개 구매
    3. 풀낱개 구매
3. 이 중 가장 작은 값 반환

## 🎨 사용된 알고리즘

> [!tip]
> greedy: 가장 싼 패키지 또는 낱개를 구매할 것이므로 입력값을 받아오면서 최소값을 최신화 하면 최적해를 구할 수 있다.

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static final double PACKAGE_NUM = 6;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int requiredString = Integer.parseInt(st.nextToken());
        int brandNum = Integer.parseInt(st.nextToken());

        int minPackagePrice = Integer.MAX_VALUE;
        int minIndividualPrice = Integer.MAX_VALUE;

        for (int i = 0; i < brandNum; i++) {
            st = new StringTokenizer(br.readLine());
            int packagePrice = Integer.parseInt(st.nextToken());
            int individualPrice = Integer.parseInt(st.nextToken());
            minPackagePrice = Math.min(minPackagePrice, packagePrice);
            minIndividualPrice = Math.min(minIndividualPrice, individualPrice);
        }

        int requiredFullPackage = (int) Math.ceil(requiredString / PACKAGE_NUM);
        int requiredEachPackage = (int) Math.floor(requiredString / PACKAGE_NUM);

        int costByFullPackage = minPackagePrice * requiredFullPackage;
        int costByPackageAndIndividual = minPackagePrice * requiredEachPackage +
                minIndividualPrice * (requiredString - requiredEachPackage * (int) PACKAGE_NUM);
        int costByIndividual = minIndividualPrice * requiredString;

        System.out.println(Math.min(costByFullPackage, Math.min(costByIndividual, costByPackageAndIndividual)));
    }
}
```
