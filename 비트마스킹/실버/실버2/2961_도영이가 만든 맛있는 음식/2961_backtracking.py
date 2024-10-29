import sys
input = sys.stdin.read

# 전역 변수 선언
query = 0
min_num = float('inf')
arr = []

def find_min_difference(idx, sour, bitter, used):
    global min_num
    if idx == query:
        if used > 0:
            difference = abs(sour - bitter)
            min_num = min(min_num, difference)
        return

    # 재료를 사용한 경우
    find_min_difference(idx + 1, sour * arr[idx][0], bitter + arr[idx][1], used + 1)
    # 재료를 사용하지 않은 경우
    find_min_difference(idx + 1, sour, bitter, used)

def main():
    global query, arr
    data = input().strip().splitlines()
    query = int(data[0].strip())
    arr = [list(map(int, line.split())) for line in data[1:query + 1]]

    # 재귀 함수 호출
    find_min_difference(0, 1, 0, 0)

    # 결과 출력
    print(min_num)

if __name__ == "__main__":
    main()
