page link : [https://www.acmicpc.net/problem/12851](https://www.acmicpc.net/problem/12851)

---

# 💡 풀이전략
- 구하고자 하는 값
    - 가장 빠른 시간과 찾는 방법의 갯수
- 풀이전략
    - BFS를 이용하여 가장 빠른 시간을 찾는다.
    1. N과 K를 받는다.
    2. `Node class`를 만들고 `pos`와 `time`를 필드로 준다.
    3. visited는 -1로 초기화 하고 노드에 도달하는 최소 time을 넣어준다.
    4. `!queue.isEmpty()` 조건의 `while`문을 만든다.
        1. 현재 시간이 최소 시간보다 크면 검증 안함.
            1. `now.time > minTime` → `continue`
        2. 현재 위치가 K라면, 현재 시간이 최소시간 보다 작으면 cases 검증 후 continue
            1. `now.time < minTime` → `minTime = now.time`, `cases = 1`
            2. `now.time == minTime` → `case++`
            3. `continue`
        3. `for (int next: int[]{now.pos -1, +1, *2})` 를 순환
            1. `next` 가 0, 100_000범위 밖이면 체크 안함(continue)
            2. visited가 -1(방문안함) 이거나, 
            visited[next] ≥ now.time + 1(현재방문 시간 + 1한 것이 다음 방문의 최소이거나 같을 경우) 
            3. `queue.offer(new Node)`

## 🎨 사용된 알고리즘
BFS, 그래프 탐색

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int pos;
        int time;

        Node(int pos, int time) {
            this.pos = pos;
            this.time = time;
        }
    }

    static int N, K, cases, minTime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node(N, 0));
        int[] visited = new int[100_001];
        Arrays.fill(visited, -1);
        visited[N] = 0;
        minTime = Integer.MAX_VALUE;

        while (!queue.isEmpty()) {
            Node now = queue.poll();
            // 현재 시간이 최소 시간보다 크면 더 이상 진행 안함.
            if (now.time > minTime) continue;

            // 현재 위치가 도달 위치라면,
            // 현재 시간이 minTime 보다 작으면 cases = 1
            // 현재 시간이 minTime과 같으면 cases++
            if (now.pos == K) {
                if (now.time < minTime) {
                    minTime = now.time;
                    cases = 1;
                } else if (now.time == minTime) {
                    cases++;
                }
                continue;
            }

            // +1, -1, *2 순환
            for (int next : new int[]{now.pos - 1, now.pos + 1, now.pos * 2}) {
                // 범위 밖이면 continue
                if (next < 0 || 100_000 < next) continue;

                // 방문한 적 없거나, 방문 노드의 시간이 현재 + 1보다 크거나 같으면 검증
                if (visited[next] == -1 || visited[next] >= now.time + 1) {
                    visited[next] = now.time + 1;
                    queue.offer(new Node(next, now.time + 1));
                }
            }
        }

        sb.append(minTime).append('\n').append(cases);
        bw.write(sb.toString());
        bw.close();
        br.close();
    }
}
```
