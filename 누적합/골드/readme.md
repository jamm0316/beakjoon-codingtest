page link : https://www.acmicpc.net/problem/3020

---

# 💡 풀이전략
1. 입력데이터 처리:
    1. N: 배열의 수, H: 장애물의 높이, A: 배열
2. 장애물 배열 초기화 및 업데이트
3. 누적합 계산
    1. 배열을 뒤집어 빈공간 부터 누적합 계산
    2. 누적 합 계산 후 다시 배열 뒤집어 원래 순서로 복구
4. 최소 파괴 장애물 수와 그 구간 수 계산:
    1. 각 높이에 대해 파괴해야하는 장애물 수 계산하여 최소값 찾고, 그 구간의 수 계산

## 🎨 사용된 알고리즘
- 누적합
    - 각 높이에서 석순과 종유석의 누적합을 계산하여, 특정 높이에서 파괴해야하는 장애물 수를 효율적으로 계산
- 탐색 및 최소 값 계산
    - 각 높이에 대해 파괴해야 하는 장애물 수를 계산, 이를 기반으로 최소 파괴 장애물 수 와 해당 구간의 수를 계산

## pseudo code
```python
# 석순과 종유석 배열 초기화
initialize obstacles_top and obstacles_bottom arrays with zeros of size H

# 장애물 데이터를 석순과 종유석으로 나누어 저장
for i from 0 to N-1:
    if i is even:
        increment obstacles_bottom[A[i] - 1]
    else:
        increment obstacles_top[A[i] - 1]

# 배열을 뒤집어 빈공간부터 더해줌
obstacles_bottom.reverse()
obstacles_top.reverse()

# 석순과 종유석의 누적 합 계산
for i from 1 to H-1:
    obstacles_bottom[i] += obstacles_bottom[i - 1]
    obstacles_top[i] += obstacles_top[i - 1]

# 배열을 다시 뒤집어 원래 순서로 복구
obstacles_bottom.reverse()

# 각 높이에 대한 파괴할 장애물 수 계산
for i from 0 to H-1:
    obstacles_sum[i] = obstacles_bottom[i] + obstacles_top[i]

# 최소 파괴 블럭 수 계산
min_obstacles = min(obstacles_sum)

# 최소 파괴 블럭 구간 수 계산
count = obstacle_sum.count(min_obstacles)

# 입력 데이터 읽기
print (min_obstacles, count)
```

# code

## Python

```python
import sys
input = sys.stdin.read()
data = list(map(int, input.split()))

#인자받기
N = data[0]
H = data[1]
A = data[2:]

# 석순과 종유석을 저장할 배열
obstacles_top = [0] * (H)
obstacles_bottom = [0] * (H)

# 장애물 데이터를 석순과 종유석으로 나누어 저장
# 두 변수의 숫자가 찍히는 기준이 다름(석순:아래, 종유석:위)
for i in range(N):
    if i % 2 == 0:  # 석순
        obstacles_bottom[A[i] - 1] += 1
    else:  # 종유석
        obstacles_top[A[i] - 1] += 1

obstacles_bottom.reverse()  # 빈공간 부터 더해줘야 함(#그림_1)
obstacles_top.reverse()  # 빈공간 부터 더해줘야 함(#그림_1)

# 석순과 종유석의 누적 합 계산
for i in range(1, H):  # 0번째 수는 고정
    obstacles_bottom[i] += obstacles_bottom[i - 1]
    obstacles_top[i] += obstacles_top[i - 1]
obstacles_bottom.reverse()  # 기준을 맞춰 줌(0:아래, 6:위)

#각 높이에 대한 파괴할 장애물 수 계산
obstacles_sum = [0] * (H)
for i in range(H ):
    obstacles_sum[i] = obstacles_bottom[i] + obstacles_top[i]

# 최소 파괴 블럭 수 계산
min_obstacles = min(obstacles_sum)

# 최소 파괴 블럭 구간 수 계산
count = obstacles_sum.count(min_obstacles)
print(min_obstacles, count)
```

## 해결한 오류

### 1. 시간초과로 인한 시간복잡도 해결

기존 코드

```python
#각 열의 장애물 갯수 세기  -> 여기서 시간복잡도 O(n^2)
for i in range(N):
    if i % 2 == 0:
        for hurdle in range(A[i]):
            row[hurdle] += 1
    else:
        for hurdle in range(H-A[i], H):
            row[hurdle] += 1
```

에서 loop가 중첩되어있으므로, 최악의 경우 O(n^2)까지 시간복잡도가 올라간다.

따라서, 각 장애물을 세는 경우를 누적합으로 나누고, 이를 합하면 시간 복잡도를 O(n)까지 낮출 수 있다.

---

- 기존코드
    
    ```python
    import sys
    input = sys.stdin.read()
    data = list(map(int, input.split()))
    
    #인자받기
    N = data[0]
    H = data[1]
    A = data[2:]
    
    #변수 초기화
    row = [0] * H
    count = 0
    
    #각 열의 장애물 갯수 세기  -> 여기서 시간복잡도 O(n^2)
    for i in range(N):
        if i % 2 == 0:
            for hurdle in range(A[i]):
                row[hurdle] += 1
        else:
            for hurdle in range(H-A[i], H):
                row[hurdle] += 1
    
    #장애물 최소값 구하기
    minimum = min(row)
    
    #빈도수 구하기
    for i in row:
        if i == minimum:
            count += 1
    
    print(minimum, count)
    ```
    

---

[질문저장소](https://www.notion.so/d1a75ae46d41441d861921bb46230afb?pvs=21)
