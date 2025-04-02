page link : [https://www.acmicpc.net/problem/18870](https://www.acmicpc.net/problem/18870)

---

# 풀이전략

- 구하고자 하는 값
    - 각 배열의 값이 전체 배열에서 내림차순으로 정렬했을 때 몇번째인지 출력

---

1. 전체 배열을 받는다
2. 배열을 set으로 받는다.
3. set을 list로 전환시켜 sort한다
4. list를 map에 index를 value로 저장한다.
5. 원배열을 향상된 for문으로 순회하면서 map에서 index를 O(1)로 찾는다.
   
---

- 기존 풀이전략
    1. 전체 배열을 받는다.
        1. 이 때, 한번 받은 배열은 다시 받지 않는다.
        2. HashSet은 순서가 없으므로 정렬도 안된다. 따라서 ArrayList를 사용한다.
    2. 배열을 내림차순으로 정렬한다.
    3. 원 배열을 향상된 for문으로 돌면서 
    하나의 값이 정렬된 배열의 몇번째 index와 대응 되는지 출력한다.
    
    **→ 위 전략으로는 for문을 2번 돌기 때문에 시간초과**
    
    그렇다면 map을 이용해서 for문을 1번 돌고 O(1)으로 접근해야할 것같다.
    
    **→ 이것도 시간초과**
    
    **이유는 List.contains()에 있었음**

## 사용된 알고리즘
자료구조, 정렬

---

# code

## Java

```java
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        //input 초기화
        int N = Integer.parseInt(br.readLine());
        int[] positions = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //Set 초기화
        Set<Integer> uniqueSet = Arrays.stream(positions).boxed().collect(Collectors.toSet());
        
        //List 초기화 및 정렬
        List<Integer> sortedList = new ArrayList<>(uniqueSet);
        sortedList.sort(Comparator.naturalOrder());

        //Map 초기화
        Map<Integer, Integer> compressMap = new HashMap<>();
        for (int i = 0; i < sortedList.size(); i++) {
            compressMap.put(sortedList.get(i), i);
        }

        //결과값 출력
        for (int eachPosition : positions) {
            sb.append(compressMap.get(eachPosition) + " ");
        }
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}

```

## 해결한 오류

### ArrayList.contains()의 비효율

### ArrayList.contains는 중복체크를 하는데 O(n)의 비용이 든다.

javaAPI의 `ArraysList.contains()`의 코드를 보면 들어오는 object의 index를 **해당 배열을 순회**하며 찾고, 없으면 `-1`을 있으면 해당 인덱스를 반환한다.

이에따라 `0>=` 일 때 `ture`를 반환하는 방법으로 해당 object를 포함하고 있는지 확인한다.

즉 for문을 사용하여 배열의 요소들이 하나하나 중복되는지 확인하는 방법은 `O(n^2)`의 시간복잡도를 초래하므로 매우 비효율 적이다.

**javaAPI 중 ArrayList.contains()의 코드**

```java
public boolean contains(Object o) {
        return indexOf(o) >= 0;
}

public int indexOf(Object o) {
        return indexOfRange(o, 0, size);
}

int indexOfRange(Object o, int start, int end) {
    Object[] es = elementData;
    if (o == null) {
        for (int i = start; i < end; i++) {
            if (es[i] == null) {
                return i;
            }
        }
    } else {
        for (int i = start; i < end; i++) {
            if (o.equals(es[i])) {
                return i;
            }
        }
    }
    return -1;
}
```

### HashSet.add()는 중복체크를 하는데 O(1)의 비용이 든다.

그러나 HashSet의 내부를 살펴보면 HashMap으로 구성되어있고,

- 값은 Map의 key로
- value는 내부에서 그냥 PRESENT라는 아무 의미없는 객체(new Object())로 채워둔다.

따라서, HashSet은 중복을 허용하지 않고,

HashSet.add() API를 보면 map.put()이 호출되는 것을 볼 수있다.

**javaAPI 중 HashSet.add()의 코드**

```java
public boolean add(E e) {
    return map.put(e, PRESENT)==null;
}
```

이 것이 의미하는 것은

- e가 **Set에 없었다면** → map.put()은 null 반환 → add()는 true 반환
- e가 **이미 존재했다면** → 기존 PRESENT 덮어쓰기 → add()는 false 반환

즉, HashSet은 내부적으로 **HashMap을 이용해 중복 여부를 판단**하고 있음을 알수 있고, 이는 O(1)이다.
