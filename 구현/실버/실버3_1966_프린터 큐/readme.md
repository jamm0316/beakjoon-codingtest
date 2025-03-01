page link : [https://www.acmicpc.net/problem/1966](https://www.acmicpc.net/problem/1966)

---

# 풀이전략

1. Queue(큐)와 ProrityQueue(우선순위 큐)를 활용하여 구현
2. Queue에 문서를 저장(문서의 중요도와 원래 인덱스를 함께 저장)
3. PriorityQueue를 사용하여 현재 남은 문서들 중 가장 높은 중요도를 항상 확인
4. 현재 Queue의 첫번째 문서가 가장 높은 중요도인지 확인
    1. 맞다면 출력(카운트 증가)
    2. 아니라면 뒤로 보냄
5. 목표문서가 출력될 때까지 반복

## 사용된 알고리즘

우선순위 큐(PriorityQueue): 우선순위가 높은 요소를 먼저 처리하는 자료구조

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main  {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int testCase = Integer.parseInt(st.nextToken());

        while (testCase-- > 0) {
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());  //문서의 개수
            int M = Integer.parseInt(st.nextToken());  //타겟 문서의 위치

            Queue<int[]> queue = new LinkedList<>();
            PriorityQueue<Integer> priorityQueue = new PriorityQueue<>(Collections.reverseOrder());

            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                int priority = Integer.parseInt(st.nextToken());
                queue.add(new int[]{priority, i});  //우선순위 및 초기 인덱스
                priorityQueue.add(priority);
            }

            int printOrder = 0;  //출력순서 카운트

            while (!queue.isEmpty()) {
                int[] current = queue.poll();
                if (current[0] == priorityQueue.peek()) {  //현재문서가 가장 높은 중요도라면
                    priorityQueue.poll();
                    printOrder++;

                    if (current[1] == M) {  //찾고자 하는 문서면 결과 출력
                        bw.write(String.valueOf(printOrder));
                        bw.newLine();
                        break;
                    }
                } else {
                    queue.add(current);
                }
            }
        }
        bw.close();
        br.close();
    }
}
```

## 해결한 오류

### 1. PriorityQueue개요

- 정의
    
     `PriorityQueue` : 우선순위가 높은 요로를 먼저 처리하는 자료구조
    
- 최소힙(`MInHeap`) vs 최대힙(`MaxHeap`)
    
    
    | 힙의 종류 | 정렬기준 | peek() 반환값 | 설정방법 |
    | --- | --- | --- | --- |
    | 최소 힙(MinHeap) | 값이 작은 것이 높은 우선순위 | 가장 작은 값 | `new PriorityQueue<>()` (기본값) |
    | 최대 힙(MaxHeap) | 값이 큰 것이 높은 우선순위 | 가장 큰 값 | `new PriorityQueue<>(Collections.reverseOrder())` |
- 최소 힙(Min Heap)
    - 기본 PriorityQueue<Integer>는 자동으로 오름차순으로 정렬되어 가장 작은 값이 먼저 나옴
    - 내부적으로 완전 이진 트리 구조로 구현됨, 부모 노드가 항상 자식 노드보다 작음
        
        ```java
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        minHeap.add(5);
        minHeap.add(3);
        minHeap.add(7);
        minHeap.add(1);
        
        System.out.println(minHeap.poll()); // 1 (가장 작은 값)
        System.out.println(minHeap.poll()); // 3
        System.out.println(minHeap.poll()); // 5
        System.out.println(minHeap.poll()); // 7
        ```
        
- 최대 힙(Max Heap)
    - 큰 숫자가 우선순위가 높음 → 내림차순 정렬됨
    - `Collections.reverseOrder()`을 사용하면 `PriorirtyQueue`의 정렬기준이 반대로 됨.
        
        ```java
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
        
        maxHeap.add(5);
        maxHeap.add(3);
        maxHeap.add(7);
        maxHeap.add(1);
        
        System.out.println(maxHeap.poll()); // 7 (가장 큰 값)
        System.out.println(maxHeap.poll()); // 5
        System.out.println(maxHeap.poll()); // 3
        System.out.println(maxHeap.poll()); // 1
        ```
        
- **PriorityQueue 내부 동작 방식**
    - 완전 이진 트리(Complete Binary Tree)기반의 “힙(Heap)” 구조 사용
    - `O(logN)`시간 복잡도로 삽입과 삭제 수행
    - `add()`를 하면 **적절한 위치에 삽입**
    - `poll()`을 하면 최상위(루트) 노드 제거 후 재정렬 됨.
