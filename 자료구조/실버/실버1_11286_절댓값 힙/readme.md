page link : [https://www.acmicpc.net/problem/11286](https://www.acmicpc.net/problem/11286)

---

# 풀이전략
- 구하고자 하는 값
    - 절대값 또는 수가 작은 순서대로 출력

---

1. 우선순위 큐를 선언한다.
    1. `Comparator`를 람다식으로 선언한다.
    2. `a, b`가 들어오면 절대값이 작은 것을
        1. 절대값이 같다면 수가 더 작은 것을 먼저 오게 한다.
2. `0`이 아니면 `queue.offer()`실행
3. `0`이면 `queue.poll()`실행
    1. `0`인데 `queue`가 비어있으면 `0`출력

## 사용된 알고리즘
우선순위 큐(Priority Queue)

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        Queue<Integer> queue = new PriorityQueue<>((a, b)->
            Math.abs(a) == Math.abs(b) ? a - b : Math.abs(a) - Math.abs(b)
        );

        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command != 0) {
                queue.add(command);
            } else {
                sb.append(queue.isEmpty() ? 0 : queue.poll()).append('\n');
            }
        }

        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
```

## 해결한 오류

### 1. 삼항 연산자는 return 값을 반환하는 표현식이기 때문에, void형은 사용할 수 없다.

- **기본형**
    - `조건 ? 값1 : 값2`
- `값1`, `값2`의 타입
    - 삼항연산자는 `값1`, `값2` 앞에 `return`이 생략되어있다.
    - 따라서, void 메서트 처럼 반환값이 없는 것은 사용할 수 없다.
    
    **코드예시**
    
    ```java
    for (int i = 0; i < N; i++) {
        int command = Integer.parseInt(br.readLine());
        if (command != 0) {
            queue.add(command);
        } else {
            //사용 가능 코드
            sb.append(queue.isEmpty() ? 0 : queue.poll()).append('\n');
            
            //사용 불가 코드 – 삼항 연산자는 값을 반환해야 하므로 실행문(statement) 형식은 사용할 수 없음
    queue.isEmpty() ? sb.append(0).append('\n') : sb.append(queue.poll()).append('\n');
        }
    }
    ```
    

### 2. StringBuilder.append()는 어떤 타입이 오더라도 자동으로 문자열로 변환된다.

1. **`StringBuilder`에 `원시타입`이나, `Object`가 들어와도 괜찮을까?**
    
    괜찮다.
    
    > `StringBuilder`는 `append()` 메서드를 다양한 타입에 대해 **오버로딩(overloading)** 해 두었기 떄문에, 어떤타입이 와도 자동으로 문자열로 변환되어 동작한다.
    > 
    
    **StringBuilder of javaAPI**
    
    ```java
    public StringBuilder append(int i)
    public StringBuilder append(long l)
    public StringBuilder append(float f)
    public StringBuilder append(double d)
    public StringBuilder append(boolean b)
    public StringBuilder append(char c)
    public StringBuilder append(char[] str)
    public StringBuilder append(String str)
    public StringBuilder append(Object obj)
    ```
    

1. **어떻게 자동으로 문자열로 변환될까?**
    1. 원시타입의 경우
        1. `append(int i)`나 `append(boolean b)` 처럼 해당 타입에 맞는 메서드 호출 됨.
            
            **타입별 로직(StringBuilder of javaAPI)**
            
            ```java
            @Override
            public StringBuilder append(boolean b) {
                super.append(b);
                return this;
            }
            
            @Override
            @IntrinsicCandidate
            public StringBuilder append(int i) {
                super.append(i);
                return this;
            }
            
            ...
            ```
            
    2. 객체타입의 경우
        1. `append(Object obj)`가 호출 됨
            
            **append(Object obj) 로직(StringBuilder of javaAPI)**
            
            ```java
            @Override
            public StringBuilder append(Object obj) {
                return append(String.valueOf(obj));
            }
            ```
            
            따라서, 컬렉션의 경우 내부적으로 `toString()` 이 호출되어, `“[a, b, c]”` 와 같이 `append` 된다.
            
2. **원시 타입의 경우 어떻게 문자열로 변환되는 걸까?**
    1. `AbstractStringBuilder`의 내부 `char[]`에 해당 타입의 값을 `char`형태로 넣은 뒤 `append`하는 방식으로 문자열로 변환.
        
        **int 타입을 문자열로 변환하는 로직(AbstractStringBuilder of javaAPI)**
        
        ```java
         public AbstractStringBuilder append(int i) {
            int count = this.count;
            int spaceNeeded = count + Integer.stringSize(i);  //char공간 체크
            ensureCapacityInternal(spaceNeeded);  //char[] 공간확보
        
        		//char단위로 변환해서 넣는 과정
            if (isLatin1()) {
                StringLatin1.getChars(i, spaceNeeded, value);
            } else {
                StringUTF16.getChars(i, count, spaceNeeded, value);
            }
            this.count = spaceNeeded;
            
            //AbstractStringBuilder 반환
            return this;
        }
        ```
        
    2. 직접 `char`를 사용해서 구현한 이유
        - 성능 최적화를 위해
            - 예를 들어 `Integer.toString(i)` 를 쓰면 ‘`char[] → String 생성 → 복사`’와 같은 과정을 거치는데 내부 로직으로 `char`를 구현하면 한 번의 복사로 끝나기 떄문
