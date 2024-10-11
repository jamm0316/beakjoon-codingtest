def calculate_total(arr):
    total = 0
    for i in range(len(arr) - 1):
        total += abs(arr[i] - arr[i + 1])
    return total

def backtrack(depth, current_permutation, visited):
    global max_value
    
    if depth == N:
        # 모든 순열을 완성했을 때, 값 계산
        max_value = max(max_value, calculate_total(current_permutation))
        return
    
    for i in range(N):
        if not visited[i]:
            visited[i] = True
            current_permutation.append(numbers[i])
            # 재귀 호출로 다음 깊이로 이동
            backtrack(depth + 1, current_permutation, visited)
            # 백트래킹: 상태 복원
            current_permutation.pop()
            visited[i] = False

def read_data(input_data):
    lines = [list(map(int, line.split())) for line in input_data.splitlines()]
    N = lines[0][0]
    numbers = lines[1]
    return N, numbers

if __name__ == '__main__':
    input_data = '6\n20 1 15 8 4 10'
    N, numbers = read_data(input_data)

    # 초기 설정
    max_value = 0
    visited = [False] * N
    current_permutation = []
    
    # 백트래킹 시작
    backtrack(0, current_permutation, visited)
    
    # 결과 출력
    print(max_value)
