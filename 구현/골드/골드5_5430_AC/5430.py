from collections import deque

def process_test_case(p, n, arr):
    arr = deque(arr)
    reverse = False
    
    for cmd in p:
        if cmd == 'R':
            reverse = not reverse
        elif cmd == 'D':
            if not arr:
                return "error"
            if reverse:
                arr.pop()
            else:
                arr.popleft()
    
    if reverse:
        arr.reverse()
        
    return '[' + ','.join(map(str, arr)) + ']'

def main():
    T = int(input())
    results = []
    
    for _ in range(T):
        p = input().strip()
        n = int(input().strip())
        arr_input = input().strip()
        
        if n == 0:
            arr = deque()
        else:
            arr = deque(map(int, arr_input[1:-1].split(',')))
        
        result = process_test_case(p, n, arr)
        results.append(result)
    
    for result in results:
        print(result)

if __name__ == "__main__":
    main()
