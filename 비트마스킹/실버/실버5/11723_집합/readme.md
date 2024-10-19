page link : [https://www.acmicpc.net/problem/11723](https://www.acmicpc.net/problem/11723)

---

# 💡 풀이전략

이 문제에서는 집합(S)을 조작하는 다양한 연산을 빠르게 처리. 
주어진 연산의 종류는 add, remove, check, toggle, all, empty 6가지로, 연산의 수가 최대 3,000,000개로 매우 많기 때문에 효율적인 자료 구조와 알고리즘이 필요.

문제에서 주어지는 연산들은 모두 1 ≤ x ≤ 20의 값들로 제한되므로, 일반적인 집합(set)을 사용하는 대신 비트마스크(Bitmask)를 활용하여 빠르게 집합을 조작.
**HashSet의 경우 add 메서드를 실행할 떄 마다 메모리에 각각의 data가 저장되지만, 비트마스크의 경우 하나의 숫자로 각 이진 자리수를 index로 활용하여, 중복을 제거하고 집합으로 사용 가능.** 
비트마스크를 사용하면 집합의 각 요소를 비트로 표현하고, 이를 통해 집합의 상태를 매우 빠르게 조작.

---

- **비트마스크**:
    1. 1에서 20까지의 숫자는 각각의 비트 위치로 대응시킬 수 있습니다.
    2. 예를 들어, 숫자 1은 첫 번째 비트, 숫자 2는 두 번째 비트, …, 숫자 20은 20번째 비트에 대응됩니다.
    3. 이를 통해 집합을 int 타입 변수로 관리하고, 각 연산을 비트 연산으로 수행합니다.
    4. 비트 연산은 매우 빠르기 때문에 많은 연산을 처리해야 하는 이 문제에서 적합합니다.
- **연산 구현**
    1. add x: x 번째 비트를 1로 설정 (OR 연산 사용)
    2. remove x: x 번째 비트를 0으로 설정 (AND와 NOT 연산 사용)
    3. check x: x 번째 비트가 1인지 0인지 확인 (AND 연산 사용)
    4. toggle x: x 번째 비트를 반전시킴 (XOR 연산 사용)
    5. all: 1부터 20까지의 모든 비트를 1로 설정
    6. empty: 모든 비트를 0으로 설정

---

- 시간복잡도
    - 각 연산은 비트 연산으로 O(1)에 수행
    - M개의 연산이 있으므로 전체 시간 복잡도는 O(M)

## 🎨 사용된 알고리즘

> [!tip]
> BItmask: 비트마스크

---

# code

## Python

```python
import sys

# 입력 속도를 위해 sys.stdin 사용
input = sys.stdin.read
data = input().splitlines()

# 비어 있는 집합을 나타낼 비트마스크
S = 0
all_set = (1 << 21) - 1  # 1부터 20까지를 모두 포함하는 비트마스크 (21번째 비트까지 모두 1로)

# 결과를 저장할 리스트
output = []

# 연산의 수
M = int(data[0])

for i in range(1, M + 1):
    command = data[i].split()
    
    if command[0] == "add":
        x = int(command[1])
        S |= (1 << x)
    
    elif command[0] == "remove":
        x = int(command[1])
        S &= ~(1 << x)
    
    elif command[0] == "check":
        x = int(command[1])
        output.append("1" if S & (1 << x) else "0")
    
    elif command[0] == "toggle":
        x = int(command[1])
        S ^= (1 << x)
    
    elif command[0] == "all":
        S = all_set
    
    elif command[0] == "empty":
        S = 0

# 결과 출력
sys.stdout.write("\n".join(output) + "\n")
```

## Java

```python
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        
        int S = 0;
        int allSet = (1 << 21) - 1;  // 1부터 20까지의 모든 수를 포함하는 비트마스크
        int M = Integer.parseInt(br.readLine());
        
        for (int i = 0; i < M; i++) {
            String[] command = br.readLine().split(" ");
            String op = command[0];
            
            if (op.equals("add")) {
                int x = Integer.parseInt(command[1]);
                S |= (1 << x);
            } else if (op.equals("remove")) {
                int x = Integer.parseInt(command[1]);
                S &= ~(1 << x);
            } else if (op.equals("check")) {
                int x = Integer.parseInt(command[1]);
                sb.append((S & (1 << x)) != 0 ? "1\n" : "0\n");
            } else if (op.equals("toggle")) {
                int x = Integer.parseInt(command[1]);
                S ^= (1 << x);
            } else if (op.equals("all")) {
                S = allSet;
            } else if (op.equals("empty")) {
                S = 0;
            }
        }
        
        // 결과 출력
        System.out.print(sb);
    }
}
```
