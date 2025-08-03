page link : [https://www.acmicpc.net/problem/20437](https://www.acmicpc.net/problem/20437)

---

# 💡 풀이전략
- 구하고자 하는 값
    - k개의 연속된 문자열 최소길이, k개 연속된 문자열 + 맨 앞, 맨 뒤가 같은 문자열 최대길이
- 핵심 아이디어
    - Map<char, List<int>> 또는 LIst<List<Integer>>를 사용해서 문자별 인덱스 저장
    - 각 문자별 인덱스에서 슬라이딩 윈도우로 K개를 서냍ㄱ해서 구간 길이 계산
    - 최소/최대값 갱신
- 풀이전략
    1. 각 문자의 위치 인덱스를 저장한다.
        1. 예) a → [0, 3, 4, 6]
    2. 각 문자의 위치 리스트에서 K개의 연속된 인덱스를 뽑아서 구간 길이 (length = idx[k-1] - idx[0] + 1)를 계산한다
    3. 이 구간 길이가 가장 짧은 것과, 양 끝 문자가 같은 겨우 중 가장 긴 것을 각각 찾는다.
    4. 모든 문자열(a~z)에 대해 위 과정을 반복해서 최솟값, 최댓값을 갱신한다.

## 🎨 사용된 알고리즘
슬라이딩 윈도우

---

# 🧑🏻‍💻 code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            String W = br.readLine();
            int K = Integer.parseInt(br.readLine());

            List<List<Integer>> charPos = new ArrayList<>();
            for (int i = 0; i < 26; i++) {
                charPos.add(new ArrayList<>());
            }

            //각 문자별 인덱스 저장
            for (int i = 0; i < W.length(); i++) {
                char c = W.charAt(i);
                charPos.get(c - 'a').add(i);
            }

            int min = Integer.MAX_VALUE;
            int max = -1;

            for (int i = 0; i < 26; i++) {
                List<Integer> list = charPos.get(i);
                if (list.size() < K) continue;

                for (int j = 0; j <= list.size() - K; j++) {
                    int start = list.get(j);
                    int end = list.get(j + K - 1);
                    int length = end - start + 1;

                    min = Math.min(min, length);
                    max = Math.max(max, length);
                }
            }

            if (max == -1) {
                sb.append(-1).append('\n');
            } else {
                sb.append(min).append(" ").append(max).append('\n');
            }
        }

        System.out.println(sb);
    }
}

```

# 🪄 해결한 오류

## 1. 인덱스로 슬라이이딩 윈도우 구현하기

### 🔥 문제

일반적인 슬라이딩 윈도우는 누적합이나 포인터를 사용하여 값을 갱신하며 좌 → 우로 이동하는 방식이지만,

이번 문제는 구간이 동적이므로 위와 같은 방식으로 풀이가 불가하다.

### 🧯 해결방안

이번 문제에서는 슬라이딩 윈도우는 인덱스를 기반으로 고정된 크기의 구간(K)을 이동하며 검증하는 구조다.

따라서 아래와 같은 핵심 아이디어가 필요하다.

- `List<List<Integer>> charPos` 를 통해 26개의 알파벳의 인덱스 `List`를 저장한다.
- 각 알파벳 List를 순회하며, List.size()가 K개 이상이면,
    - K개의 인덱스를 슬라이딩 윈도우처럼 묶고, 앞뒤 인덱스를 비교하여 길이(length)를 계산한다.
- 이후 min, max 조건에 따라 정답 후보 갱신한다.

```java
for (int j = 0; j <= list.size() - K; j++) {
    int start = list.get(j);
    int end = list.get(j + K - 1);
    int length = end - start + 1;
```

따라서, 누적합이나 배열 값을 갱신하지 않지만,

고정된 윈도우 크기(K)를 유지한 채 한 칸씩 오른쪽으로 이동하며 판단한다는 점에서

이 방식도 슬라이딩 윈도우의 확장 개념으로 볼 수 있다.
