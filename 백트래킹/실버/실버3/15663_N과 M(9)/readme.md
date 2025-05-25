page link : [https://www.acmicpc.net/problem/15663](https://www.acmicpc.net/problem/15663)

---

# 💡 풀이전략
- java_가독성 우선 ver
    1. Set<String> 으로 중복으로 들어오는 수열 체크
    2. List를 오름차순으로 정렬
    3. visited을 이용해 현재 방문한 인덱스 체크
    4. 재귀 호출
        1. list.size() == M 이면, String을 만든다.
        2. 위 String을 Set.contain()으로 체크한 후, 통과하면 StringBuilder.append()
        3. 백트레킹 이용하여 순회가 끝난 지점은 조건 해제
- java_성능 우선 ver
    1. result에 arr[i]를 순회하며 담고, depth가 M에 도달했을 때, result를 반환
    2. lastUsed를 사용하여, 각 재귀마다 마지막으로 사용한 숫자를 체크함
        1. 이 에 따라 마지막으로 사용한 숫자가 곂치면 해당 값은 result에 추가 안함.

- python
    - 백트래킹 이용
    - 각 재귀 호출 단계마다 last_used변수가 초기화되어, 독립적으로 시행
    - 1번 들렸던 숫자는 중복되어도 다시 탐색 안함.
        - ex) 1 1 1 9 9 입력 시
        1 9
        9 1 출력

## 🎨 사용된 알고리즘
Backtracking: 백트래킹

---

# 🧑🏻‍💻 code

## Java_가독성 우선 ver

```java
import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int N, M;
    static boolean[] visited;
    static List<Integer> arr;
    static Set<String> set = new HashSet<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        visited = new boolean[N];
        st = new StringTokenizer(br.readLine());
        arr = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            arr.add(Integer.parseInt(st.nextToken()));
        }
        arr.sort(Comparator.naturalOrder());

        dfs(new ArrayList<>());

        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void dfs(List<Integer> list) {
        if (list.size() == M) {
            String seq = list.stream().map(String::valueOf).collect(Collectors.joining(" "));
            if (!set.contains(seq)) {
                set.add(seq);
                sb.append(seq).append('\n');
            }
        }

        for (int i = 0; i < arr.size(); i++) {
            if (!visited[i]){
                visited[i] = true;
                list.add(arr.get(i));

                dfs(list);

                visited[i] = false;
                list.remove(list.size() - 1);
            }
        }
    }
}
```

## Java_성능 우선 ver

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static int[] arr, result;
    static boolean[] visited;
    static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        arr = new int[N];
        result = new int[M];
        visited = new boolean[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arr);
        dfs(0);
        System.out.print(sb);
    }

    static void dfs(int depth) {
        if (depth == M) {
            for (int i = 0; i < M; i++) {
                sb.append(result[i]).append(' ');
            }
            sb.append('\n');
            return;
        }

        int lastUsed = -1;
        for (int i = 0; i < N; i++) {
            if (!visited[i] && arr[i] != lastUsed) {
                visited[i] = true;
                result[depth] = arr[i];
                lastUsed = arr[i];

                dfs(depth + 1);

                visited[i] = false;
            }
        }
    }
}
```

## Python

```python
import sys

def backtrack(N, M, sequence, numbers, visited):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    last_used = None  # 마지막에 사용한 숫자 저장 변수
    for i in range(N):
        if not visited[i] and numbers[i] != last_used:
            sequence.append(numbers[i])
            visited[i] = True
            last_used = numbers[i]
            backtrack(N, M, sequence, numbers, visited)

            visited[i] = False
            sequence.pop()

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N, M = lines[0]
    numbers = sorted(lines[1])
    return N, M, numbers

if __name__ == '__main__':
    input_data = sys.stdin.read()
    N, M, numbers = read_data(input_data)
    visited = [False] * N
    backtrack(N, M, [], numbers, visited)
```

# 🪄 해결한 오류

## 1. list1 == list2와 list1.equals(list2) 차이

`list != prev` 비교는 **list와 prev가 같은 객체(참조)인지**를 비교함.

즉, `list`와 `prev`의 **내용이 같은지**를 비교하고 싶었지만,

`!=` 연산자는 참조 비교만 하니까 **내용이 같더라도 다른 객체면 true**, 같은 객체면 false가 된다.

또한 `prev = list;` 이 줄에서 **객체 참조를 복사**했기 때문에, 그 다음부터는 `list`가 다시 재사용되면서 `prev`와 같은 참조를 가지게 됨.

그래서 이후에는 `list != prev`가 항상 `false`가 되고, if 조건에 절대 안 들어오는 현상 발생

**기존코드**

```java
private static void dfs(List<Integer> list) {
if (list.size() == M && list != prev) {
    for (int i : list) {
        sb.append(i).append(" ");
    }
    sb.append('\n');
    prev = list;
}
```

## 2. Stream API import하기

```java
import java.util.*;
```

위 코드는 java.util 패키지 아래에 있는 클래스들만 포함한다.

예를 들어:

- `ArrayList`, `HashMap`, `HashSet`, `Collections`, `Comparator`, `List`, `Queue` 등은 가능
- **java.util.stream은 util 하위 패키지가 아님!** (※ Java에서 패키지는 폴더처럼 계층이 아님)

> ⚠️ 즉, java.util.*은 java.util.stream.*을 포함하지 않는다.
> 

따라서 반드시 따로 써야 함:

```java
import java.util.stream.*; // Stream, Collectors, etc.
```

## 3. Stream.collect()함수 사용

- Stream쪼개서 해석하기
    
    ```java
    String seq = list.stream().map(String::valueOf).collect(Collectors.joining(" "));
    
    ```
    
    1. **list.stream()**
        1. **반환값:** `Stream<Integer>`
        2. **역할:** List<Integer>를 Stream<Integer>로 바꾸어 Stream함수를 사용하도록 함.
    2. **.map(String::valueOf)**
        1. **반환값:** `Stream<String>`
        2. 역할: Integer타입의 1을 “1”로 바꿔주는 역할.
        모두 바뀌면 **Java 컴파일러가 제네릭 타입을 추론해서** Stream<Integer>을Stream<String>으로 바꿔줌.
    3. **.collect()**
        1. **반환값:** 내부 Collector에 따라 다름(Stream → 결과값으로 변환)
        2. **역할:** Stream의 최종 연산 메서드.
    4. **.collect(Collectors.joining(” “))**
        1. **반환값:** `String`
        2. **Collectors 역할:** Collector는 collect를 실행시키기 위한 도구 클래스로 내부에 static으로 선언된 메서드들이 있음.
        3. **joining 역할:** 스트림이 “1”, “7” 일 때 “1 7”으로 공백 기준 join하는 역할

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        static int N, M;
        static boolean[] visited;
        static List<Integer> arr;
        static List<Integer> prev = new ArrayList<>();
        static StringBuilder sb = new StringBuilder();
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());
    
            visited = new boolean[N];
            st = new StringTokenizer(br.readLine());
            arr = new ArrayList<>();
            for (int i = 0; i < N; i++) {
                arr.add(Integer.parseInt(st.nextToken()));
            }
            arr.sort(Comparator.naturalOrder());
    
            dfs(new ArrayList<>());
    
            bw.write(sb.toString());
            bw.close();
            br.close();
        }
    
        private static void dfs(List<Integer> list) {
            if (list.size() == M && list != prev) {
                for (int i : list) {
                    sb.append(i).append(" ");
                }
                sb.append('\n');
                prev = list;
            }
    
            for (int i = 0; i < arr.size(); i++) {
                if (!visited[i]){
                    visited[i] = true;
                    list.add(arr.get(i));
    
                    dfs(list);
    
                    visited[i] = false;
                    list.remove(list.size() - 1);
                }
            }
        }
    }
    ```
