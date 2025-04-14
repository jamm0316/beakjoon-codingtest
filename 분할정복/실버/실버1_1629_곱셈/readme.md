page link : [https://www.acmicpc.net/problem/1629](https://www.acmicpc.net/problem/1629)

---

# 풀이전략
- 구하고자 하는 값
    - A ^ B % C

---

- 성공한 풀이전략
    1. 자료형 주의
        1. A, B, C는 최대 2,147,483,647 → int 범위 초과 가능성 있음
        2. 따라서 모든 연산은 long으로 처리
    2. 단순 반복 곱셈은 오버플로우 발생
        1. A^B는 최대 A^2,147, 483, 647 → long으로 감당 불가
        2. 단순 for문은 시간 초과 + 오버플로우
    3. 분할정복 거듭 제곱 활용(재귀 방식)
        1. 핵심 아이이디어:
            1. A^B는 다음과 같이 나눌 수 있다.
                - B가 짝수일 때:  $A^B = (A^{\left({B}/{2}\right)})^2$
                - B가 홀수일 때:  $A^B = (A^{\left({B}-1/{2}\right)})^2*A$
        2. 이때 매 연산 후 mod C를 적용해서 값이 커지지 않도록 관리
    4. 시간 복잡도
        1. O(log B)
        2. 반으로 나누며 재귀 호출되기 떄문

---

- 실패한 풀이전략(overflow)
    1. 주어지는 값이 모두 2,147,483,647로 int타입으로 받을 수 있는 max 수준이다.
    2. 따라서 결과값은 원시 타입 중 가장 큰 값을 저장할 수 있는 long타입으로 저장하고 반환하자.

## 사용된 알고리즘
분할정복

---

## pseudo code

```python
1. 함수 pow(a, b)정의
2. 기저조건
	- b == 1 -> return a % c
3. 재귀적으로 분할
	- half = pow(a, b/2)
	- res = (half * half) % c
	- b가 홀수이면 res = (res * a) % c 추가 곱셈
4. 최종적으로 res 리턴
```

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static long A, B, C;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        A = Integer.parseInt(st.nextToken());
        B = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        System.out.println(pow(A, B));
    }

    static long pow(long a, long b) {
        if (b == 1) return a % C;

        long half = pow(a, b / 2);
        long result = (half * half) % C;

        if (b % 2 == 1) result = (result * a) % C;

        return result;
    }
}

```

## 해결한 오류

### 1. 오버플로우 발생

- 문제
    - 기존 코드에서 `result *= A;` 를 반복하며 A^B를 계산했으나, B의 경우 long 범위를 초과하여 오버플로우 발생
- **기존코드**
    
    ```java
    long result = 1;
            for (int i = 0; i < B; i++) {
                result *= A;  //long 범위 초과
            }
            System.out.println(result % C);
    ```
    
- 원인 분석
    - `A = 10`, `B = 11` 정도는 괜찮겠지만, 문제에서 제시한 최대 범위 (최대 2,147,483, 647)를 넣으면 `result`값이 `long`으로도 감당 불가능
    - 오버플로우가 발생하면 `result`에 엉뚱한 값이 저장되어 `result % C` 도 무의미해짐

### 2. 시간초과

- 문제:
    - for 반복문은 최대 B번 수행 → B가 수십억이면 시간 초과 발생
- 개선방법
    - `O(B)`의 시간복잡도를 가지는 반복문 대신, `O(log B)`로 줄일 수 있는 **분할 정복 알고리즘 사용**
    - 매 연산 후 `% C`를 적용해 계산한 값이 커지지 않도록 관리

### 3. 분할 정복 거듭제곱의 수학적 원리

### 🔎 공식을 쓰는 이유는?

1. 계산량을 줄이기 위해
2. A^B 전체를 직접 계산하지 않고, 절반만 계산해서 재활용하기 위해
3. 즉, O(B) → O(log B)로 최적화 하기위해

### 📌 핵심 아이디어: 지수 법칙

**💡 지수 법칙**

$A^m * A^n = A^{m+n}$

즉, 같은 밑 A에 대해서 지굿끼리는 더할 수 있다.

이걸 이용하면

### ✅ 짝수일 때(B가 짝수)

예: B = 8 이라면

$A^8 = A^4 + A^4 = (A^4)^2$

즉, 지수를 반으로 쪼개서 두 번 곱하면 원래 제곱이 된다.

**일반화**

$A^B = (A^{B/2})^2$

### ✅ 홀수일 때(B가 홀수)

예: B = 9라면,

$A^9 = A^4 * A^4 * A = (A^4)^2 * A$

즉, 짝수까지 계산한 다음, **A를 한 번 더 곱해주면 된다.**

**일반화**

$A^B = (A^{B/2})^2*A$

여기서, $B/2$ 는 정수 나눗셈이므로 $(B-1)/2$ 와 같음

### 📌 결론

| **이유** | **설명** |
| --- | --- |
| 지수법칙 | $A^m * A^n = A^{m + n}$ 이 성립하기 때문 |
| 거듭제곱의 성질 | 짝수일 때는 $A^B = (A^{B/2})^2$로 나눌 수 있음 |
| 홀수일 때 보정 | $A^{짝수}$ 까지만 계산한 후, 남은 A 하나만 곱해주면 됨 |

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());
            int C = Integer.parseInt(st.nextToken());
            long result = 1;
            for (int i = 0; i < B; i++) {
                result *= A;
            }
            System.out.println(result % C);
        }
    }
    
    ```
