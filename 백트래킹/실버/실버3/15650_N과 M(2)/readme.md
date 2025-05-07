page link : [https://www.acmicpc.net/problem/15650](https://www.acmicpc.net/problem/15650)

# 💡 풀이전략

- 전략
    - 백트래킹 사용
    - 숫자를 오름차순을 유지해야하므로, 다음 숫자는 이전보다 큰 숫자만 선택
- 절차
    1. sequence를 만들고, len(seqeunce) == M일 때, sequence를 반환한다.
    2. start number를 매개변수로 준다.
        1. for 구문에서 i를 start number로 설정한다.
        2. backtracking함수 재귀 호출 시, i + 1을 start number로 활용한다.
        3. 위 두개의 코드로 재귀 안에서는 재귀 바깥보다 큰 숫자만 sequence에 appending된다.

## 🎨 사용된 알고리즘

> [!tip]
> BackTracking: 백트래킹

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static StringBuilder sb = new StringBuilder();;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        backtracking(1, new ArrayList<>());
        bw.write(sb.toString());
        bw.close();
        br.close();
    }

    private static void backtracking(int start, List<Integer> list) {
        if (list.size() == M) {
            for (int i : list) {
                sb.append(i).append(" ");
            }
            sb.append('\n');
            return;
        }

        for (int i = start; i <= N; i++) {
            list.add(i);
            backtracking(i + 1, list);
            list.remove(list.size() - 1);
        }
    }
}

```

## Python

```python
import sys

def backtrack(N, M, sequence, start):
    if len(sequence) == M:
        print(' '.join(map(str, sequence)))
        return
    
    for i in range(start, N + 1):
        sequence.append(i)
        backtrack(N, M, sequence, i + 1)
        
        sequence.pop()

input_data = sys.stdin.read()
N, M = map(int, input_data.split())
backtrack(N, M, [], 1)
```
