
page link : [https://www.acmicpc.net/problem/2578](https://www.acmicpc.net/problem/2578)

---

# 💡 풀이전략
1. 입력값 파싱

- 입력값을 한 번에 받아들이고, 이를 적절히 분리하여 빙고판과 사회자가 부르는 숫자 리스트로 나눔.
- 빙고판은 5x5 크기의 2차원 배열로 저장하고, 사회자가 부르는 숫자는 25개의 숫자 리스트로 저장.

2. `Bingo` 클래스 만들기

- `Bingo` 클래스는 빙고 게임의 핵심 로직을 포함. 이 클래스는 입력된 빙고판과 부른 숫자들을 관리하고, 빙고 상태를 추적.

2.1 `check_board` 만들기

- `check_board`는 빙고판의 상태를 추적하기 위한 5x5 크기의 2차원 배열.
- 처음에는 모든 값이 0으로 초기화. 숫자가 불린 후 해당 좌표의 값이 1.
- 이는 어떤 숫자가 불렸는지를 추적하는 데 사용.

2.2 `count_bingo`

- `count_bingo`는 현재 `check_board`에서 빙고의 개수를 세는 메서드.
- 가로줄, 세로줄, 그리고 두 개의 대각선을 검사하여 모든 값이 1인 줄을 카운트.
- 이 메서드는 빙고판의 상태를 확인하고, 게임의 승리 조건인 3줄 이상의 빙고가 발생했는지를 판단하는 데 사용.

2.3 `take_coordinate`

- `take_coordinate` 메서드는 사회자가 부른 숫자를 빙고판에서 찾아 그 숫자의 좌표를 `check_board`에 반영.
- 숫자가 부를 때마다 해당 숫자의 좌표를 찾아 `check_board`에서 해당 위치를 1.
- 이 과정을 통해 게임 진행 중 어떤 숫자가 불렸고, 그로 인해 몇 개의 빙고 줄이 완성되었는지를 추적.

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)
> 시뮬레이션(Simulation)


---

# code

## Python

```python
import sys

def main():
    input_data = sys.stdin.read()
    bingo = Bingo()  # 인스턴스 생성
    bingo.parse_data(input_data)
    bingo.create_check_board()
    bingo.take_coordinate()
    bingo.print_bingo()

class Bingo:
    def __init__(self):
        self.bingo_board = []
        self.numbers = []
        self.check_board = []
        self.coordinate = []

    def parse_data(self, input_data):
        lines = input_data.split('\n')
        self.queries = []
        for i in range(5):
            row = list(map(int, lines[i].split()))
            self.bingo_board.append(row)
        for i in range(5, 10):
            query = list(map(int, lines[i].split()))  # 수정된 부분: 두 번째 5줄의 숫자들을 처리하기 위해 인덱스 5에서 10까지 사용
            self.queries.append(query)
        self.numbers = [number for query in self.queries for number in query]  # 수정된 부분: 호출된 숫자들을 하나의 리스트로 결합

    def create_check_board(self):
        self.check_board = [[0] * 5 for i in range(5)]
    
    def print_bingo(self):
        round_num = 0
        for x, y in self.coordinate:
            self.check_board[x][y] = 1  # 수정된 부분: 좌표의 값을 1로 설정하여 해당 숫자가 호출되었음을 표시
            round_num += 1
            if self.check_bingo() >= 3:  # 수정된 부분: 3줄 이상의 빙고가 완성되면 게임을 종료
                break
        print(round_num)
    
    def check_bingo(self):
        count = 0
        # 가로 줄 체크
        for row in range(5):
            if sum(self.check_board[row]) == 5:
                count += 1
        
        # 세로 줄 체크
        for column in range(5):
            if sum(self.check_board[row][column] for row in range(5)) == 5:  # 수정된 부분: 열을 검사할 때 올바르게 column 변수를 사용
                count += 1
        
        # 대각선 체크 (왼쪽 상단에서 오른쪽 하단으로)
        if sum(self.check_board[i][i] for i in range(5)) == 5:
            count += 1
        
        # 대각선 체크 (오른쪽 상단에서 왼쪽 하단으로)
        if sum(self.check_board[i][4-i] for i in range(5)) == 5:
            count += 1
        
        return count

    def take_coordinate(self):
        for number in self.numbers:
            self.coordinate.append(self.test_number(number))  # 수정된 부분: 호출된 숫자들의 좌표를 추적하기 위해 coordinate 리스트에 추가
    
    def test_number(self, number):
        for row in range(len(self.bingo_board)):
            for column in range(len(self.bingo_board[row])):
                if self.bingo_board[row][column] == number:
                    return [row, column]

if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. round명 수정, ≥3 수정, parse data → 5, 10범위 수정

---

- 기존코드
    
    ```python
    import sys
    
    def main():
        input = sys.stdin.read()
        bingo = Bingo()  #인스턴스 생성
        bingo.parse_data(input)
        bingo.create_check_board()
        bingo.take_coordinate()
        bingo.check_bingo()
        bingo.print_bingo()
    
    class Bingo:
        def __init__(self):
            self.bingo_board = []
            self.numbers = []
            self.check_board = []
            self.coordinate = []
    
        
        def parse_data(self, input):
            lines = input.split('\n')
            self.queries = []
            for i in range(5):
                row = list(map(int, lines[i].split()))
                self.bingo_board.append(row)
                query = list(map(int, lines[i + 5].split()))
                self.queries.append(query)
            self.numbers = [number for query in self.queries for number in query]
    
        def create_check_board(self):
            self.check_board = [[0] * 5 for i in range(5)]
        
        def print_bingo(self):
            round = 0
            for x, y in self.coordinate:
                self.check_board[x][y] += 1
                round += 1
                count = self.check_bingo()
                if count == 3:
                    break
            print(round)
        
        def check_bingo(self):
            count = 0
            for row in range(5):
                if sum(self.check_board[row]) == 5:
                    count += 1
            
            for column in range(5):
                if sum(self.check_board[row][column] for row in range(5)) == 5:
                    count += 1
            
            if sum(self.check_board[i][i] for i in range(5)) == 5:
                count += 1
            
            if sum(self.check_board[i][4-i] for i in range(5)) == 5:
                 count += 1
            
            return count
    
        def take_coordinate(self):
            for number in self.numbers:
                self.coordinate.append(self.test_number(number))
        
        def test_number(self, number):
            for row in range(len(self.bingo_board)):
                for column in range(len(self.bingo_board[row])):
                    if self.bingo_board[row][column] == number:
                        return [row, column]
    
    if __name__ == '__main__':
        main()
    ```
    
