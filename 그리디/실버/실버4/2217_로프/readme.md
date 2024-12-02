page link : [https://www.acmicpc.net/problem/2217](https://www.acmicpc.net/problem/2217)

# 💡 풀이전략

- 로프의 갯수만큼 정렬
- 각 로프를 순회
    - 최소로프 * 남은 로프 갯수를 MaxWeight로 선언
    - MaxWeight 최신화
    - 마지막에 갑자기 성능이 뛰어난 로프가 올 수 있으니 모든 로프 탐색
    - 탐색이 끝나면 MaxWeight 반환

## 🎨 사용된 알고리즘

> [!tip]
> algoritm
> greedy(그리디)

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int totalRopes = Integer.parseInt(br.readLine());
        List<Integer> ropes = new ArrayList<>();
        int maxWeight = Integer.MIN_VALUE;

        for (int i = 0; i < totalRopes; i++) {
            ropes.add(Integer.parseInt(br.readLine()));
        }

        ropes.sort(Comparator.naturalOrder());

        for (int i = 0; i < totalRopes; i++) {
            int currWeight = ropes.get(i) * (totalRopes - i);
            if (maxWeight < currWeight) {
                maxWeight = Math.max(maxWeight, currWeight);
            }
        }
        System.out.println(maxWeight);
    }
}
```

## 해결한 오류

### 1. 한줄이여도 BufferedReader로 읽자.

1. Scanner의 내부 로직의 비효율성
    1. **정규표현식 사용**: 입력을 파싱하는데 내부적으로 
    2. **즉석에서 데이터를 파싱**: 입력을 읽으면서 변환하므로 추가적인 계산 작업 발생.
    3. **부수 작업들이 많음**: 단순히 값을 읽어오는 것 이외에 변수를 다시 세팅하고, 캐시 초기화, 예외 처리 등의 작업 반복수행
        
        **Scanner.java**
        
        ```java
        public int nextInt(int radix) {
            // Check cached result
            if ((typeCache != null) && (typeCache instanceof Integer)
                && this.radix == radix) {
                int val = ((Integer)typeCache).intValue();
                useTypeCache();
                return val;
            }
            setRadix(radix);
            clearCaches();
            // Search for next int
            try {
                String s = next(integerPattern());
                if (matcher.group(SIMPLE_GROUP_INDEX) == null)
                    s = processIntegerToken(s);
                return Integer.parseInt(s, radix);
            } catch (NumberFormatException nfe) {
                position = matcher.start(); // don't skip bad token
                throw new InputMismatchException(nfe.getMessage());
            }
        }
        ```
        
2. BufferedReader의 로직은 입력받는 것에 집중
    1. **버퍼링된 입력**: 버퍼에 저장 후 한번에 읽어오므로 I/O횟수 감소
    2. **파싱과 변환 분리**: 단순히 문자열만 읽어오므로 필요한 경우 파싱은 따로해야함.
    3. **부수작업 없음**: 입력 데이터를 단순히 읽고 반환하는 데 집중하여 추가적인 연산이 없음.

---
