page link : [https://www.acmicpc.net/problem/11279](https://www.acmicpc.net/problem/11279)

---

# 풀이전략

1. 우선순위 큐 생성(내림차순으로)
2. 0이 들어오면 poll()실행

## 사용된 알고리즘

우선순위 큐(priorityQueue)

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static BufferedReader br;
    static BufferedWriter bw;
    
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        bw = new BufferedWriter(new OutputStreamWriter(System.out));
        Queue<Integer> queue = new PriorityQueue<>(Comparator.reverseOrder());

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            int command = Integer.parseInt(br.readLine());
            if (command == 0) {
                if (queue.isEmpty()) {
                    writeNumber(0);
                } else {
                    writeNumber(queue.poll());
                }
            } else {
                queue.offer(command);
            }
        }
        bw.close();
        br.close();
    }

    private static void writeNumber(int x) throws IOException {
        bw.write(String.valueOf(x));
        bw.newLine();
    }
}

```
