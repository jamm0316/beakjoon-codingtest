page link : https://www.acmicpc.net/problem/2615

---

# 💡 풀이전략

- 구하고 싶은것
    - 이긴 측의 좌상단 바둑돌 좌표 ex) 3 2
    - 누가 이겼나? 검: 1 흰: 2, draw: 0
1. 문제 분할
    1. 1 또는 2가 나오는 지점 확인
    2. 그 시점 부터 오목이 되는지 확인
        1. 이 때 육목이 나오는지 체크
        2. 오목일 때 무엇을 반환할 것인지?
2. 세부 수행 전략
    1. 1 또는 2가 나오는 지점 확인
        1. 19 x 19를 brute force로 확인
    2. 그 시점 부터 오목이 되는지 확인
        1. 4방향 방향키 만들기(하,우,우상, 우하)
        2. 4방향 탐색(전체 배열 내부 and 연속적인 숫자)
            - 검사기: 1 or 2가 걸리면 5방향 중에 연속적인 숫자가 있는지 탐색
                - 있다면 count += 1, 해당 좌표로 검사기 이동 → 같은 검사 반복
                - 없다면 다음으로 넘어감
        3. count == 5라면
            1. 육목 확인(이전 or 이후)
            2. 육목 아니라면 return x+1, y+1, focus 반환
        4. 위 모든 조건 만족 못하면 return None
3. 승리했을 시 foucs, x, y값 반환
    1. 비기면 0반환

## 🎨 사용된 알고리즘

> [!tip]
> 구현(implement)<br>
> 완전탐색(Brute Force)

---

# code

## Python

```python
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
```

## 해결한 오류

### break: 순환, 조건문 탈출 / return: 함수, 메서드 탈출

**기존코드**

```python
def find_winner(self):
    for x in range(len(self.game_board)):
        for y in range(len(self.game_board[x])):
				    if result:
				        print(result[0])
				        print(result[1], result[2])
				        break
		print(0)
```

**실행결과**

```python
1
3 2
0
0
0
...
0
```

- break는 순환문을 빠져나오고, 그 다음 코드 실행.
    - 이에 따라, for문을 순회한 횟수 만큼 `print(0)`가 실행됨.

**수정된 코드**

```python
def find_winner(self):
    for x in range(len(self.game_board)):
        for y in range(len(self.game_board[x])):
				    if result:
				        print(result[0])
				        print(result[1], result[2])
				        return
		print(0)
```

**실행결과**

```python
1
3 2
```

- `return`은 `if문`이 충족된 후 해당 메서드를 빠져나오므로, `print(0)`는 해당 메서드의 `default`값으로 `if문`이 `return`되지 않을 시 호출됨.

### 2. IndexError

바둑판은 19 x 19로 고정되어있으므로 `len(self.game_board)`와 같이 동적으로 범위를 설정해주면, 해당 범위를 벗어날 수 있음.

따라서라는 숫자로 고정해주는 것이 안전하며, 매직 넘버를 `board_size`와 같은 변수로 저장해줄 수 있다.
