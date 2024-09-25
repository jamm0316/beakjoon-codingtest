page link : [https://www.acmicpc.net/problem/1436](https://www.acmicpc.net/problem/1436)

---

# 💡 풀이전략

- 출력값 파악
    - 666을 포함한 **n번째** 자연수 구하기(더하기가 아니라 순서)
        
        `666, 1666, 2666, 3666, 4666, 5666, 6660, 6661, 6662, 6663, 6664 …`
        `6669, 7666, 8666, 9666, 10666, 11666, 12666, 13666, 14666, 15666, 16661`
        
    - 따라서 n이 187 또는 500 일 때 187666, 500666이 아님.

---

- 입력값 파악
    - 자연수 n

## 🎨 사용된 알고리즘

> [!tip]
> Brute-Force: 완전 탐색

---

## pseudo code

```python
1. 666을 시작 수로 정한다.
2. number가'666'을 포함하고 있는지 확인한다.
		- 666을 포함하고 있다면 count가 주어진 숫자와 맞는지 확인한다.
			- 맞다면 해당 숫자 반환
			- 틀리다면 1씩 추가
```

---

# code

## Python

```python
def find_end_of_world_number(N):
    count = 0  
    number = 666
    
    while True:
        if '666' in str(number):
            count += 1 
            if count == N:
                return number
        number += 1  

N = int(input())
print(find_end_of_world_number(N))
```

---
