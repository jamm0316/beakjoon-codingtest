page link : [https://www.acmicpc.net/problem/2630](https://www.acmicpc.net/problem/2630)

---

# 풀이전략

1. 2차원 배열에 색 정보를 저장한다.
2. 전역 변수로 white, blue 카운트를 만든다.
3. divideAndCount(x, y, size)재귀 함수를 만든다.
    1. 같으면 해당 색 카운트를 1증가시키고 return 
    2. 좌상단, 우상단, 좌하단, 우하단 순으로
4. 초기호출은 (0, 0, N)으로 시작한다.

## 사용된 알고리즘

분할정복(Divide & Conquer)

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    static int[][] graph;
    static int blueCount;
    static int whiteCount;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());
        graph = new int[N][N];
        blueCount = 0;
        whiteCount = 0;

        for (int i = 0; i < N; i++) {
            graph[i] = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
        }

        divideAndCount(0, 0, N);

        bw.write(String.valueOf(whiteCount));
        bw.newLine();
        bw.write(String.valueOf(blueCount));
        bw.close();
        br.close();
    }

    //분할정복 로직
    static private void divideAndCount(int x, int y, int size) {
        if (isSameColor(x, y, size)) {
            if (graph[x][y] == 0) {
                whiteCount++;
            } else {
                blueCount++;
            }
            return;
        }

        int newSize = size / 2;
        divideAndCount(x, y, newSize);  //좌상단
        divideAndCount(x, y + newSize, newSize);  //우상단
        divideAndCount(x + newSize, y, newSize);  //좌하단
        divideAndCount(x + newSize, y + newSize, newSize);  //우하단
    }

    //같은 색깔인지 판단하는 로직
    static private boolean isSameColor(int x, int y, int size) {
        int color = graph[x][y];
        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (graph[i][j] != color) {
                    return false;
                }
            }
        }
        return true;
    }
}
```

## 해결한 오류

### 분할정복이 유용한 이유

1. **전체 문제 직접해결이 번거로움**
    1. 전체 종이에서 모두 같은색인지 판단해야하는데 비효율적임
2. **작은 단위로 문제를 쪼개면 더 쉽게 풀 수 있음**
    1. 검사 → 자르기 → 카운트를 반복할 수 있음
    2. 분할정복의 핵심구조
        - 작은문제로 나눠서(분할), 각 문제를 해결하고(정복), 전체문제를 해결한다
3. 종이가 일정 조건(N = 2^k)라 자르기 쉬움
    1. 2^k 사이즈는 정확히 반으로 자르기 쉬움
    2. 자를 때 모양이 찌그러지지 않으므로 재귀로 나누는데 유리함
4. 같은 작업을 반복적으로 적용해야함
    1. `같은 색인지 확인 + 아니면 나누기` 작업 반복
5. 최악의 경우에도 효율적임
    1. 종이가 전부 섞여있어도 결국 1x1 정사각형 까지만 검사
    2. 트리 형태로 분할되는 구조이므로 시간복잡도 `O(N^2)`
    3. 최대 128이므로 충분히 감당 가능(최대 연산회수 16,384회)
