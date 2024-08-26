page link : [https://www.acmicpc.net/problem/5430](https://www.acmicpc.net/problem/5430)

# 💡 풀이전략

1. 입력값 파싱
    1. 쿼리갯수, 명령어, 배열갯수, 배열
    2. 덱형식으로 배열 파싱
2. 명령어 최적화
    1. reverse_flag를 이용하여 R명령어 최적화
    2. pop()과 depue.popleft()를 활용하여 D명령어 최적화

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)<br>
> 덱(DEQue, Double-Ended Queue)

---

# code

## Python(sys 바로 받기)

```python
import sys
from collections import deque

def main():
    input = sys.stdin.read()
    ac = AC()
    ac.prase_data(input)
    ac.run_program()

class AC:
    def __init__(self):
        self.K = 0  # 쿼리 개수
        self.commands = []
        self.N = []
        self.A = []
    
    def prase_data(self, input):
        lines = input.splitlines()
        self.K = int(lines[0])
        for i in range(1, len(lines) - 2, 3):
            self.commands.append(lines[i])
            self.N.append(int(lines[i + 1]))
            if lines[i + 2] == "[]":
                array = deque()
            else:
                array = deque(map(int, lines[i + 2].strip("[]").split(',')))
            self.A.append(array)
    
    def run_program(self):
        for i in range(self.K):
            result = self.execute_commands(self.commands[i], self.A[i])
            print(result)
        
    def execute_commands(self, commands, arr):
        reverse = False
        
        for cmd in commands:
            if cmd == "R":
                reverse = not reverse
            elif cmd == "D":
                if not arr:
                    return "error"
                if reverse:
                    arr.pop()
                else:
                    arr.popleft()
        
        if reverse:
            arr.reverse()
        
        return '[' + ','.join(map(str, arr)) + ']'

if __name__ == '__main__':
    main()
```

## Python(input 바로 받기)

```python
from collections import deque

def process_test_case(p, n, arr):
    arr = deque(arr)
    reverse = False
    
    for cmd in p:
        if cmd == 'R':
            reverse = not reverse
        elif cmd == 'D':
            if not arr:
                return "error"
            if reverse:
                arr.pop()
            else:
                arr.popleft()
    
    if reverse:
        arr.reverse()
        
    return '[' + ','.join(map(str, arr)) + ']'

def main():
    T = int(input())
    results = []
    
    for _ in range(T):
        p = input().strip()
        n = int(input().strip())
        arr_input = input().strip()
        
        if n == 0:
            arr = deque()
        else:
            arr = deque(map(int, arr_input[1:-1].split(',')))
        
        result = process_test_case(p, n, arr)
        results.append(result)
    
    for result in results:
        print(result)

if __name__ == "__main__":
    main()
```

## 해결한 오류

### 1. 시간 초과 문제

**기존코드**

```python
def run_command(self, command, i):
		if command == "R":
		    self.A[i] = list(reversed(self.A[i]))
		elif command == "D":
		    if len(self.A[i]) != 0:
		        self.A[i].pop(0)
```

**개선된 코드**

```python
def execute_commands(self, commands, arr):
		reverse = False
	  
		
		for cmd in commands:
		    if cmd == "R":
		        reverse = not reverse
		    elif cmd == "D":
		        if not arr:
		            return "error"
		        if reverse:
		            arr.pop()
		        else:
		            arr.popleft()
```

- **데이터 구조의 차이(deque vs list)**
    - **기존코드**
        - `list.pop(0)`의 시간 복잡도 O(n)
    - **개선된 코드**
        - `deque.pop()` or `deque.popleft()`의 시간 복잡도 O(1)
- **명령어 실행 방식의 차이(reverse flag)**
    - **기존코드**
        - 각 테스트 케이스에서 명령어를 하나씩 처리
        - 만약 `R`이 2번 이상 등장한다면 O(n)은 `R`의 등장마다 실행됨.
    - **개선된 코드**
        - `R`명령어가 등장하면, `reverse flag`를 달아두고 마지막에 한번에 뒤집음.
        - `R`이 여러번 등장해도 `flag`에 집적 접근하는 O(1)을 수행하고 마지막에 `flag == true` or `flag == false`에 따라 O(n)을 실행할지 결정.
        - 따라서 최대 O(n) 최소 O(2)의 시간 복잡도를 가짐

---

- 기존코드
    
    ```python
    import sys
    
    def main():
        input = sys.stdin.read()
        ac = AC()
        ac.prase_data(input)
        ac.run_program()
        return
    
    class AC:
        def __init__(self):
            self.K = 0  # 쿼리 갯수
            self.command = []
            self.N = []
            self.A = []
        
        def prase_data(self, input):
            lines = input.split('\n')
            self.K = int(lines[0])
            for i in range(1, len(lines) - 2, 3):
                self.command.append(lines[i])
                self.N.append(int(lines[i + 1]))
                if lines[i + 2] == "[]":
                    array = []
                else:
                    array = list(map(int, lines[i + 2].strip("[]").split(',')))
                self.A.append(array)
        
        def run_program(self):
            for i in range(self.K):
                for char in self.command[i]:
                    self.run_command(char, i)
                if not self.A[i]:
                    print('error')
                else:
                    print(self.A[i])
            
        def run_command(self, command, i):
            if command == "R":
                self.A[i] = list(reversed(self.A[i]))
            elif command == "D":
                if len(self.A[i]) != 0:
                    self.A[i].pop(0)
    
    if __name__ == '__main__':
        main()
    ```
