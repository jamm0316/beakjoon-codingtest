page link : [https://www.acmicpc.net/problem/30804](https://www.acmicpc.net/problem/30804)

---

# 풀이전략
- 구하고자 하는 것
    - 2종류 이하로 구성할 때 가장 긴 배열의 길이

---

- 완전탐색(brute-force)접근 보다 슬라이딩 윈도우(투 포인터) 접근이 빠르다.
- 핵심 아이디어
    - 어떤 연속된 구간 [start, end]이 있을 때, 이 구간에 두 종류 이하의 과일만 있다면 그 길이를 갱신
    - 이때, **end를 오른쪽으로 확장하면서 고정해두고**, 조건을 만족하지 않으면 **start를 증가시켜서** 조건을 다시 만족하는 구간으로 조정한다.
        - 이때 map.size()가 2개 이상인 경우에만 제거, 투포인터 이동 작업을 실시한다.

---

- 기존 풀이전략(시간초과)
    1. deque를 사용한다.
    2. 가장 긴 배열을 생성하려면?
        1. 방법 1.
            1. 탕후루 하나를 뺼 때마다 배열을 검증하고, 배열의 길이를 측정한다.
            2. 탕후루의 종류와 갯수를 map으로 관리하고 뺼때는 제일 앞이라 뒤에 탕후르 종류를 map에서 수량비교를 통해 더 적은 것은 뺀다.
            3. 탕후루 갯수만큼 이 작업을 반복한다.

## 사용된 알고리즘
완전탐색, 투 포인터

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<Integer, Integer> tanghuruMap = new HashMap<>();  //key: 종류, value: 갯수
        int N = Integer.parseInt(br.readLine());
        int[] tanghuru = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0;
        int maxLength = 0;
        for (int right = 0; right < N; right++) {
            tanghuruMap.put(tanghuru[right], tanghuruMap.getOrDefault(tanghuru[right], 0) + 1);

            while (tanghuruMap.size() > 2) {
                tanghuruMap.put(tanghuru[left], tanghuruMap.getOrDefault(tanghuru[left], 0) - 1);
                if (tanghuruMap.get(tanghuru[left]) == 0) {
                    tanghuruMap.remove(tanghuru[left]);
                }
                left++;
            }
            maxLength = Math.max(maxLength, right - left + 1);
        }

        System.out.println(maxLength);
    }
}

```

## 해결한 오류

### 1. HashSet<>()의 생성자 초기화 할 때 무슨 일이 일어나는가?

기존 코드에서 checkDeque()를 통해 기존의 `Deque` 를 `HashSet`에 넣어 그 `size`가 `2`를 넘는지 확인하는 로직이 있었다.

이 로직은 외부 루프에서 for문을 이용해 N번 순회하고, 내부 루프에서 `Set<>(Deque)` 를 호출하게 된다.

**기존코드**

```java
public static void main(String[] args) throws IOException {
		...
		for (int i = 0; i < N; i++) {
		    if (checkDeque(tanghuruDeque)) {
		        break;
		    }
		...
		}
...
}

private static boolean checkDeque(Deque<Integer> tanghuruDeque) {
    Set<Integer> tanghuruSet = new HashSet<>(tanghuruDeque);
    return tanghuruSet.size() <= 2;
}
```

여기서 HashSet의 초기화 코드를 더 자세히 보면, `addAll();` 에서 `for`문으로 들어오는 `Collection`을 순회하며 하나씩 `add`하는 것을 볼 수 있다. 따라서 시간복잡도가 여기서 `O(N)`이 되는 것!

**HashSet.java**

```java
public HashSet(Collection<? extends E> c) {
    map = HashMap.newHashMap(Math.max(c.size(), 12));
    addAll(c);
}

public boolean addAll(Collection<? extends E> c) {
    boolean modified = false;
    **for (E e : c)**
        if (add(e))
            modified = true;
    return modified;
}
```

따라서 기존 코드의 경우

`for` 문 안에서 `Set`을 생성하고, 이 `Set`안에서 다시한번 `for`문이 돌기 때문에 시간 복잡도가 `O(N^2)`이 되어 시간 초과가 난다.

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            Map<Integer, Integer> tanghuruMap = new HashMap<>();  //key: 종류, value: 갯수
            Deque<Integer> tanghuruDeque = new LinkedList<>();
            int N = Integer.parseInt(br.readLine());
    
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int fruit = Integer.parseInt(st.nextToken());
                tanghuruDeque.offer(fruit);
                tanghuruMap.put(fruit, tanghuruMap.getOrDefault(fruit, 0) + 1);
            }
    
            for (int i = 0; i < N; i++) {
                if (checkDeque(tanghuruDeque)) {
                    break;
                }
                int firstNum = tanghuruMap.get(tanghuruDeque.getFirst());
                int lastNum = tanghuruMap.get(tanghuruDeque.getLast());
                if (firstNum > lastNum) {
                    tanghuruDeque.poll();
                } else {
                    tanghuruDeque.pop();
                }
            }
    
            System.out.println(tanghuruDeque.size());
        }
    
        private static boolean checkDeque(Deque<Integer> tanghuruDeque) {
            Set<Integer> tanghuruSet = new HashSet<>(tanghuruDeque);
            return tanghuruSet.size() <= 2;
        }
    }
    
    ```
