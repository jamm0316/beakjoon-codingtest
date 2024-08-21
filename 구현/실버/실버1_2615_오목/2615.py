import sys

def main():
    input = sys.stdin.read()
    game = Omok_game()
    game.parse_data(input)
    result = game.find_winner()
        

class Omok_game:
    def __init__(self):
        self.game_board = []
    
    def parse_data(self, input):
        lines = input.split('\n')
        self.game_board = [list(map(int, lines[i].split())) for i in range(len(lines))]

    def find_winner(self):
        for x in range(len(self.game_board)):
            for y in range(len(self.game_board[x])):
                if self.game_board[x][y] != 0:
                    focus = self.game_board[x][y]
                    result = self.check_omok(x, y, focus)
                    if result:
                        print(result[0])
                        print(result[1], result[2])
                        return
        print(0)
                
    def check_omok(self, x: int, y: int, focus: int):
        #방향키 설정(우, 하, 우하, 우상)
        direction = [(0, 1), (1, 0), (1, 1), (-1, 1)]
        board_size = 19

        for i in range(len(direction)):
            count = 1
            #방향 설정
            #해당 방향의 다음 요소 정의
            next_x = x + direction[i][0]
            next_y = y + direction[i][1]

            #다음 요소가 배열 내에 있고 focus가 같다면
            while 0 <= next_x < board_size and 0 <= next_y < board_size and self.game_board[next_x][next_y] == focus:
                count += 1  # 카운트 세기

                if count == 5:
                    #육목체크
                    #최초 지점에서 같은방향으로 이전방향
                    if 0 <= (x - direction[i][0]) < board_size and 0 <= (y - direction[i][1]) < board_size and self.game_board[x - direction[i][0]][y - direction[i][1]] == focus:
                        break
                    #마지막 지점에서 같은방향으로 다음방향
                    if 0 <= (next_x + direction[i][0]) < board_size and 0 <= (next_y + direction[i][1]) < board_size and self.game_board[next_x + direction[i][0]][next_y + direction[i][1]] == focus:
                        break
                    return focus, x + 1, y + 1

                #같은 방향으로 하나 더 세기
                next_x += direction[i][0]
                next_y += direction[i][1]
        
        # 5개에 도달하지 못하면 None반환
        return None

if __name__ == '__main__':
    main()
