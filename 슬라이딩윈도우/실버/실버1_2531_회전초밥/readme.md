page link : [https://www.acmicpc.net/problem/2531](https://www.acmicpc.net/problem/2531)

---

# 💡 풀이전략

- 구하고자 하는 값
    - 연속해서 먹을 수 있는 초밥의 최대 가지수
- 풀이전략
    1. 접시 수 N, 초밥 가지수 d, 연속 접시 수 k, 쿠폰번호 c
    2. 초밥 접시 정보를 배열에 저장하고, 회전밸트 `(i + k -1) % N` 으로 처리해 원형 구현
    3. Deque로 현재 k개 연속된 초밥 유지
    4. Map으로 중복 없이 초밥 종류 수 O(1)로 관리
    5. 슬라이딩 윈도우로 앞에서 초밥을 제거하고 뒤에서 초밥을 추가하며 갱신
    6. 쿠폰 초밥이 윈도우에 없으면 종류 수 + 1로 계산하여 최대값 갱신

## 🎨 사용된 알고리즘

브루트포스, 슬라이딩 윈도우, 투포인터

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {

    static int N, d, k, c;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속 먹을 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        Deque<Integer> window = new ArrayDeque<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        int max = 0;

        for (int i = 0; i < k; i++) {
            window.offerLast(sushi[i]);
            countMap.put(sushi[i], countMap.getOrDefault(sushi[i], 0) + 1);
        }

        max = countMap.containsKey(c) ? countMap.size() : countMap.size() + 1;

        for (int i = 1; i < N; i++) {
            int out = window.poll();
            countMap.put(out, countMap.get(out) - 1);
            if (countMap.get(out) == 0) {
                countMap.remove(out);
            }

            int in = sushi[(i + k - 1) % N];
            window.offerLast(in);
            countMap.put(in, countMap.getOrDefault(in, 0) + 1);

            int current = countMap.containsKey(c) ? countMap.size() : countMap.size() + 1;
            max = Math.max(max, current);
        }

        System.out.println(max);
    }
}

```

# 🪄 해결한 오류

## 1. 슬라이딩 윈도우 구조 개선

### 🔥 문제

기존 코드는 윈도우를 `LIst<Node>` 로 구성한 뒤

- 윈도우 슬라이드 시 `remove()` + `add()` 후
- `distinct.clear()`하고 다시 `for` 루프를 돌며 종류 수 재계산

이 방식은

- 매 슬라이드마다 `O(k)`의 연산이 필요하고
- 원형 구조를 `Node.next`로 구현하면서 코드가 복잡해짐
- `distinct.add(c)`를 반복해도 항상 쿠폰 초밥이 들어간다는 보장이 없음.

**기존코드**

```java
for (int i = k; i < N; i++) {
    test.remove(test.size() - k);
    test.add(graph.get(test.get(test.size() - 1).next));

    distinct.clear();
    for (int j = 0; j < k; j++) {
        distinct.add(test.get(j).number);
    }
    distinct.add(c);
    maxNumber = Math.max(distinct.size(), maxNumber);
}
```

### 🧯 해결

Deque를 사용해 윈도우를 한칸씩 밀고,

Map을 통해 초밥 번호별 개수 관리하여 중복 제거된 종류 수를 O(1)에 계산

쿠폰 초밥도 한 줄 조건문으로 직관적 처리 가능

**수정된 코드**

```java
int out = window.poll();
countMap.put(out, countMap.get(out) - 1);
if (countMap.get(out) == 0) {
    countMap.remove(out);
}

int in = sushi[(i + k - 1) % N];
window.offerLast(in);
countMap.put(in, countMap.getOrDefault(in, 0) + 1);

int current = countMap.containsKey(c) ? countMap.size() : countMap.size() + 1;
max = Math.max(max, current);
```

## 2. 중복 카운팅 처리 개선

### 🔥 문제

기존코드는 초밥 번호 중복 여부를 판단하기 위해

`Set<Integer> distinct`를 사용해서 전체 윈도우를 순회하며 초밥 번호를 일일이 넣음 → `O(k)`

**기존코드**

```java
distinct.clear();
for (int j = 0; j < k; j++) {
    distinct.add(test.get(j).number);
}
distinct.add(c);
```

위 코드는 연속된 초밥 중 중복된 초밥이 많을수록 비효율적임

쿠폰 초밥도 이미 있는지 여부를 체크하지 않음(그냥 넣음)

### 🧯 해결

초밥 번호별 등장 횟수를 `Map<Integer, Integer>`로 관리

- 추가 시 개수가 0 → 1 이면 종류 수 +1
- 제거 시 개수가 1 → 0 이면 종류 수 -1
    
    → 중복여부도 자동관리 됨.
    

→ 결과적으로 Set.clear(), 루프 없이 초밥 종류 수 추적 가능

## 3. 자료구조 단순화 및 가독성 개선

### 🔥 문제

기존코드는 Node 클래스를 이용해 next 인덱스를 직접 연결해가며 원형 리스트를 구현

- graph와 test 리스트 2개 사용
- next 추적도 불필요하게 복잡
- 목적에 비해 과도한 구조 사용

**기존코드**

```java
static class Node {
    int next;
    int number;

    Node(int next, int number) {
        this.next = next;
        this.number = number;
    }
}
```

### 🧯 해결

초밥을 단순한 int[] sushi 배열로 저장하고

- 원형 밸트는 `(i + k - 1) % N` 으로 처리해 훨씬 간결하게 원형 표현
- Deque + Map 조합으로 리스트 2개를 사용할 필요 없음.

## ✅ 최종 비교 요약

| **항목** | **기존 코드** | **수정된 코드** |
| --- | --- | --- |
| 윈도우 처리 | List + next 링크 | Deque (진짜 슬라이딩 윈도우) |
| 중복 관리 | Set에 전부 다시 넣기 (O(k)) | Map으로 개수 관리 (O(1)) |
| 쿠폰 처리 | 무조건 add(c)만 함 | containsKey(c)로 조건 분기 |
| 원형 처리 | Node의 next 수동 처리 | (i + k - 1) % N 으로 간결화 |
| 성능 | O(N × k) 가능성 있음 | ✅ O(N) 보장 |
| 가독성 | 복잡한 구조 | ✅ 단순하고 명확함 |

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static class Node {
            int next;
            int number;
    
            Node(int next, int number) {
                this.next = next;
                this.number = number;
            }
    
            @Override
            public String toString() {
                return "Node{next: " + next + ", number: " + number + "}";
            }
        }
    
        static int N, d, k, c, maxNumber;
        static List<Node> graph = new ArrayList<>();
        static List<Node> test = new ArrayList<>();
        static Set<Integer> distinct = new HashSet<>();
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            d = Integer.parseInt(st.nextToken());
            k = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
    
            for (int i = 0; i < N; i++) {
                if (i == N - 1) {
                    graph.add(new Node(0, Integer.parseInt(br.readLine())));
                    break;
                }
                graph.add(new Node(i + 1, Integer.parseInt(br.readLine())));
            }
    
            for (int i = 0; i < k; i++) {
                test.add(graph.get(i));
                distinct.add(test.get(i).number);
                distinct.add(c);
            }
    
            maxNumber = Math.max(distinct.size(), maxNumber);
    
            for (int i = k; i < N; i++) {
                test.remove(test.size() - k);
                test.add(graph.get(test.get(test.size() - 1).next));
    
                distinct.clear();
                for (int j = 0; j < k; j++) {
                    distinct.add(test.get(j).number);
                }
                distinct.add(c);
    
                maxNumber = Math.max(distinct.size(), maxNumber);
            }
    
            System.out.println(maxNumber);
        }
    }
    
    ```
