page link : https://www.acmicpc.net/problem/9935

---

# 💡 풀이전략
- 구하고자 하는 값
    - 폭발 문자열 이후 남은 문자열 출력

- 풀이전략 - StringBuilder만 사용(메모리, 속도 절약)
    1. StringBuilder를 통해 문자열을 순회한다
    2. 출발 문자열과 일치하는지 검사할 때는 마지막 몇 글자만 검사한다.
- 풀이전략 - Stack 사용(직관적)
    1. input의 길이를 순회하며 Stack에 input값을 하나씩 넣는다.
    2. stack.size() ≥ explosion.lenth()라면 폭발문자가 있는지 검증한다.
        1. stack.get() 메서드를 이용하여 마지막에서 explosion.length()만큼 뺀 idx의 문자열부터 폭발문자 길이만큼 순회하며, 폭발문자 일치 여부를 확인한다.
        2. 일치하면 폭발문자 길이만큼 순회하며 statck.pop()으로 제거해준다.

## 🎨 사용된 알고리즘
자료구조, 스택(Stack)

---

# 🧑🏻‍💻 code

## Java_StringBuilder만 사용

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //input String 값, 폭발 문자열 받기
        String input = br.readLine(), explosion = br.readLine();;

        for (int i = 0; i < input.length(); i++) {
            sb.append(input.charAt(i));

            if (sb.length() >= explosion.length()) {
                boolean match = true;
                for (int j = 0; j < explosion.length(); j++) {
                    if (sb.charAt(sb.length() - explosion.length() + j) != explosion.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    sb.delete(sb.length() - explosion.length(), sb.length());
                }
            }
        }

        System.out.println(sb.length() == 0 ? "FRULA" : sb.toString());
    }
}

```

## Java_Stack 사용

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        Stack<Character> stack = new Stack<>();

        String input = br.readLine(), explosion = br.readLine();

        for (int i = 0; i < input.length(); i++) {
            stack.push(input.charAt(i));

            if (stack.size() >= explosion.length()) {
                boolean match = true;
                for (int j = 0; j < explosion.length(); j++) {
                    if (stack.get(stack.size() - explosion.length() + j) != explosion.charAt(j)) {
                        match = false;
                        break;
                    }
                }

                if (match) {
                    for (int j = 0; j < explosion.length(); j++) {
                        stack.pop();
                    }
                }
            }
        }
        if (stack.isEmpty()) {
            System.out.println("FRULA");
        } else {
            for (char c : stack) {
                sb.append(c);
            }
            System.out.println(sb.toString());
        }
    }
}

```

# 🪄 해결한 오류

## 1. StringBuilder에서 charAt(), delete() 메서드 사용법

StringBuilder의 경우 시작 index와 종료 index를 param으로 받아 해당 범위 내의 문자열을 제거하는 delete 메소드가 존재한다.

### **사용법**

`StringBuilder.delete(int startIdx, int endIdx)`

여기서,
`int startIdx`: `시작 index`

`int endIdx`: `끝 index`

### API 구조

**StringBuilder**

```java
@Override
public StringBuilder delete(int start, int end) {
    super.delete(start, end);
    return this;
}
```

**AbstractStringBuilder**

```java
public AbstractStringBuilder delete(int start, int end) {
        int count = this.count;
        if (end > count) {
            end = count;
        }
        Preconditions.checkFromToIndex(start, end, count, Preconditions.SIOOBE_FORMATTER);
        int len = end - start;
        if (len > 0) {
            shift(end, -len);
            this.count = count - len;
            maybeLatin1 = true;
        }
        return this;
    }
```
