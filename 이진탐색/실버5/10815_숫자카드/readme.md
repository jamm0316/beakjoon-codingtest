page link : https://www.acmicpc.net/problem/10815

---

# 💡 풀이전략

- set자료구조에 등록
- query를 돌면서 set 자료구조에 있으면 1 없으면 0 출력

## 🎨 사용된 알고리즘

> [!tip]
> 이진탐색

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        Set<Integer> setArr = new HashSet<>();
        for (int i = 0; i < N; i++) {
            setArr.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(st.nextToken());
            sb.append(checkQueryInArray(setArr, query)).append(" ");
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }

    private static int checkQueryInArray(Set<Integer> setArr, int query) {
        if (setArr.contains(query)) {
            return 1;
        }
        return 0;
    }
}
```
