from itertools import combinations
from collections import deque

# 바이러스 확산을 시뮬레이션하는 함수
def spread_virus(lab, n, m):
    # 상하좌우 이동을 위한 방향 벡터
    directions = [(-1, 0), (1, 0), (0, -1), (0, 1)]
    # 바이러스 확산 후의 연구소 상태를 저장할 리스트 (얕은 복사로 시작)
    infected_lab = [row[:] for row in lab]
    queue = deque()

    # 초기 바이러스 위치를 큐에 추가
    for i in range(n):
        for j in range(m):
            if infected_lab[i][j] == 2:
                queue.append((i, j))
    
    # BFS를 사용해 바이러스를 확산시킴
    while queue:
        x, y = queue.popleft()
        for dx, dy in directions:
            nx, ny = x + dx, y + dy
            # 연구소 내부이고, 빈 칸이면 바이러스를 퍼뜨림
            if 0 <= nx < n and 0 <= ny < m and infected_lab[nx][ny] == 0:
                infected_lab[nx][ny] = 2
                queue.append((nx, ny))
    
    # 바이러스 확산이 끝난 후 남은 빈 칸(안전 영역)의 개수를 반환
    return sum(row.count(0) for row in infected_lab)

# 벽을 세우고 최대 안전 영역을 찾는 함수
def find_max_safe_area(lab, n, m):
    # 빈 칸의 위치를 저장
    empty_spaces = [(i, j) for i in range(n) for j in range(m) if lab[i][j] == 0]
    max_safe_area = 0

    # 3개의 벽을 세우는 모든 조합을 생성
    for walls in combinations(empty_spaces, 3):
        # 선택된 위치에 벽을 세움
        for x, y in walls:
            lab[x][y] = 1

        # 현재 벽 배치에서 바이러스 확산 후의 안전 영역 크기를 계산
        safe_area = spread_virus(lab, n, m)
        # 최대 안전 영역 크기를 갱신
        max_safe_area = max(max_safe_area, safe_area)

        # 벽을 세우기 전의 상태로 복구 (백트래킹)
        for x, y in walls:
            lab[x][y] = 0

    return max_safe_area

# 입력 받기
n, m = map(int, input().split())
lab = [list(map(int, input().split())) for _ in range(n)]

# 최대 안전 영역 계산
print(find_max_safe_area(lab, n, m))
