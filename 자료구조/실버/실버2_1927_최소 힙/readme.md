page link : https://www.acmicpc.net/problem/1927

---

# 풀이전략

1. PriorityQueue를 선언한다
    1. 여기서, `new PirorityQueue<>()` 는 작은 수 부터
    2. `new PriorityQueue<>(Collections.reverseOrder());` 는 큰 수 부터 나온다.
2. queue에 offer 후 sort한다.
3. 0을 만나면 queue.poll()한다.

## 사용된 알고리즘

우선순위큐(PriorityQueue)

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> queue = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (queue.isEmpty()) {
                    bw.write(String.valueOf(0));
                    bw.newLine();
                } else {
                    bw.write(String.valueOf(queue.poll()));
                    bw.newLine();
                }
            } else {
                queue.offer(command);
            }
        }
        bw.close();
        br.close();
    }
}
```

## 해결한 오류

### 1. PriorityQueue 사용법(우선순위 큐)

### **기본개념**

개념부터 짚고 넘어가자.

`Queue`는 `interface`다.

`PriorityQueue`는 `implement`다.

**JavaAPI**

```java
/** PriorityQueue API */
public class PriorityQueue<E> extends **AbstractQueue**<E>
    implements java.io.Serializable {

/** AbstractQueue API */
public abstract class AbstractQueue<E>
    extends AbstractCollection<E>
    implements **Queue**<E> {

/** Queue API */
public interface Queue<E> extends Collection<E> {
```

### **Interface 사용이유**

따라서 Queue를 선언할 때, 아래와 같이 주로 쓰는 이유는 interface를 사용하면서 구현체를 생성하여 코드에 유연성을 주기 위함이다.

유연성이란 코드 유지보수가 쉽게 하기 위함이다.

```java
Queue<Integer> queue = new LinkedList<>();
```

이런 경우 유지보수 측면에서 무엇이 좋은가?

일반적인 큐는 FIFO로 선입선출이 기본 개념이다.

그런데 만약 queue를 sort하고 싶다면 어떻게 해야할까?

PriorityQueue가 필요하다.

### PriorityQueue 사용이유

LinkedList에는 sort가 있지만 변수를 Queue로 선언했으므로 Queue에는 sort 메서드가 없다.

따라서, `new LinkedList<>()`를 `new PriorityQueue<>()` 로만 바꿔주면 해결된다.

`PriortyQueue`의 경우`Comparable Interface`를 구현해야하는데, 아무것도 구현하지 않으면 오름차순 정렬이 기본이다.
(낮은 수가 먼저 `poll`되는 구조)

따라서 내림차순 정렬으로 바꾸려면 아래와 같이 선언해주면 된다.

```java
Queue<Intger> queue = new PriorityQueue<>(Comparator.reverseOrder());
```

현재 제내릭 타입을 `Integer`로 명시해주었기 때문에 **`Comparator.reverseOrder()`**가 안전한 선택이다. `String`의 경우에도 알파벳의 역순으로 출력되므로 안전하게 사용할 수 있다.

만약 특정 조건에 맞게 `sort`하고 싶다면, 람다식으로 `Comparable Interface`를 구현하는 방법도 있다.

### **PriorityQueue의 경우 이진트리 구조 및 sort방법**

PriorityQueue의 경우 이진트리로 구성되어 있으며, 새로운 값이 들어오면 일단 마지막에 넣고, 부모와 비교하여 값이 더 작을 경우 부모와 교체하는 형식으로 정렬이 일어난다.

- 참고사진
    
    ![image.png](attachment:f5eb7382-84a3-4241-80e0-8cb94eb43f9a:image.png)
    

이진트리 내부 로직을 보면 아래와 같이 선언되어있다.

```java
private void siftUp(int k, E x) {
    if (comparator != null)
        siftUpUsingComparator(k, x, queue, comparator);  //사용자 지정 Comparator
    else
        siftUpComparable(k, x, queue);  //기본 Comparator
}

//siftUpComparable 구현 로직
private static <T> void siftUpComparable(int k, T x, Object[] es) {
    Comparable<? super T> key = (Comparable<? super T>) x;
    while (k > 0) {
        int parent = (k - 1) >>> 1;
        Object e = es[parent];
        if (key.compareTo((T) e) >= 0)
            break;
        es[k] = e;
        k = parent;
    }
    es[k] = key;
}
```

siftUpComparable 로직 요약

| 부분 | 의미 |
| --- | --- |
| `while (k > 0)` | 루트 노드까지 반복 |
| `parent = (k - 1) >>> 1` | 부모 인덱스 계산 |
| `key.compareTo(e)` | 새 요소와 부모 비교 |
| `break` | 조건 만족하면 멈춤 |
| `es[k] = e` | 부모를 자식 위치로 내림 |
| `es[k] = key` | 새 요소를 알맞은 위치에 삽입 |

### **PriorityQueue의 기본 메서드**

- `add()` : 우선순위 큐에 원소를 추가. 큐가 꽉 찬 경우 에러 발생
- `offer()` : 우선순위 큐에 원소를 추가. 값 추가 실패 시 false를 반환
- `poll()` : 우선순위 큐에서 첫 번째 값을 반환하고 제거, 비어있으면 null 반환
- `remove()` : 우선순위 큐에서 첫 번째 값을 반환하고 제거, 비어있으면 에러 발생
- `isEmpty()` : 우선순위 큐에서 첫 번째 값을 반환하고 제거, 비어있으면 에러 발생
- `clear()` : 우선순위 큐를 초기화
- `size()` : 우선순위 큐에 포함되어 있는 원소의 수를 반환
