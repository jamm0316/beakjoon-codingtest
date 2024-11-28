page link : [https://www.acmicpc.net/problem/1931](https://www.acmicpc.net/problem/1931)

---

# 💡 풀이전략

- 변수: query(int), time(ArrayList<Integer[]>), count(int)
- 2차원 배열로 회의 시간을 받는다.
- 풀이계획
    - 끝나는 시간 기준으로 정렬한다
        - 시작시간은 끝나는 시간을 포함하지 않는다.
        - 그래서 시작시간으로 정렬하면 언제 끝날지 모른다.
        - 그러나 끝나는 시간은 시작시간을 포함한다
        - 때문에 끝나는 시간 순으로 정렬하면 빨리 시작하고 빨리 끝나는 순으로 count 할 수 있다
    - 첫번째 회의를 선택하고 이후로 현재 선택된 회의의 끝나는 시간 이후에 시작하는 회의들 중 가장 빨리 끝나는 회의를 반복적으로 선택한다.

## 🎨 사용된 알고리즘


> [!tip]
> greedy(그리디), 정렬

---

# code

## Java

```java
package greedy;

import java.util.*;
import java.io.*;

public class P1931copy {
    public static void main(String[] agrs) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int query = Integer.parseInt(st.nextToken());
        int[][] meetingTable = new int[query][2];

        for (int i = 0; i < query; i++) {
            String[] args = br.readLine().split(" ");
            meetingTable[i][0] = Integer.parseInt(args[0]);
            meetingTable[i][1] = Integer.parseInt(args[1]);
        }

        Arrays.sort(meetingTable, (a, b) -> {
            if (a[1] == b[1]) {
                return Integer.compare(a[0], b[0]);
            }
            return Integer.compare(a[1], b[1]);
        });

        int count = 0;
        int lastMeeting = 0;
        for (int[] eachMeeting : meetingTable) {
            if (eachMeeting[0] >= lastMeeting) {
                count++;
                lastMeeting = eachMeeting[1];
            }
        }
        System.out.println(count);
    }
}

```
