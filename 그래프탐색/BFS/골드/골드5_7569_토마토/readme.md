page link : [https://www.acmicpc.net/problem/7569](https://www.acmicpc.net/problem/7569)

---

# 풀이전략
- 구하고자 하는 값
    - 토마토가 모두 익을 때까지의 최소날짜
    - 만약 모두 익어있다면 0출력, 모두 못익는다면 -1  출력

- 성공한 풀이전략
    1. 입력값 받기
        - `N`, `M`, `H`= 상자의 세로(col), 가로(row), 높이(height)
        - 익은토마토(1), 안익은 토마토(0), 없는칸(-1)
            - `Queue`에 익은 토마토 위치 저장
        - `zeroCount` 를 선언하고, 순회 전 모두 익은 상태인지, 순회 후 모두 익었는지 체크
    2. 상수 설정
        1. dx = {1, -1, 0, 0, 0, 0}, dy = {0, 0, 1, -1, 0, 0}, dz = {0, 0, 0, 0, 1, -1}
        를 상수로 설정하여 탐색에서 사용
    3. 순회 전에 모두 익었는지 확인함(`zeroCount == 0`)
        1. 모두 익었다면 `0`출력, `return;`
    4. `queue`를 순회
        1. `while`아래 `for`문으로 `queue`의 `size`만큼 순회하도록 함.
        2. `queue.poll()`의 위치에서 인접한 위치에(상, 하, 좌, 우) 토마토가 있는지 확인
            1. 있다면 익었는지 확인
                - 익지 않았다면 익음으로 표시하고 해당 좌표 `queue.offer()`
                - `zeroCount--`
        3. 순회가 끝이나면 `days++`
    5. 출력 전에 모두 익었는지 확인함(`zeroCount != 0 ?`)
        1. 모두 익지 않았다면 `-1`출력,
        2. 모두 익없다면 days 출력

## 사용된 알고리즘
그래프이론, BFS

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dy = {0, 0, 1, -1, 0, 0};
    static int[] dz = {0, 0, 0, 0, 1, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //col
        int M = Integer.parseInt(st.nextToken());  //row
        int H = Integer.parseInt(st.nextToken());  //height
        int[][][] tomatoBox = new int[H][M][N];  //가장 바깥쪽 대괄호부터 안쪽 대괄호까지
        Queue<int[]> queue = new LinkedList<>();
        int zeroCount = 0;
        int days = -1;

        for (int i = 0; i < H; i++) {
            for (int j = 0; j < M; j++) {
                st = new StringTokenizer(br.readLine());
                for (int k = 0; k < N; k++) {
                    int tomato = Integer.parseInt(st.nextToken());
                    tomatoBox[i][j][k] = tomato;
                    if (tomato == 1) {
                        queue.offer(new int[]{i, j, k});
                    } else if (tomato == 0) {
                        zeroCount++;
                    }
                }
            }
        }

        if (zeroCount == 0) {
            System.out.println(0);
            return;
        }

        while (!queue.isEmpty()) {
            int cycle = queue.size();
            for (int eachCycle = 0; eachCycle < cycle; eachCycle++) {
                int[] pos = queue.poll();
                int z = pos[0], y = pos[1], x = pos[2];
                for (int i = 0; i < 6; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];
                    int nz = z + dz[i];

                    if (0 <= nx && nx < N &&
                            0 <= ny && ny < M &&
                            0 <= nz && nz < H &&
                            tomatoBox[nz][ny][nx] == 0) {
                        tomatoBox[nz][ny][nx] = 1;
                        zeroCount--;
                        queue.offer(new int[]{nz, ny, nx});
                    }
                }
            }
            days++;
        }

        System.out.println(zeroCount != 0 ? -1 : days);
    }
}

```

## 해결한 오류

### 1. 3차원 배열 int[][][]의 구조

2차원 배열의 경우 `int[][]`는 `int[row][col]` 형태이다. 즉, 제일 바깥쪽에 있는 `[]`가 실제 배열에서 가장 안쪽에 있는 `[]`에 해당한다.

예를들어 `new int[2][10]` 이라면 10개의 index를 가진 배열이 2개 생성된다.

즉, `[[0],[1]…,[9]], [[0],[1]…,[9]]`

이와 동일하게 3차원 배열의 경우 `int[][][]`는 가장 바깥쪽부터 `int[height][row][col]` 이 된다.

예를들어, `new int[3][2][10]` 은 2 x 10 짜리 box가 3층 있다는 뜻이다.

즉, `[[[0],[1],…[9]],[[0],[1],…[9]]], [[[0],[***],…[9]],[[0],[1],…[9]]], [[[0],[1],…[9]],[[0],[1],…[9]]]`

배열은 본인이 직접 index를 찍어봐야 이해가 편하다.

위 배열중 중 `***` 의 index는 `array[1][0][1]` 이다.

풀어서 얘기하면 2층, 1번째 줄, 2번째 토마토라는 것이다.
