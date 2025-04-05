page link : [https://www.acmicpc.net/problem/6064](https://www.acmicpc.net/problem/6064)

---

# 풀이전략
- ex)
    
    1,1 \ 2,2 \ 3,3 \ 4,4 \ 5,5 \ 6,6 \ `7,7` \ 8,8 \ 9,9 \ 10,10
    1,11 \ 2,12,\ 3, 1 \ 4, 2 \ 5, 3 \ 6, 4 \ `7, 5` \ 8, 6 \ 9, 7 \ 10, 8
    1, 9 \ 2, 10, \ 3, 11 \ 4, 12 \ 5, 1 \ 6, 2 \ `7, 3` \ 8, 4 \ 9, 5 \ 10, 6 
    1, 7 \ 2, 8 \ 3, 9 \ 4, 10 \ 5, 11 \ 6, 12 \ `7, 1` \ 8, 2 \ 9, 3 \ 10, 4 \
    1, 5 \ 2, 6 \ 3, 7 \ 4, 8 \ 5, 9 \ 6, 10 \ `7, 11` \ 8, 12 \ 9, 1  \ 10, 2 \
    
- 성공한 풀이 전략
    - 구하고자 하는 값
        - 몇 번째 해가 <x:y>로 표현되는지
    - 풀이전략
        1. 카잉 달력에서 해 k는 아래 두 조건을 만족해야함.
            
            ```
            (k % M == x) 또는 (k - 1) % M + 1 == x) → 1부터 시작했기 때문에
            (k % N == y) 또는 (k - 1) % N + 1 == y)
            ```
            
            - 카잉 달력은 <1:1> ~ <M:N>까지 순환되며, **마지막 해는 LCM(M, N)번째 해**
                
                → 왜? M과 N이라는 두 주기의 **동시 반복**이기 때문
                
        2. `k`는 `x`, `x + M`, `x + 2M`, ... 처럼 주기적으로 반복된다.
            
            → 왜냐면 `k ≡ x mod M`이기 때문에.
            
        3. 이 `k`들 중에서 `(k - 1) % N + 1 == y` 를 만족하는 값을 찾으면 된다.
            
            → 카잉 달력은 1년부터 시작하므로 인덱스 보정 필요 (+1, -1)
            
        4. 이 과정을 반복할 수 있는 최대 범위는 **LCM(M, N)까지**이다.
            
            → 왜냐하면 M과 N이 처음으로 동시에 돌아오는 해이므로
            
            그 이후는 주기가 반복되어 이미 나온 해만 반복됨.
            
        5. 따라서 `k = x`, `x+M`, `x+2M`, ...으로 루프 돌면서 `(k-1) % N + 1 == y`를 만족하는 가장 첫 `k`를 찾는다.
        6. 없으면 `-1`을 출력한다.
    
    | **단계** | **설명** |
    | --- | --- |
    | **문제 조건** | k ≡ x mod M, k ≡ y mod N 만족하는 해 k 찾기 |
    | **시작점** | k = x부터 시작 (왜냐면 k ≡ x mod M을 만족해야 하니까) |
    | **증가 폭** | M씩 증가 (주기적으로 반복되는 해만 탐색하기 위해) |
    | **종료 조건** | k > LCM(M, N)이면 탐색 불가 → -1 |
    | **y 판별 방법** | (k-1) % N + 1 == y (1부터 시작이므로 인덱스 조정) |
    | **LCM 필요한 이유** | 그 해 이후는 (x, y) 조합이 반복되므로, 그 이전까지만 탐색 |

- **실패한 풀이전략(-1 조건을 찾지 못함)**
    1. 1차원 배열에 2칸을 만든다.
    2. x:y가 나올 때까지 count++
        1. 1번쨰 칸은 N이 넘어가면 1로
        2. 2번째 칸은 M이 넘어가면 1로
    3. `-1` 조건(‼️틀린 접근‼️)
        1. `N`이 순회할 때마다 같은 `x`의 `y`는 `N - M` 만큼 `-`되는 패턴을 보인다.
            1. `N < M`이라면 모든 순회에서 `x`가 홀수일때, `y`는 무조건 홀수이고
            2. `N > M`이라면
                - `M`이 홀수 순회에서 `x`가 홀수일때, `y`은 홀수
                - `M`이 짝수 순회에서 `x`가 홀수일때, `y`은 짝수

## 사용된 알고리즘
모듈러 연산, 주기적 탐색, 최소공배수(LCM) 계산 → 유클리드 호제법

---

## pseudo code

```
입력: 테스트 케이스 수 T
T번 반복:
	M, N, x, y 입력받기
	최대 해 maxYear = LCM(M,N)
	정답 answer = -1 (초기값)
	
	for k = x부터, maxYear까지, M씩 증가:
		만약 (k - 1) % N + 1 == y 라면:
			answer = k
			break

	answer 출력
```

---

# code

## Java

```java
import java.io.*;

public class P6064 {
    // 최대공약수 (유클리드 호제법)
    private static int gcd(int a, int b) {
        if (b == 0) return a;
        return gcd(b, a % b);
    }

    // 최소공배수 = (a * b) / gcd(a, b)
    private static int lcm(int a, int b) {
        return a * b / gcd(a, b);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());  // 테스트 케이스 개수
        StringBuilder sb = new StringBuilder();

        while (T-- > 0) {
            String[] input = br.readLine().split(" ");
            int M = Integer.parseInt(input[0]);
            int N = Integer.parseInt(input[1]);
            int x = Integer.parseInt(input[2]);
            int y = Integer.parseInt(input[3]);

            int maxYear = lcm(M, N);  // 마지막 해 (달력의 끝)
            int answer = -1;

            // x부터 시작해서 M씩 더하면서 y가 맞는 해를 찾는다
            for (int k = x; k <= maxYear; k += M) {
                // 카잉 달력은 1부터 시작하므로 인덱스를 조정
                if ((k - 1) % N + 1 == y) {
                    answer = k;
                    break;
                }
            }

            sb.append(answer).append("\n");
        }

        System.out.print(sb);
    }
}
```
