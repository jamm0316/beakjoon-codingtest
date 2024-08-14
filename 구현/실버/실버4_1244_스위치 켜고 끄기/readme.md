page link : [https://www.acmicpc.net/problem/1244](https://www.acmicpc.net/problem/1244)

# 💡 풀이전략

1. 입력값 받기
    - N: 배열의 수
    - A: 배열
    - K: 쿼리 갯수
    - queries: 쿼리들(성별, 주어진 수)
2. 남학생의 경우
    - 주어진 수의 배수 바꾸기
        
        ```python
        if gender == 1:
        for i in range(number - 1, N, number):
            toggle_switch(i)
        ```
        
3. 여학생의 경우
    - 주어진 수에서 양쪽으로 대칭이면 바꾸기
        
        ```python
        if gender == 2:
        index = number - 1
        toggle_switch(index)
        left = index - 1
        right = index + 1
        while left >= 0 and right < N and A[left] == A[right]:
        ```
        
## 🎨 사용된 알고리즘
> [!tip]
> 구현(implement)

---

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    switch_controller = SwitchController()  # 명확한 클래스 이름
    switch_controller.parse_input(input)
    switch_controller.process_queries()
    switch_controller.print_switch_states()

class SwitchController:
    def __init__(self):
        self.N = 0  # 스위치 개수
        self.A = []  # 스위치 상태 리스트
        self.queries = []  # 쿼리 리스트

    def parse_input(self, input):
        lines = input.strip().split('\n')
        self.N = int(lines[0])
        self.A = list(map(int, lines[1].split()))
        query_count = int(lines[2])

        for i in range(3, 3 + query_count):
            gender, number = map(int, lines[i].split())
            self.queries.append((gender, number))

    def toggle_switch(self, index):
        self.A[index] = 1 - self.A[index]

    def process_queries(self):
        for gender, number in self.queries:
            if gender == 1:  # 남학생의 경우
                self.toggle_by_male(number)
            elif gender == 2:  # 여학생의 경우
                self.toggle_by_female(number)

    def toggle_by_male(self, number):
        for i in range(number - 1, self.N, number):
            self.toggle_switch(i)

    def toggle_by_female(self, number):
        index = number - 1
        self.toggle_switch(index)
        left = index - 1
        right = index + 1
        while left >= 0 and right < self.N and self.A[left] == self.A[right]:
            self.toggle_switch(left)
            self.toggle_switch(right)
            left -= 1
            right += 1

    def print_switch_states(self):
        for i in range(0, self.N, 20):
            print(' '.join(map(str, self.A[i:i+20])))

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 변수명 직관적으로 수정

### 2. 유닛 테스트 추가

1. `porcess_queries`를 통해 실행과 `toggle_by_male`, `toggle_by_female` 각각의 유닛으로 나누어 고유의 책임을 갖도록 부여함

---

- 기존코드
    
    ```python
    def main():
        input = '8\n0 1 0 1 0 0 0 1\n2\n1 3\n2 3'
        changeSwithch = ChangeSwitch()  #새로운 인스턴스 생성
        changeSwithch.parseArgs(input)
        changeSwithch.switchEach()
        changeSwithch.printResults()
    
    class ChangeSwitch():
        # 인스턴스 초기화
        def __init__(self):
            self.N = 0  # 배열의 크기
            self.A = []  # 배열
            self.K = 0  # 쿼리 갯수
            self.queries = []  # 쿼리들
    
        # 인자(argument) 파싱
        def parseArgs(self, input):
            lines = input.split('\n')
    
            self.N = int(lines[0])  # 배열의 크기
    
            self.A = list(map(int, lines[1].split()))  # 배열
    
            self.K = int(lines[2])   # 쿼리 갯수
    
            # self.queries = []  # 중복코드
            for i in range(3, 3 + self.K):
                query = list(map(int, lines[i].split()))
                self.queries.append(query)
    
        # 스위치 메서드
        def toggle(self, index):
            self.A[index] = 1 - self.A[index]
        
        # 성별에 따른 스위치
        def switchEach(self):
            for query in self.queries:
                gender = query[0]
                number = query[1]
                if gender == 1:  #남자일 경우
                    for i in range(number - 1, self.N, number):
                        self.toggle(i)
    
                elif gender == 2:  #여자일 경우
                    index = number - 1
                    self.toggle(index)
                    before = index - 1
                    after = index + 1
                    while before >= 0 and after < self.N and self.A[before] == self.A[after]:
                        self.toggle(before)
                        self.toggle(after)
                        before -= 1
                        after += 1
        
        # 20개씩 출력하는 메서드
        def printResults(self):
            for i in range(0, self.N, 20):
                print(' '.join(map(str, self.A[i : i + 20])))
    
    if __name__ == '__main__':
        main()
    ```
    

---
