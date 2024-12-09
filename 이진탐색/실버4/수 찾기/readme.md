page link : [https://www.acmicpc.net/problem/1920](https://www.acmicpc.net/problem/1920)

---

# 💡 풀이전략

- 완전 탐색의 경우 각 쿼리에 대해 $O(N)$의 시간복잡도가 걸림
    - M번 수행하면 총 $O(N*M)$의 연산이 필요하다.
- 이 경우 100,000 x 100,000 = $10^{10}$번의 연산이 필요하므로 시간 내에 해결하지 못함.

---

**이분 탐색은 정렬된 배열에서 작동**

각 쿼리에 대해 $O(logN)$ 시간 복잡도가 걸림.

M번 수행하면 총 $O(MlogN)$으로, $10^6$ 수준의 연산으로 해결 가능


## 사용된 알고리즘

> [!tip]
> 이분탐색

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
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int[] searchTargetArray = new int[N];

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            searchTargetArray[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(searchTargetArray);

        st = new StringTokenizer(br.readLine());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < M; i++) {
            int query = Integer.parseInt(st.nextToken());
            sb.append(binarySearch(searchTargetArray, query)).append("\n");
        }

        bw.write(String.valueOf(sb));
        bw.flush();
        br.close();
        bw.close();
    }

    private static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                return 1;
            } else if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }
}

```

## 해결한 오류

### 1. 시간 초과

```java
Arrays.stream(arr).anyMatch(val → val == i)
```

위와 같이 stream API를 사용한 로직의 경우, `anyMatch` 는 아래와 같은 구현 로직을 사용하여 순차적 순회를 사용하므로 O(n)의 시간이 걸린다.

**anyMatch 내부 로직**

```java
boolean anyMatch(Predicate<? super T> predicate) {
    for (T element : this) { // 스트림의 각 요소를 반복
        if (predicate.test(element)) { // 조건 만족 확인
            return true; // 만족 시 true 반환 및 중단
        }
    }
    return false; // 조건을 만족하는 요소가 없으면 false 반환
}
```

### 2. solution

---

- 기존코드
    
    ```python
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
            StringBuilder sb = new StringBuilder();
    
            int N = Integer.parseInt(st.nextToken());
            int[] targetArr = new int[N];
    
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                targetArr[i] = Integer.parseInt(st.nextToken());
            }
    
            st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
    
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < M; i++) {
                int j = Integer.parseInt(st.nextToken());
                if (Arrays.stream(targetArr).anyMatch(val -> val == j)) {
                    sb.append("1\n");
                } else {
                    sb.append("0\n");
                }
            }
            bw.write(String.valueOf(sb));
            bw.flush();
            br.close();
            bw.close();
        }
    }
    ```
