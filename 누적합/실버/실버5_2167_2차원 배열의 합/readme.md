page link : https://www.acmicpc.net/problem/2167

# 풀이전략
1. conputePrefixSum 함수 사용
    1. 배열로 matrix 생성
        
        
        | 0 | 0 | 0 | 0 |
        | --- | --- | --- | --- |
        | 0 | 0 | 0 | 0 |
        | 0 | 0 | 0 | 0 |
2. subMatrixSum 함수 사용
    1. 매트릭스에 누적합 수 넣기
        
        
        | 0 | 0 | 0 | 0 |
        | --- | --- | --- | --- |
        | 0 | 1 | 3 | 7 |
        | 0 | 9 | 27 | 63 |
3. main함수 사용
    1. 각 쿼리에 따른 결과값 도출

## 배열의 합이란 개념 적립하기

- 배열의 합이란?
    - 배열의 좌표값이 주어질 때, 4개 좌표값(i, j), (x, y)으로 형성되는 사각형 내 요소 값을 모두 더한 것.
- 1. `(0, 0) ~ (x, y)` 값의 합
    1. 원배열
        
        
        | 1 | 2 | 3 | 4 | 5 |
        | --- | --- | --- | --- | --- |
        | 6 | 7 | 8(A) | 9 | 10 |
        | 11 | 12 | 13 | 14 | 15 |
    2. 누적배열
        
        
        | 1 | 3(D) | 6(B) | 10 | 15 |
        | --- | --- | --- | --- | --- |
        | 7 | 16(C) | 27 | 40 | 55 |
        | 18 | 35 | 59 | 86 | 116 |
    3. (0, 0) ~ (3, 2) 구하는법
        
![image](https://github.com/user-attachments/assets/7f466993-2bd4-4aca-a699-67685930e977)
        <br>
    4. 따라서 누적합 배열 공식
        
        ```javasript
        const prefixSum[x][y] = array[x][y]
                              + prefixSum[x - 1][y]
                              + prefixSum[x][y - 1]
                              - prefixSum[x - 1][y - 1]
        ```
        
    
- 2. `(i, j) ~ (x, y)` 값의 합
    1. 누적배열
        
        
        | 1(D) | 3 | 6(C) | 10 | 15 |
        | --- | --- | --- | --- | --- |
        | 7 | 16 | 27 | 40 | 55 |
        | 18(B) | 35 | 59(A) | 86 | 116 |
    2. (2, 2) ~ (3, 2) 구하는법
        
![image](https://github.com/user-attachments/assets/85cc161d-ff7b-4c90-992b-b930f21e187b)
    <br>
    3. 따라서, `(i, j) ~ (x, y)` 까지의 합

        ```javascript
        const subSum[x][y] = prefixSum[x][y]
                           - prefixSum[i - 1][y]
                           - prefixSum[x][j - 1]
                           + prefixSum[i - 1][j - 1]
        ```
        

---

# pseudo code

```
1. 입력을 읽는다.
    a. 배열 크기 N, M을 읽는다.
    b. N x M 크기의 배열을 읽는다.
    c. 쿼리 개수 K를 읽는다.
    d. K개의 쿼리를 읽는다.

2. 누적 합 배열을 생성한다.
    a. (N+1) x (M+1) 크기의 배열을 0으로 초기화한다.

3. 누적 합 배열을 채운다.
    a. for i from 1 to N:
        b. for j from 1 to M:
            c. 누적 합 배열[i][j] = 배열[i-1][j-1] + 누적 합 배열[i-1][j] + 누적 합 배열[i][j-1] - 누적 합 배열[i-1][j-1]

4. 각 쿼리를 처리한다.
    a. for each query (i, j, x, y):
        b. 부분 배열의 합을 계산한다:
            c. 부분 배열의 합 = 누적 합 배열[x][y] - 누적 합 배열[i-1][y] - 누적 합 배열[x][j-1] + 누적 합 배열[i-1][j-1]
        d. 결과를 저장한다.

5. 결과를 출력한다.
```

---

[질문저장소](https://www.notion.so/a376cdb21ac24aad97ae5fc9584b3e08?pvs=21)

---
