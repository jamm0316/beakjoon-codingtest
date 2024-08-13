import sys

def main():
    input_data = sys.stdin.read()
    bingo = Bingo()  # 인스턴스 생성
    bingo.parse_data(input_data)
    bingo.create_check_board()
    bingo.take_coordinate()
    bingo.print_bingo()

class Bingo:
    def __init__(self):
        self.bingo_board = []
        self.numbers = []
        self.check_board = []
        self.coordinate = []

    def parse_data(self, input_data):
        lines = input_data.split('\n')
        self.queries = []
        for i in range(5):
            row = list(map(int, lines[i].split()))
            self.bingo_board.append(row)
        for i in range(5, 10):
            query = list(map(int, lines[i].split()))
            self.queries.append(query)
        self.numbers = [number for query in self.queries for number in query]

    def create_check_board(self):
        self.check_board = [[0] * 5 for i in range(5)]
    
    def print_bingo(self):
        round_num = 0
        for x, y in self.coordinate:
            self.check_board[x][y] = 1
            round_num += 1
            if self.check_bingo() >= 3:
                break
        print(round_num)
    
    def check_bingo(self):
        count = 0
        # 가로 줄 체크
        for row in range(5):
            if sum(self.check_board[row]) == 5:
                count += 1
        
        # 세로 줄 체크
        for column in range(5):
            if sum(self.check_board[row][column] for row in range(5)) == 5:
                count += 1
        
        # 대각선 체크 (왼쪽 상단에서 오른쪽 하단으로)
        if sum(self.check_board[i][i] for i in range(5)) == 5:
            count += 1
        
        # 대각선 체크 (오른쪽 상단에서 왼쪽 하단으로)
        if sum(self.check_board[i][4-i] for i in range(5)) == 5:
            count += 1
        
        return count

    def take_coordinate(self):
        for number in self.numbers:
            self.coordinate.append(self.test_number(number))
    
    def test_number(self, number):
        for row in range(len(self.bingo_board)):
            for column in range(len(self.bingo_board[row])):
                if self.bingo_board[row][column] == number:
                    return [row, column]

if __name__ == '__main__':
    main()
