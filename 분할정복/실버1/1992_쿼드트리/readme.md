page link : [https://www.acmicpc.net/problem/1992](https://www.acmicpc.net/problem/1992)

---

# 💡 풀이전략

- 전체가 같은 수인지 확인
    - 같은 수라면 그 수 바로 출력
    - 아니라면 4분할 정복
- 4분할하기 위해 N/2실시 배열이 N xN이므로 가로를 2등분, 세로를 2등분하여 4분할로 나눈다는 개념
- 시작지점을 좌상, 우상, 좌하, 우하로 나누어 위에서 실행한 검증로직을 재귀로 호출
    - 반환값은 괄호안에 좌상, 우상, 좌하, 우하를 넣어 반횐

## 🎨 사용된 알고리즘

> [!tip]
> **재귀**: 행렬을 나누고 동일한 작업 반복 수행 필요
> **분할정복**: 작게나누어 처리, 데이터 압축 시 값 확인

- 영역을 동일성에 따라 나누고, 각 부분을 독립적으로 처리해야하 하므로 분할정복 적용

---

# code

## Java

```java
import java.io.*;
import java.util.StringTokenizer;

public class Main {
    private static String compressQuadTree(char[][] matrix, int x, int y, int size) {
        char firstValue = matrix[x][y];
        boolean same = true;

        for (int i = x; i < x + size; i++) {
            for (int j = y; j < y + size; j++) {
                if (matrix[i][j] != firstValue) {
                    same = false;
                    break;
                }
            }
            if (!same) break;
        }
 
        if (same) {
            return String.valueOf(firstValue);
        }

        int halfSize = size / 2;
        String topLeft = compressQuadTree(matrix, x, y, halfSize);
        String topRight = compressQuadTree(matrix, x, y + halfSize, halfSize);
        String bottomLeft = compressQuadTree(matrix, x + halfSize, y, halfSize);
        String bottomRight = compressQuadTree(matrix, x + halfSize, y + halfSize, halfSize);
        return "(" + topLeft + topRight + bottomLeft + bottomRight + ")";
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        char[][] matrix = new char[N][N];

        for (int i = 0; i < N; i++) {
            String line = br.readLine();
            matrix[i] = line.toCharArray();
        }

        String result = compressQuadTree(matrix, 0, 0, N);

        bw.write(result);
        bw.newLine();

        br.close();
        bw.close();
    }
}
```

## 해결한 오류

**머지소트 찾아볼것!!!!!!!! TIL**

### 1. 분할정복 개념 이해

**핵심컨셉(디자인 패러다임)**

- 동일한 로직을 수행하는 큰 문제를 작게 나누어 해결한 뒤 다시 합친다
    - 동일한 로직의 반복 수행이라는 개념으로 인해 재귀로 해결하는 것이 자연스럽다

<img width="526" alt="image" src="https://github.com/user-attachments/assets/10f2acf3-2b34-4605-884f-c5829dbb86b9">

### 2. 해당 문제 분할 정보 모식도

<img width="533" alt="image" src="https://github.com/user-attachments/assets/c3d0d075-5b83-4d11-a70b-7cd5a622356f">
