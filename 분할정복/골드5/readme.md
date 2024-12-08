# 💡 풀이전략

- 4등분하여 분할정복한다.
- 각 분할의 시작점에서부터 index를 1씩 늘려가며 z모양으로 빈칸을 채운다.
- 해당 열, 행에 있는 숫자를 O(1)시간 복잡도로 직접 접근한다.

위 방법으로 풀이 했을 때, 메모리 초과가 일어남.

---

**배열을 4등분하면서 내가 찾고 싶은 위치가 몇 번째 사분면에 있는지 계속 확인하고, 그 사분면까지의 칸 수를 차곡차곡 더해가면 된다.**

즉, 0 1 2 3이 각 사분면 마다 반복적으로 더해지고, 각 사분면마다 0,0 위치에 있는 숫자를 더하고 마지막 size가 2x2인(0 1 2 3)사각형일때 해당 좌표가 있는 부분을 마지막으로 더해주면 된다.

size가 1일 경우에는 순회를 중단한다.

- 그래프를 생성하지 않고 숫자에 직접 접근함.
    - size를 2로 나누어 while 순회
        - row, column이 각각 halfSize에 속할 때(1사분면)
            
            넘어감
            
        - row 는 속하고, column은 벗어날 때(2사분면)
            
            result += size * size
            
            c -= size
            
        - row는 벗어나고, column은 속할 때(4사분면)
            
            result += 2 * size * size
            
            r -= size
            
        - 모두 벗어날 때(3사분면)
            
            result += 3 * size * size
            
            r -= size;
            
            c -= size;
            
## 🎨 사용된 알고리즘


> [!tip]
> 재귀(recursive)
> 분할정복(divide-and-conquer)

---

# code

## Java

```java
import java.util.*;
import java.io.*;

public class Main {
    static int[][] graph;
    static int N;
    static int r;
    static int c;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        // 특정 위치의 값 출력
        System.out.println(findGraph(N, r, c));
    }

    private static int findGraph(int N, int r, int c) {
        int result = 0;
        int size = 1 << N;

        while (size > 1) {
            size /= 2;

            if (r < size && c < size) {

            } else if (r < size && c >= size) {
                result += size * size;
                c -= size;
            } else if (r >= size && c < size) {
                result += 2 * size * size;
                r -= size;
            } else {
                result += 3 * size * size;
                r -= size;
                c -= size;
            }
        }
        return result;
    }
}
```

## 해결한 오류

### 1. graph를 만들면 memory 초과

### 2. 직접접근하는 모식도

![IMG_8263.jpeg](https://prod-files-secure.s3.us-west-2.amazonaws.com/6b8d40ba-5287-42be-84df-56b1c96a2c05/a653ae93-c4e1-49f8-a5be-3461c7fcb836/IMG_8263.jpeg)

각 사분면의 0, 0의 값을 순차적으로 더하고, size가 2x2가 되면 해당 좌표의 값을 더한다.

위 경우 계속해서 halfSize^2*3을 하면 됨

---

- 기존코드
    
    ```java
    import java.util.*;
    import java.io.*;
    
    public class Main {
        static int[][] graph;
        static int N;
        static int r;
        static int c;
    
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            N = Integer.parseInt(st.nextToken());
            r = Integer.parseInt(st.nextToken());
            c = Integer.parseInt(st.nextToken());
    
            // 전체 배열 생성
            int size = 1 << N; // 2^N
            graph = new int[size][size];
    
            // Z 순서로 배열 채우기
            fillZOrder(0, 0, size, 0);
    
            // 특정 위치의 값 출력
            System.out.println(graph[r][c]);
        }
    
        private static void fillZOrder(int startX, int startY, int size, int startValue) {
            if (size == 1) {
                // Base case: 크기가 1일 때 해당 위치를 값으로 설정
                graph[startX][startY] = startValue;
                return;
            }
    
            int halfSize = size / 2;
            int area = halfSize * halfSize;
    
            // 재귀적으로 Z 순서로 채우기
            fillZOrder(startX, startY, halfSize, startValue); // 왼쪽 위
            fillZOrder(startX, startY + halfSize, halfSize, startValue + area); // 오른쪽 위
            fillZOrder(startX + halfSize, startY, halfSize, startValue + 2 * area); // 왼쪽 아래
            fillZOrder(startX + halfSize, startY + halfSize, halfSize, startValue + 3 * area); // 오른쪽 아래
        }
    }
    ```
