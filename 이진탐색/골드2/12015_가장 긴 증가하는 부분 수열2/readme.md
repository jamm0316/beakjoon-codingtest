page link : [https://www.acmicpc.net/problem/12015](https://www.acmicpc.net/problem/12015)

---

# 풀이전략

- 이진 탐색을 활용한 LIS 찾기 (`O(N log N)`)
    - 배열을 순회하며 증가하는 수열을 유지하는 리스트를 관리
    - `Collections.bianrySearch()`를 활용하여 현재 원소를 삽입할 위치 찾음
        - 해당 원소의 실제 삽입 위치를 `-(위치)` 로 표시
        - 따라서 `list = [10, 20];`, `num = 30;` 과 같은 경우
            
            `Collections.binarySearch(list, num);`
            
            `-3` 반환
            
            따라서, `-(pos + 1)` 이 삽입될 진짜 `index`가 됨.
            
    - 만약 현재 원소가 리스트의 마지막 원소보다 크다면 그냥 추가.
    - 그렇지 않다면 적절한 위치에 덮어씌움(이러면 단순 길이 유지가능)
    - 최종 리스트의 크기가 정답.

## 사용된 알고리즘

이분탐색

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] A = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        List<Integer> list = new ArrayList<>();

        for (int num : A) {
            int pos = Collections.binarySearch(list, num);  //이진 탐색으로 위치 찾기
            if (pos < 0) {
                pos = -(pos + 1);  //삽입할 위치 변환
            }

            if (pos == list.size()) {
                list.add(num);
            } else {
                list.set(pos, num);
            }
        }
        System.out.println(list.size());
    }
}

```

## 해결한 오류

### 1. 풀이전략 생각하기

- for 문을 돌면서 max보다 크면 count를 센다

`예상오류)` 이 경우 가장 큰수가 먼저 나오고 가장 작은수가 마지막에나오면 못품

ex) 100 3 5 3 2 3 4 5 1

---

- for문을 돌면서 가장 작은 수가 있으면 index 저장
    - 이 때 같은 수가 나오지 않았을 때만 인덱스 저장

`예상오류)` 이 경우 가장 마지막에 제일 작은 수가 나오면 못 품

ex) 100 3 5 3 2 3 4 5 1 

### 2. `Collections.binarySearch(list, num)`

반환값 ⇒ -(들어가야할 index 실제 값)

따라서 `-(반환값 + 1)` 가 실제 들어가야할 index 의미

| **num** | **`lis` 상태** | **`binarySearch(lis, num)`** | **`pos` 값** | **`if pos == lis.size()`** | **결과** |
| --- | --- | --- | --- | --- | --- |
| 10 | [] | -1 → `0` | 0 | Yes (추가) | `[10]` |
| 20 | [10] | -2 → `1` | 1 | Yes (추가) | `[10, 20]` |
| 10 | [10, 20] | `0` | 0 | No (교체) | `[10, 20]` |
| 30 | [10, 20] | -3 → `2` | 2 | Yes (추가) | `[10, 20, 30]` |
| 20 | [10, 20, 30] | `1` | 1 | No (교체) | `[10, 20, 30]` |
| 50 | [10, 20, 30] | -4 → `3` | 3 | Yes (추가) | `[10, 20, 30, 50]` |
