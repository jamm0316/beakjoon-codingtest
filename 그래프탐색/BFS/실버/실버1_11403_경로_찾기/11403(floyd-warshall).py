import sys

def main():
    input_data = sys.stdin.read()
    lines = input_data.splitlines()
    N = int(int(lines[0]))
    graph = [list(map(int, line.split())) for line in lines[1:]]

    floyd_warshall(N, graph)
    # 결과 출력
    for line in graph:
        print(*line)

def floyd_warshall(N, graph):
    # 모든 노드에서 다른 노드로의 경로 정보를 업데이트
    for k in range(N):
        for i in range(N):
            for j in range(N):
                if graph[i][k] == 1 and graph[k][j] == 1:
                    graph[i][j] = 1

if __name__ == '__main__':
    main()
