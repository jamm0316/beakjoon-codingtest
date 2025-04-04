page link : [https://www.acmicpc.net/problem/5525](https://www.acmicpc.net/problem/5525)

---

# 풀이전략
- 구하고자 하는 것
    - S에 포함된 $P_N$의 갯수

---

- **성공한 풀이 전략**
    1. `IOIOI` 패턴은 `I`로 시작하고 `OI` 가 `N`번 반복되는 구조
    2. 문자열을 한 번 순회하면서, `I`다음에 `OI`가 몇 번 반복되는지 세면 된다.
    3. 반복이 `N` 이상이면, (반복횟수 - N + 1) 만큼 $P_N$패턴이 생김.
        - 예시
            1. 조건
                - S = IOIOIOIOI
                - N = 2
            2. 문자열 분해
                
                ```java
                문자열:   I O I O I O I O I
                인덱스:   0 1 2 3 4 5 6 7 8
                           ↑   ↑   ↑   ↑
                          OI  OI  OI  OI  ← OI가 총 4번 있음
                ```
                
            3. $P_2=IOIOI$ 를 찾는 방법
                
                ```java
                S:   I O I O I O I O I
                     ↑ ↑ ↑ ↑ ↑           → 첫 번째 P2 (0~4)
                         ↑ ↑ ↑ ↑ ↑       → 두 번째 P2 (2~6)
                             ↑ ↑ ↑ ↑ ↑   → 세 번째 P2 (4~8)
                ```
                
            4. 결론
                
                $P_N$의 갯수 = (`OI`반복횟수 - N + 1)
                               3 = (4 - 2 + 1)
                

---

- **실패한 풀이 전략(시간, 메모리 초과)**
    1. 브루트포스를 이용한다.
    2. `for (int left = 0; left < S.length; left++)` 해당 구간 동안 순회한다
        1. `left + 3`을 `subString`했을 떄 $P_N$과 같으면 `count++`

## 사용된 알고리즘
투포인터, 패턴 카운팅 방식

---

# psudo code

```
	1. 문자열을 1회 순회한다.
		a. 순회하면서 i-1 == 'I', i == 'O', i+1 == 'I' 인 패턴을 찾는다.
			a-1. 위 패턴('OI')이 맞으면, pattern++;
			a-2. 이미 i+1을 조회했으므로, i++;
			a-3. 여기서 pattern == N이면, 원하는 패턴 하나가 완성됐음을 의미
				- 해당 패턴을 한개 찾았으므로, count++;
				- 다음 패턴을 찾기위해 IOI패턴 중 OI하나를 빼고 다음으로 넘어가야하므로, pattern--;
```

# code

## Java

```java
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // PN에서 O의 개수
        int M = Integer.parseInt(br.readLine()); // 문자열 길이
        String S = br.readLine();

        int count = 0;      // 결과 개수
        int pattern = 0;    // 현재 OI 반복 횟수

        for (int i = 1; i < M - 1; i++) {
            if (S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
                pattern++;
                i++; // 'OI'에서 i+1을 검증하였으므로 'I'까지 건너뜀

                if (pattern == N) {
                    count++;
                    pattern--; // 겹치는 패턴 고려
                }
            } else {
                pattern = 0; // 패턴이 끊겼으면 초기화
            }
        }

        System.out.println(count);
    }
}
```

## 해결한 오류

| 최적화 요소 | 원래 병목 | 개선 방식 | 대응 방식 |
| --- | --- | --- | --- |
| `substring()` | 문자열 생성 (복사 비용, 메모리 낭비) | **투포인터**로 문자열 범위 직접 탐색 | `substring` 제거 |
| `.equals()` | 문자열 비교 (2N+1 길이) | **패턴 카운팅**으로 문자열을 문자 단위로 직접 비교 | `equals` 제거 |

### 1. String.substring은 복사한 객체를 내놓으므로 메모리를 초과한다.

1. 뭐하는 메서드?
    - 문자열의 부분 문자열을 새로 만들어서 반환해주는 메서드.
2. 느린 이유?
    - Java 6 까지는 substring이 원본문자열을 공유했지만,
    Java 7 이후부터는 부분 문자열을 만들 때 아예 새로운 문자열 객체를 만들어서 복사함.
    
    **javaAPI String.substring()**
    
    ```java
    public String substring(int beginIndex, int endIndex) {
        int length = length();
        checkBoundsBeginEnd(beginIndex, endIndex, length); // 유효 범위 확인
    
        if (beginIndex == 0 && endIndex == length) {
            return this; // 전체 문자열이면 this 반환 (복사 X)
        }
    
        int subLen = endIndex - beginIndex;
        
        // 여기서 복사 발생
        return isLatin1()
            ? StringLatin1.newString(value, beginIndex, subLen)
            : StringUTF16.newString(value, beginIndex, subLen);
    }
    ```
    
    **`StringLatin1.newString(value, beginIndex, subLen)`**
    
    **`StringUTF16.newString(value, beginIndex, subLen)`**
    
    각 메서드에서 copy가 발생하는 부분
    
    **StringLatin1.newString**
    
    ```java
    public static String newString(byte[] val, int index, int len) {
        if (len == 0) {
            return "";
        }
        **return new String(Arrays.copyOfRange(val, index, index + len),
                          LATIN1);**
    }
    ```
    
    **StringUTF16.*newString***
    
    ```java
    public static String newString(byte[] val, int index, int len) {
        if (len == 0) {
            return "";
        }
        if (String.COMPACT_STRINGS) {
            byte[] res = StringUTF16.compress(val, index, len);
            byte coder = StringUTF16.coderFromArrayLen(res, len);
            return new String(res, coder);
        }
        int last = index + len;
        **return new String(Arrays.copyOfRange(val, index << 1, last << 1), UTF16);**
    }
    ```
    
3. 따라서
    - `substring`은 단순히 포인터를 바꾸는 방식이 아님
        - 내부적으로 `byte[]` 배열을 복사해서 새로운 문자열을 생성
    - 길이가 `2N+1`인  새 문자열 객체를 복사해서 만들어냄.
    - 이걸 `M`번 가까이 반복하므로 문자열 복사만으로도 최악의 경우 `2천만번` 이상의 메모리 복사 발생.

### 2. equals의 비효율

`2N+1` 만큼 문자열 비교가 일어난다

1. 뭐하는 메서드
    - 두 문자열이 완전히 같은지 비교
    - 내부적으로 문자 하나씩 차례로 비교함.
2. 느린 이유
    - 문자가 짧으면 거의 차이 없지만, 길이가 길어질 수 록 비교해야할 문자가 많아짐.
        
        **javaAPI String.equals()**
        
        ```java
        public boolean equals(Object anObject) {
                if (this == anObject) {
                    return true;
                }
                return (anObject instanceof String aString)
                        && (!COMPACT_STRINGS || this.coder == aString.coder)
                        **//여기서 하나씩 비교
                        && StringLatin1.equals(value, aString.value);**  
            }
        ```
        
        **StringLatin1.equals(value, aString.value)**
        
        ```java
           @IntrinsicCandidate
            public static boolean equals(byte[] value, byte[] other) {
                if (value.length == other.length) {
                    **for (int i = 0; i < value.length; i++) {
                        if (value[i] != other[i]) {
                            return false;
                        }
                    }**
                    return true;
                }
                return false;
            }
        ```
        
    
    - 즉, 기존 코드의 경우 `O(N * M)` 의 시간복잡도가 나옴
        
        **기존코드**
        
        ```java
        S.substring(...)  //길이 2N+1짜리 문자열 생성
        .equals(test)     //길이 2N+1짜리 문자열 하나씩 비교
        ```
        

---

- 기존코드
    
    ```java
    import java.util.*;
    import java.io.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringBuilder sb = new StringBuilder();
            int N = Integer.parseInt(br.readLine());
            int M = Integer.parseInt(br.readLine());
            String S = br.readLine();
            int count = 0;
    
            sb.append("I");
            for (int i = 0; i < N; i++) {
                sb.append("OI");
            }
    
            String test = sb.toString();
            for (int left = 0; left < M - test.length(); left++) {
                if (S.substring(left, left + test.length()).equals(test)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    }
    
    ```
