import sys

def main(input_data):
    parsed = ParseData(input_data)
    converter = Tetromino(parsed.N, parsed.M, parsed.graph)
    print(converter.process())
    
class ParseData:
    def __init__(self, input_data):
        lines = input_data.splitlines()
        self.N, self.M = map(int, lines[0].split())
        self.graph = [list(map(int, line.split())) for line in lines[1:]]

class Tetromino:
    def __init__(self, N, M, graph):
        self.N = N
        self.M = M
        self.graph = graph
        self.tetromino = self.initialize_tetromino()
    
    def initialize_tetromino(self):
        all_tetromino = [
            [(0,1), (0,2), (0,3)],[(1,0), (2,0), (3,0)], # ㅣ(회전)
	[(0,1), (1,0), (1,1)],  # ㅁ
    [(1,0), (2,0), (2,1)], [(0,1), (0,2), (1,0)], [(0,1), (1,1), (2,1)], [(0,1), (0,2),(-1,2)], # ㄴ(회전)
    [(0,1),(-1,1),(-2,1)], [(1,0), (1,1), (1,2)], [(0,1), (1,0), (2,0)], [(0,1), (0,2), (1,2)], # ㄴ대칭(회전)
    [(1,0), (1,1), (2,1)], [(0,1),(-1,1),(-1,2)],   # ㄹ(회전)
    [(1,0), (0,1),(-1,1)], [(0,1), (1,1), (1,2)],   # ㄹ대칭(회전)
    [(0,1), (0,2), (1,1)], [(-1,1),(0,1), (1,1)], [(0,1), (0,2),(-1,1)], [(1,0), (2,0), (1,1)]] # ㅏ(회전)
        
        return all_tetromino
    
    def cal(self, i, j, pos):
        sm = self.graph[i][j]
        for di, dj in pos:
            ni, nj = i + di, j + dj
            if 0 <= ni < self.N and 0 <= nj < self.M:
                sm += self.graph[ni][nj]
            else:
                return 0
        return sm

    def process(self):
        ans = 0
        for i in range(self.N):
            for j in range(self.M):
                for pos in self.tetromino:
                    sm = self.cal(i, j, pos)
                    ans = max(ans, sm)
        return ans

if __name__ == '__main__':
    input_data = sys.stdin.read()
    main(input_data)