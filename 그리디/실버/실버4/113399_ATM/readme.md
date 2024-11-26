page link : [https://www.acmicpc.net/problem/11399](https://www.acmicpc.net/problem/11399)

# 💡 풀이전략

- 돈 뽑을 사람의 수, 돈 뽑는데 걸리는 시간
- 누적합에 관한 문제로 오름차순 정렬이 될 때 최소값이 반환된다.
- 따라서 배열을 오름차순 정렬하고, total 변수를 선언한 후 누적합을 구한다.

## 🎨 사용된 알고리즘

> [!tip]
> greedy(그리디): 오름차순 정렬, 즉, 값이 작은 수를 앞에 배치하여 누적합을 할 때 누적으로 합해지는 값을 최소화하면 최적해에 쉽게 도달할 수 있다.

---

# code

## Java

```java
package greedy;

import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int person = Integer.parseInt(st.nextToken());
        int totalTime = 0;
        int prefixSum = 0;
        List<Integer> personList = new ArrayList<Integer>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < person; i++) {
            personList.add(Integer.parseInt(st.nextToken()));
        }

        personList.sort(Comparator.naturalOrder());

        for (Integer time : personList) {
            prefixSum += time;
            totalTime += prefixSum;
        }

        System.out.println(totalTime);

    }
}

```

## 해결한 오류

### 1. 다양한 sort방법

1. 람다식으로 표현
    
    `personList.sort((a, b) → a - b);`
    
    `personList.sort(a, b) → Integer.compare(a, b));`
    
2. 내장 함수 이용(권장)
    
    `personList.sort(Comparator.naturalOrder());`
    
3. Collections 이용
    
    `Collections.sort(personList);`
    

### 차이점 요약

| 코드 | 특징 | 안전성 | 가독성 | 사용 권장 상황 |
| --- | --- | --- | --- | --- |
| personList.sort((a, b) ->
 a - b); | 간단한 람다식 | 낮음 (오버플로우 위험) | 중간 | 숫자 정렬에서 간단히 사용할 때 |
| **personList.sort((a, b) -> Integer.compare(a, b));** | **안전한 비교 메서드 활용** | **높음** | **높음** | **숫자 정렬 (특화된 상황)** |
| **person.sort
(Comparator.naturalOrder());** | **안전한 비교 메서드 활용** | **높음** | **높음** | **기본 정렬 기준 사용 (범용적)** |
| Collections.sort(personList); | 기본 정렬 기준 사용 (`Comparable`) | 높음 | 높음 | 기본 정렬 기준이 명확한 경우 |

위를 통해 비교해 보았을 때 **안정성 측면**과 **가독성, 재상용성, 범용성 측면**에서 자바 내장 Comparator인 `personList.sort(Comparator.naturalOrder());` 를 사용하는 것이 적합해보인다.

물론 문제는 숫자만 비교하므로 `personList.sort(Integer::compare);` 도 적합한 코드라고 생각된다.

---
