page link : [https://www.acmicpc.net/problem/6603](https://www.acmicpc.net/problem/6603)

---

# 💡 풀이전략

- 백트래킹
- 입력값 분석
    - N, list, 0
    - 각 줄을 query에 저장하고 0을 만나면 종료, query별로 한줄 띄움
- N을 순회하며 len(seqeunce) == 6일 때, 프린트
- start를 매개변수로 받아 해당 숫자보다 큰 숫자만 비교

## 🎨 사용된 알고리즘

> [!tip]
> Backtrack: 백트래킹

---

# code

## Python

```python
import sys

def main(queries):
    for idx, query in enumerate(queries):
        if query[0] == 0:
            return
        lotto_list = query[1:]
        print_combinations(lotto_list)
        backtrack(6, [], lotto_list, 0)
        if idx < len(queries) - 2:
            print()

def print_combinations(lotto_list):
    sequence_length = 6
    backtrack(sequence_length, [], lotto_list, 0)

def backtrack(sequence_length, sequence, lotto_list, start):
    if len(sequence) == sequence_length:
        print(" ".join(map(str, sequence)))
        return

    for i in range(start, len(lotto_list)):
        sequence.append(lotto_list[i])
        backtrack(N, sequence, lotto_list, i + 1)
        sequence.pop()
    
def read_input():
    input_data = sys.stdin.read()
    lines = input_data.splitlines()
    queries = [list(map(int, line.split())) for line in lines]

if __name__ == "__main__":
    queries = read_intput()
    main(queries)
```

## 해결한 오류

### 1. 코드 리펙터링

1. **count 변수를 제거하고, enumerate를 이용해 idx로 줄띄우기 적용**
    
    **기존코드**
    
    ```python
    def main(queries):
        count = 0
        for query in queries:
            if query[0] == 0:
                return
            lotto_list = query[1:]
            count += 1
            backtrack(6, [], lotto_list, 0)
            if count < len(queries) - 1:
                print()
    ```
    
    **수정된 코드**
    
    ```python
    def main(queries):
        for idx, query in enumerate(queries):
            if query[0] == 0:
                return
            lotto_list = query[1:]
            print_combinations(lotto_list)
            backtrack(6, [], lotto_list, 0)
            if idx < len(queries) - 2:
                print()
    ```
    

1. **로또 뽑기 횟수 변수로 지정**
    
    **기존코드**
    
    ```python
    backtrack(6, [], lotto_list, 0)
    ```
    
    **수정된 코드**
    
    ```python
    def print_combinations(lotto_list):
        sequence_length = 6
        backtrack(sequence_length, [], lotto_list, 0)
    ```
    
2. **main에서 실행하던 로직을 세분화하여, 유지보수성을 높힘**
    
    **기존코드**
    
    ```python
    def main(queries):
        count = 0
        for query in queries:
            if query[0] == 0:
                return
            lotto_list = query[1:]
            count += 1
            backtrack(6, [], lotto_list, 0)
            if count < len(queries) - 1:
                print()
    ```
    
    **수정된 코드**
    
    ```python
    import sys
    
    def main(queries):
        for idx, query in enumerate(queries):
            if query[0] == 0:
                return
            lotto_list = query[1:]
            print_combinations(lotto_list)
            backtrack(6, [], lotto_list, 0)
            if idx < len(queries) - 2:
                print()
    
    def print_combinations(lotto_list):
        ...
    
    def backtrack(sequence_length, sequence, lotto_list, start):
        ....
                
    def read_input():
        ....
    
    if __name__ == "__main__":
        queries = read_intput()
        main(queries)
    ```
    

### 2. backtrack함수 수정

1. start 매개변수 추가
    1. 숫자의 조합에서 중복되지 않도록 start 매개변수를 추가하여 백트래킹을 할 때 숫자가 선택된 이후 다시 그 앞의 숫자를 선택하지 않도록 설정.
    2. 출력 조건 수정: 선택된 숫자가 6개가 되었을 때 출력하는 조건 설정.

---

- 기존코드
    
    ```python
    import sys
    
    def main(queries):
        count = 0
        for query in queries:
            if query[0] == 0:
                return
            lotto_list = query[1:]
            count += 1
            backtrack(6, [], lotto_list, 0)
            if count < len(queries) - 1:
                print()
            
    
    def backtrack(N, sequence, lotto_list, start):
        if len(sequence) == N:
            print(" ".join(map(str, sequence)))
            return
    
        for i in range(start, len(lotto_list)):
            sequence.append(lotto_list[i])
            backtrack(N, sequence, lotto_list, i + 1)
            
            sequence.pop()
                
        
    input_data = sys.stdin.read()
    lines = input_data.splitlines()
    queries = [list(map(int, line.split())) for line in lines]
    
    main(queries)
    
    ```
