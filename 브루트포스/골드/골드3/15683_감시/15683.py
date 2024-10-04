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
