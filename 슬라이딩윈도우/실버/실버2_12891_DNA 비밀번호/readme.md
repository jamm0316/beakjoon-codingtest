page link : [https://www.acmicpc.net/problem/12891](https://www.acmicpc.net/problem/12891)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 조건을 만족하는 암호의 갯수
- 풀이 전략
    1. M길이 만큼 문자열을 추출해 검증한다.
    2. 이 후 N길이 만큼 1개씩 문자열을 넣고, 뺴면서 검증한다.
        1. 암호 조건에 만족하면 count++를 한다.

## 🎨 사용된 알고리즘
슬라이딩 윈도우

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static int[] dnaCondition = new int[4];
    static int[] testDnaCondition = new int[4];  // A C G T
    static String DNA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DNA = br.readLine();
        dnaCondition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        for (int i = 0; i < M; i++) {
            addChar(DNA.charAt(i));
        }
        if (checkCondition()) {
            count++;
        }

        for (int i = M; i < N; i++) {
            removeChar(DNA.charAt(i - M));
            addChar(DNA.charAt(i));
            if (checkCondition()) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static void addChar(char c) {
        if (c == 'A') {
            testDnaCondition[0]++;
        } else if (c == 'C') {
            testDnaCondition[1]++;
        } else if (c == 'G') {
            testDnaCondition[2]++;
        } else if (c == 'T') {
            testDnaCondition[3]++;
        }
    }

    private static void removeChar(char c) {
        if (c == 'A') {
            testDnaCondition[0]--;
        } else if (c == 'C') {
            testDnaCondition[1]--;
        } else if (c == 'G') {
            testDnaCondition[2]--;
        } else if (c == 'T') {
            testDnaCondition[3]--;
        }
    }
    private static boolean checkCondition() {
        for (int i = 0; i < 4; i++) {  //A C G T
            if (dnaCondition[i] > testDnaCondition[i]) {
                return false;
            }
        }
        return true;
    }
}
```

# 🪄 해결한 오류

<img width="514" height="156" alt="image" src="https://github.com/user-attachments/assets/6a998def-976b-4864-91b4-29e6c8502c57" />

## 1. 슬라이딩 윈도우 미적용으로 인한 시간 초과 발생

### 🔥 문제

- 기존 코드는 문자열의 모든 부분 문자열을 substring()으로 잘라서 새 문자열을 생성한다.
- 이 후 toCharArray()를 통해 문자 수를 직접 세고 조건 검사한다.
- 이 방식은 윈도우마다 O(M)의 문자열 연산과 배열 계산이 들어가며, 이를 O(N*M)번 반복한다.
- 결과적으로 총 시간 복잡도는 O(N*M) → 입력 제한 최대치인 N=1,000,000 일 경우 시간 초과 발생

**기존코드**

```java
for (int i = 0; i <= N - M; i++) {
    String testDna = DNA.substring(i, i + M);
    if (checkCondition(testDna)) {
        count++;
    }
}
```

### 🧯해결

- 슬라이딩 윈도우 알고리즘 적용
    - 처음 윈도우의 문자 개수를 한 번만 세고,
    - 이후엔 왼쪽 문자 제거, 오른쪽 문자 추가만 하면서 조건 검사
- addChar() / removeChar() 메서드를 활용해 O(1) 연산으로 상태 업데이트
- 전체 시간복잡도 : O(N)

**수정된 코드**

```java
for (int i = 0; i < M; i++) {
    addChar(DNA.charAt(i));
}
if (checkCondition()) count++;

for (int i = M; i < N; i++) {
    removeChar(DNA.charAt(i - M));
    addChar(DNA.charAt(i));
    if (checkCondition()) count++;
}
```

### ✅ 개선 효과

| **항목** | **기존 코드** | **수정 코드** |
| --- | --- | --- |
| 윈도우 이동 | 매번 substring → O(M) | O(1) 문자 추가/제거 |
| 전체 복잡도 | O(N×M) | O(N) |
| 메모리 사용 | 비교적 높음 (substring 객체 생성) | 낮음 (배열만 사용) |
| 실행 속도 | 느림 (시간 초과 발생 가능) | 빠름 (통과 가능) |

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N, M, count;
        static int[] dnaCondition = new int[4], testDnaCondition = new int[4];  // A C G T
        static String DNA;
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
            DNA = br.readLine();
    
            dnaCondition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    
            for (int i = 0; i <= N - M; i++) {
                String testDna = DNA.substring(i, i + M);
                if (checkCondition(testDna)) {
                    count++;
                }
            }
            System.out.println(count);
        }
    
        private static boolean checkCondition(String testDna) {
            boolean isTrue = true;
            Arrays.fill(testDnaCondition, 0);
    
            for (char c : testDna.toCharArray()) {
                if (c == 'A') {
                    testDnaCondition[0]++;
                } else if (c == 'C') {
                    testDnaCondition[1]++;
                } else if (c == 'G') {
                    testDnaCondition[2]++;
                } else if (c == 'T') {
                    testDnaCondition[3]++;
                }
            }
    
            for (int i = 0; i < 4; i++) {  //A C G T
                if (dnaCondition[i] > testDnaCondition[i]) {
                    isTrue = false;
                }
            }
            return isTrue;
        }
    }
    
    ```
