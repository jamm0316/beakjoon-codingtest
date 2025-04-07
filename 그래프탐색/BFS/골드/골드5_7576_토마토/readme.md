page link : [https://www.acmicpc.net/problem/7576](https://www.acmicpc.net/problem/7576)

---

# 풀이전략
- 구하고자 하는 값
    - 토마토가 모두 익을 때까지의 최소날짜
    - 만약 모두 익어있다면 0출력, 모두 못익는다면 -1  출력

---

- 성공한 풀이전략
    1. 입력값 받기
        - `N`, `M` = 상자의 세로(col), 가로(row)칸 수
        - 익은토마토(1), 안익은 토마토(0), 없는칸(-1)
            - `Queue`에 익은 토마토 위치 저장
        - `zeroCount` 를 선언하고, 순회 전 모두 익은 상태인지, 순회 후 모두 익었는지 체크
    2. 상수 설정
        1. dx = {1, -1, 0, 0}, dy = {0, 0, 1, -1}를 상수로 설정하여 탐색에서 사용
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
그래프탐색, 너비우선탐색

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());  //col
        int M = Integer.parseInt(st.nextToken());  //row

        int[][] tomatoBox = new int[M][N];
        Queue<int[]>queue = new LinkedList<>();
        int zeroCount = 0;
        int days = -1;  //제일 마지막 queue의 익은 토마토는 days에서 빼줌

        // tomatoBox 초기화
        for (int i = 0; i < M; i++) {  //row
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {  //col
                tomatoBox[i][j] = Integer.parseInt(st.nextToken());
                //queue, zeroCount 초기화
                if (tomatoBox[i][j] == 1) {
                    queue.offer(new int[]{i, j});
                } else if (tomatoBox[i][j] == 0) {
                    zeroCount++;
                }
            }
        }

        // 모두 익은 상태로 들어왔는지 체크
        if (zeroCount == 0) {
            System.out.println(0);
            return;
        }

        // 모두 익은 상태가 아니라면 순회
        while (!queue.isEmpty()) {
            int dayCycle = queue.size();
            for (int cycle = 0; cycle < dayCycle; cycle++) {
                int[] curTomato = queue.poll();
                int x = curTomato[0], y = curTomato[1];

                for (int i = 0; i < 4; i++) {
                    int nx = x + dx[i];
                    int ny = y + dy[i];

                    if (0 <= nx && nx < M && 0 <= ny && ny < N && tomatoBox[nx][ny] == 0) {
                        tomatoBox[nx][ny] = 1;
                        zeroCount--;
                        queue.offer(new int[]{nx, ny});
                    }
                }
            }
            days++;
        }

        // 모두 익었으면 days, 모두 익지 않았으면 -1 출력
        System.out.println(zeroCount != 0 ? "-1" : days);

    }
}

```
