page link : https://www.acmicpc.net/problem/1181

---

# 풀이전략

1. 정렬이 덮어씌워지지 않게 `Comparator`의 `compare`함수를 구현
2. `Comparator`의 `comparing`함수 이용.

```java
Comparator.comparingInt(String::length)
       .thenComparing(Comparator.naturalOrder())
```

## 사용된 알고리즘

정렬
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
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<String> array = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String a = st.nextToken().trim();
            if (!array.contains(a)) {
                array.add(a);
            }
        }
        array.sort(Comparator.comparingInt(String::length)
                .thenComparing(Comparator.naturalOrder()));

        for (String str : array) {
            sb.append(str).append("\n");
        }

        bw.write(sb.toString());
        bw.flush();
        bw.close();
        br.close();
    }
}

```

## 해결한 오류

### 1. Comparator의 comparing함수

Java8에서 추가된 메서드

1. 코드의 가독성을 높혀주고 코드양을 크게 줄여준다.
2. `comparingInt(String::length)`: String의 length속성을 비교하여 숫자를 오름차순으로 정렬한다.
3. `thenComparing(Comparator.natruralOrder())`: 위에서 문자열의 길이 순으로 정렬한 배열을 다시 사전순으로 정렬한다.
4. 결과적으로 문자열의 길이가 1번 우선순위로 정렬되고, 사전순이 2번 우선순위로 정렬된다.
5. **여기서 이득은, 정렬이 덮어쓰기가 되지 않고 순차로 적용된다는 점!!**
