import sys
input = sys.stdin.read()

def main():
    # 표준 입력으로부터 모든 데이터를 읽고 공백으로 분리하여 리스트로 저장
    data = input.split()
    
    # 첫 번째와 두 번째 값을 N과 M으로 변환
    N = int(data[0])
    M = int(data[1])
    
    # 다음 N개의 숫자를 리스트로 변환
    numbers = list(map(int, data[2:N+2]))
    
    # 쿼리 리스트 초기화 및 데이터에서 쿼리 추출
    queries = []
    for i in range(N+2, len(data), 2):
        queries.append((int(data[i]), int(data[i+1])))
    
    # 누적 합 배열 생성
    prefix_sum = [0] * (N + 1)
    for i in range(1, N + 1):
        prefix_sum[i] = prefix_sum[i - 1] + numbers[i - 1]
    
    # 쿼리 처리 및 결과 저장
    results = []
    for (i, j) in queries:
        result = prefix_sum[j] - prefix_sum[i - 1]
        results.append(result)
    
    # 결과를 한 줄씩 출력
    return '\n'.join(map(str, results))

print(main())
    
