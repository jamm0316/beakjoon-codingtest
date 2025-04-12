page link : [https://www.acmicpc.net/problem/9019](https://www.acmicpc.net/problem/9019)

---

# 풀이전략
- 구하고자 하는 값
    - testCase마다 2번째 값이 되기위해 DSLR 중 무엇을 해야하는지 최소한의 명령어

- 성공한 풀이전략
    1. visited[10000] 배열로 중복 방문 방지
    2. Queue<Node> 형태로 BFS 탐색
        1. class Node
            1. field: int value, String command
    3. DSLR 연산 4개를 각각 구현해서 다음 값으로 이동
    4. target에 도달하면 해당 명령어 문자열 출력

## 사용된 알고리즘
그래프 탐색, BFS

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    static class Node {
        int value;
        String command;

        Node(int value, String command) {
            this.value = value;
            this.command = command;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            Queue<Node> queue = new LinkedList<>();
            boolean[] visited = new boolean[10_000];
            StringTokenizer st = new StringTokenizer(br.readLine());

            int start = Integer.parseInt(st.nextToken());
            int target = Integer.parseInt(st.nextToken());
            queue.offer(new Node(start, ""));
            visited[start] = true;

            while (!queue.isEmpty()) {
                Node curNode = queue.poll();
                if (curNode.value == target) {
                    bw.write(curNode.command + "\n");
                    break;
                }

                int d = curNode.value * 2 % 10_000;
                if (!visited[d]) {
                    visited[d] = true;
                    queue.offer(new Node(d, curNode.command + "D"));
                }

                int s = curNode.value == 0 ? 9999 : curNode.value - 1;
                if (!visited[s]) {
                    visited[s] = true;
                    queue.offer(new Node(s, curNode.command + "S"));
                }

                int l = curNode.value % 1000 * 10 + curNode.value / 1000;
                if (!visited[l]) {
                    visited[l] = true;
                    queue.offer(new Node(l, curNode.command + "L"));
                }

                int r = curNode.value % 10 * 1000 + curNode.value / 10;
                if (!visited[r]) {
                    visited[r] = true;
                    queue.offer(new Node(r, curNode.command + "R"));
                }
            }
        }
        bw.close();
        br.close();
    }
}

```

## 해결한 오류

### 1. 와일드카드와 제네릭 타입 파라미터의 공통점과 차이점

### 제네릭 타입 파라미터`<T>` 와 와일드카드 `?` 의 차이점

- 공통점
    - 둘 다 **타입의 결정을 컴파일 시점이 아닌, 나중(사용 시점)으로 미룬다.**
    - 타입 안정성을 유지하면서 **유연한 코드 작성을 가능**하게 한다.
- 핵심 차이점
    
    > **제네릭 타입 파라미터**는
    “타입을 만들어내는 입장”(템플릿)
    → “**모든 타입을 열어두고, 사용하는 시점에 타입을 결정하겠다.”**
    > 
    
    > **와일드카드**는
    “타입을 받아들이는 입장”(제한된 유연한 수용)
    → “**타입을 직접 선언할 순 없지만, 특정 범위 안에서 유연하게 허용하겠다.”**
    > 
    

### 비교표

| **구분** | **제네릭 타입 파라미터** <T> | **와일드카드** ? |
| --- | --- | --- |
| 💡 핵심 역할 | **타입을 선언**하는 역할
(타입을 만드는 쪽) | **타입을 사용하는** 역할
(이미 만들어진 타입을 사용) |
| 사용 위치 | 클래스/메서드 정의부 | 변수, 파라미터 등 사용부 |
| 예시 | `class Box<T> / <T> void print(T t)` | `void printList(List<?> list)` |

### 문법 사용 위치 비교

- 제네릭 타입 파라미터는 함수의 매개변수를 선언하는 것과 같고,
- 와일드카드는 함수에 인자를 넘겨주는 것과 같음.
    
    ```java
    // 제네릭 타입 파라미터 선언 (선언부)
    <T> void doSomething(T item)
    
    // 와일드카드 사용 (사용부)
    void printList(List<?> list)
    ```
    

### 제네릭 타입 파라미터 표기관례

| **기호** | **의미** |
| --- | --- |
| E | Element (요소, 보통 컬렉션에 담기는 값들) |
| T | Type (일반적인 타입) |
| K | Key (맵의 키) |
| V | Value (맵의 값) |
| N | Number (숫자) |

### 와일드 카드 문법과 역할

| **문법** | **읽기** | **쓰기** | **설명** |
| --- | --- | --- | --- |
| List<?> | ✅ (Object) | ❌ | 타입 모를 때, 그냥 존재만 중요할 때 |
| List<? extends T> | ✅ (T로) | ❌ | 읽기 전용 (상한 제한) |
| List<? super T> | ❌ | ✅ (T만) | 쓰기 전용 (하한 제한) |

### 마무리 요약

- 제네릭 타입은 **타입을 선언하고 생성하는 템플릿**
- 와일드카드는 **이미 정해진 제네릭 타입을 유연하게 받아들이는 수단**
- 제네릭은 **재사용성**, 와일드카드는 **유연성**에 초점이 있다
