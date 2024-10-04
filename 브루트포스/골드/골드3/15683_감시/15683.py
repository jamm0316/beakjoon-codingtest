import copy

# 방향 벡터 (상, 우, 하, 좌)
directions = [(-1, 0), (0, 1), (1, 0), (0, -1)]

# CCTV별로 회전할 수 있는 방향 설정 (1번은 4가지, 2번은 2가지, 3번은 4가지, 4번은 4가지, 5번은 1가지)
cctv_directions = {
    1: [[0], [1], [2], [3]],
    2: [[0, 2], [1, 3]],
    3: [[0, 1], [1, 2], [2, 3], [3, 0]],
    4: [[0, 1, 3], [0, 1, 2], [1, 2, 3], [0, 2, 3]],
    5: [[0, 1, 2, 3]]
}

# 주어진 방향으로 감시할 수 있는 영역을 '#'으로 표시
def watch_area(x, y, office, direction):
    N = len(office)
    M = len(office[0])
    for d in direction:
        nx, ny = x, y
        while True:
            nx += directions[d][0]
            ny += directions[d][1]
            if 0 <= nx < N and 0 <= ny < M and office[nx][ny] != 6:
                if office[nx][ny] == 0:
                    office[nx][ny] = '#'
            else:
                break

# 백트래킹 함수
def backtrack(depth, office):
    global min_blind_spot
    
    if depth == len(cctvs):
        # 모든 CCTV 방향 설정이 완료되면, 사각지대 계산
        blind_spot = sum(row.count(0) for row in office)
        min_blind_spot = min(min_blind_spot, blind_spot)
        return
    
    x, y, cctv_type = cctvs[depth]
    for directions_set in cctv_directions[cctv_type]:
        # 현재 사무실 상태를 복사하고, CCTV 감시 영역을 표시
        temp_office = copy.deepcopy(office)
        watch_area(x, y, temp_office, directions_set)
        # 다음 CCTV로 넘어가 백트래킹
        backtrack(depth + 1, temp_office)

# 입력 처리
N, M = map(int, input().split())
office = []
cctvs = []

for i in range(N):
    row = list(map(int, input().split()))
    office.append(row)
    for j in range(M):
        if 1 <= row[j] <= 5:
            cctvs.append((i, j, row[j]))  # CCTV 위치와 타입을 저장

min_blind_spot = N * M

# 백트래킹 시작
backtrack(0, office)

# 결과 출력
print(min_blind_spot)
