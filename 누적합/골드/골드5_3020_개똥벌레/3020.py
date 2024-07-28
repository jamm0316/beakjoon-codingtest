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
