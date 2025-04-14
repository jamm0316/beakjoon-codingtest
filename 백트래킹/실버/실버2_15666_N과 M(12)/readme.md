page link : [https://www.acmicpc.net/problem/15666](https://www.acmicpc.net/problem/15666)

---

# 풀이전략
- 구하고자 하는 값
    - N개의 숫자 중, M개로 만들 수 있는 조합


- 성공한 풀이전략
    1. Set자료구조를 이용하여 N개의 숫자를 받는다.
    2. Set을 list로 바꾼다.
    3. 백트래킹과 재귀를 사용한다.
        1. param에 (List<Integer> result, int startIdx)를 넣는다.
        2. result.size() == M이면,
            1. StringBuilder에 해당 배열을 순회하며 각 숫자와 띄어쓰기, 개행문자를 append한다.
            2. 이후 return하여 재귀를 빠져나온다.
        3. 위 조건에 만족하지 않으면,
            1. i = startIdx 부터 1씩 추가해가며 순회한다.
            2. result에 sequence.get(i)를 추가한다.
            3. 그 이후, 재귀를 호출하는데 이때 현재까지의 result와 startIdx를 함께 param으로 넘겨주어 같은 경우의 수는 중복체크에서 제외한다.
            4. 재귀가 끝나면, result에서 제일 마지막 요소를 뺀 후 다음 숫자도 검증할 수 있도록 한다.

## 사용된 알고리즘
백트레킹

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    static int N, M;
    static List<Integer> sequence;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        Set<Integer> set = new HashSet<>();
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            set.add(Integer.parseInt(st.nextToken()));
        }
        sequence = new ArrayList<>(set);
        sequence.sort(Comparator.naturalOrder());

        sequenceDfs(new ArrayList<>(), 0);

        bw.write(sb.toString());
        bw.close();
        br.close();

    }

    private static void sequenceDfs(List<Integer> result, int startIdx) {
        if (result.size() == M) {
            for (int num : result) {
                sb.append(num).append(" ");
            }
            sb.append('\n');
            return;
        }
        for (int i = startIdx; i < sequence.size(); i++) {
            result.add(sequence.get(i));
            sequenceDfs(result, i);  //현재 인덱스를 넘겨서 비내림차순 유지
            result.remove(result.size() - 1);
        }
    }
}

```
