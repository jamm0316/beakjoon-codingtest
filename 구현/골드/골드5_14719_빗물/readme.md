page link : [https://www.acmicpc.net/problem/14719](https://www.acmicpc.net/problem/14719)

---

# 💡 풀이전략

1. 아이디어
    1. 왼쪽에서 제일 높은 순으로 water_line을 구한다.
    2. 오른쪽에서 제일 높은 순으로 water_line을 구한다.
    3. 둘 중 낮은 것이 water_line
    4. water_line - (각 지점의 height) = stagnant_water

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)<br>
> 시뮬레이션(Simulation)
---

# code

## Python

```python
import sys

def main():
    input = sys.stdin.read()
    rainwater = StagnantWater()
    rainwater.is_stagnant_water(input)

class StagnantWater():
    def __init__(self):
        self.H = 0
        self.W = 0
        self.A = []
    
    def parse_data(self, input):
        data = list(map(int, input.split()))
        self.H = data[0]
        self.W = data[1]
        self.A = data[2:]
        self.left_water_line = [0] * self.W
        self.right_water_line = [0] * self.W

    def calculate_left_water_line(self):
        self.left_water_line[0] = self.A[0]
        for i in range(1, self.W):
            self.left_water_line[i] = max(self.left_water_line[i - 1], self.A[i])
    
    def calculate_right_water_line(self):
        self.right_water_line[self.W - 1] = self.A[self.W - 1]
        for i in range(self.W - 2, -1, -1):
            self.right_water_line[i] = max(self.right_water_line[i + 1], self.A[i])
    
    def is_stagnant_water(self, input):
        self.parse_data(input)
        self.calculate_left_water_line()
        self.calculate_right_water_line()
        total_water = 0
        water_line = [0] * self.W
        for i in range(self.W):
            water_line[i] = min(self.left_water_line[i], self.right_water_line[i])
            total_water += water_line[i] - self.A[i]
        return print(total_water)
    
if __name__ == '__main__':
    main()
```

## 해결한 오류

### 1. 데이터 파싱 오류

- `left_water_line`과 `right_water_line`의 경우 `W`가 정의 됨에 따라 정의되는 arguments 이므로, `__init__` 이 아닌 `parse_data` 에서 `W`를 정의 해 준 이후 정의 되어야 함.

---

- 기존코드
    
    ```python
    def main():
        input = '4 8\n3 1 2 3 4 1 1 2'
        rainwater = StagnantWater()
        rainwater.parse_data(input)
        rainwater.is_stagnant_water(input)
    
    class StagnantWater():
        def __init__(self):
            self.H = 0
            self.W = 0
            self.A = []
            self.left_water_line = [0] * self.W
            self.right_water_line = [0] * self.W
        
        def parse_data(self, input):
            data = list(map(int, input.split()))
            self.H = data[0]
            self.W = data[1]
            self.A = data[2:]
            print(self.A)
            print(self.left_water_line)
    
        def calculate_left_water_line(self):
            self.left_water_line[0] = self.A[0]
            for i in range(1, self.W):
                self.left_water_line[i] = max(self.left_water_line[i - 1], self.A[i])
        
        def calculate_right_water_line(self):
            self.right_water_line[self.W - 1] = self.A[self.W - 1]
            for i in range(self.W - 2, -1, -1):
                self.right_water_line[i] = max(self.right_water_line[i + 1], self.A[i])
        
        def is_stagnant_water(self, input):
            self.parse_data(input)
            self.calculate_left_water_line()
            self.calculate_right_water_line()
            total_water = 0
            water_line = [0] * self.W
            for i in range(self.W):
                water_line[i] = min(self.left_water_line[i], self.right_water_line[i])
                total_water += water_line[i] - self.A[i]
            return total_water
        
    if __name__ == '__main__':
        main()
    ```
    

---
