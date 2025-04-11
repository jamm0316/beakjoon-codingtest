page link : [https://www.acmicpc.net/problem/16928](https://www.acmicpc.net/problem/16928)

---

# 풀이전략
- 구하고자 하는 값
    - 최소 주사위 횟수

---

- 성공한 풀이 전략
    1. 1번 칸부터 BFS시작
    2. 주사위 눈금을 더해 이동할 수 있는 모든 칸 탐색
    3. 해당 칸에 사다리나 뱀이 있으면, 사다리 또는 뱀의 끝 위치로 이동
    4. 도착한 칸이 처음 도달하는 칸이면 큐에 넣음
    5. 100번 칸에 도달할 때 까지 반복
    6. 이때 각 칸까지 주사위 횟수(깊이)를 저장해서 최소 이동 횟수 계산
- 실패한 풀이 전략(뱀을 타고 내려가는 것은 고려하지 않음)
    1. 사다리와 뱀의 배열을 받는다.
    2. 사다리가 도착하는 곳이 가장 큰 것을 고른다.
        1. 그 곳에 도달하기 까지 얼마나 걸리는지 센다.
        2. 도착점에서 100까지 얼마나 걸리는지 센다.

## 사용된 알고리즘
그래프 탐색, BFS

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int[] board = new int[101];
    static boolean[] visited = new boolean[101];
    static int[] dice = {1, 2, 3, 4, 5, 6};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N, M;

        st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        //board 초기화
        for (int i = 0; i <= 100; i++) {
            board[i] = i;
        }

        for (int i = 0; i < N + M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            board[from] = to;
        }

        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{1, 0});  //시작점, 주사위 횟수
        visited[1] = true;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int pos = current[0];
            int moves = current[1];

            if (pos == 100) {
                System.out.println(moves);
                return;
            }

            for (int i = 0; i < 6; i++) {
                int next = pos + dice[i];
                if (next > 100) continue;

                next = board[next]; // 사다리나 뱀이 있으면 이동

                //사다리나 뱀을 타고 간 곳이 이미 지나쳐온 곳이라면 더이상 탐색 안함
                if (!visited[next]) {
                    visited[next] = true;
                    queue.offer(new int[]{next, moves + 1});
                }
            }
        }
    }
}
```
