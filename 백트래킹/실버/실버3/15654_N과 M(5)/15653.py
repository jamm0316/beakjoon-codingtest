import sys

def backtrack(N, M, sequence, number_list, visited):
    if len(sequence) == M:
      print(" ".join(map(str, sequence)))
      return
    
    
    for i in range(N):
        if not visited[i]:
            sequence.append(number_list[i])
            visited[i] = True
            backtrack(N, M, sequence, number_list, visited)
        
            sequence.pop()
            visited[i] = False

input_data = sys.stdin.read()
lines = input_data.splitlines()
N, M = map(int, lines[0].split())
visited = [False] * N
number_list = sorted(list(map(int, lines[1].split())))

backtrack(N, M, [], number_list, visited)
