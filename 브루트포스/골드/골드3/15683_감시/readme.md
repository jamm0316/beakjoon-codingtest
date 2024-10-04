page link : [https://www.acmicpc.net/problem/15683](https://www.acmicpc.net/problem/15683)

---

# 💡 풀이전략

구하고자 하는 값: 최소 0의 갯수

---

전략: 각 CCTV의 모든 방향에 따른 0의 갯수 탐색 후 가장 작은 수 반환.

- CCTV가 한방향으로 탐색을 시작하면, 그 방향의 모든 좌표가 6이 아닌 0인 경우 모두 #으로 바뀐다.
- 가지치기 조건이 있으므로, 백트래킹을 사용한다.

절차

- 입력값 파싱 및 cctv좌표, 형태 파싱, cctv유형에 따른 탐색 방향 파싱
- global변수를 이용해 최소값을 관리하고 #이 채워진 office를 순회하며 0 세기
- sum(row.count(0)반복문)이용
- 백트래킹을 이용해 office에 감시영역 # 찍어내기
    - 백트래킹 종료 조건(cctv의 모든 대수 탐색 → 사각지대 값 변환)

## 🎨 사용된 알고리즘

> [!tip]
> Back Tracking: 백트래킹

---

# code

## Python

```python
import sys
import copy

directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]  #상, 우, 하, 좌
cctvs_directions = {
    #동시에 탐색할 수 있는 지역
    1: [[0], [1], [2], [3]],  #상, 우, 하, 좌
    2: [[0, 2], [1, 3]],  #상하, 우좌
    3: [[0, 1], [1, 2], [2, 3], [3, 0]],  #상우, 우하, 하좌, 좌상
    4: [[0, 1, 2], [1, 2, 3], [2, 3, 0], [3, 0, 1]],  #상우하, 우하좌, 하좌상, 좌상우
    5: [[0, 1, 2, 3]],  #상하좌우
}

def watch_area(x, y, direction_set, temp_office):
    for d in direction_set:
        nx, ny = x, y
        while True:
            nx += directions[d][0]
            ny += directions[d][1]

            if 0 <= nx < N and 0 <= ny < M and temp_office[nx][ny] != 6:
                if temp_office[nx][ny] == 0:
                    temp_office[nx][ny] = '#'
            else:
                break

def backtrack(depth, office):
    global min_behind_area
    
    if depth == len(cctvs):
        behind_area = sum(row.count(0) for row in office)
        min_behind_area = min(min_behind_area, behind_area)
        return
    
    x, y, cctv_type = cctvs[depth]
    for direction_set in cctvs_directions[cctv_type]:
        temp_office = copy.deepcopy(office)
        watch_area(x, y, direction_set, temp_office)
        backtrack(depth + 1, temp_office)
            
input_data = sys.stdin.read()
lines = input_data.splitlines()
N, M = map(int, lines[0].split())
office = []
cctvs = []
for i in range(N):
    row = list(map(int, lines[i + 1].split()))
    office.append(row)
    for j in range(M):
        if 1 <= row[j] <= 5:
            cctvs.append((i, j, row[j]))

min_behind_area = N * M

backtrack(0, office)

print(min_behind_area)
```
