## 조건
- N개의 정수가 주어지고, K개의 합 중 가장 큰 수를 구하여라.

## 풀이전략
1. sliding window algoritm
    - 일정구간을 갖는 윈도우를 옮겨가며 누적합을 구하는 알고리즘
    - 시간복잡도 : O(n)
2. 각 숫자 순회하며 누적합 구하기
    - 배열의 모든 구간을 순회하며, 매 회차마다 누적합을 다시 구한다.
    - 시간복잡도 : O(n * K)

## pseudo code
1. sliding window algoritm
    - 인자불러오기
    - intialize currentTemperatrue, topTemperature
    - loop using sliding window
    - if currentTemperature > topTemperature
      topTemperature = currentTemperature
    - return topTemperature

2. loop each numbers
    - 인자불러오기
    - intialize currentTemperatrue, topTemperature
    - loop each numbers
    - if currentTemperature > topTemperature
      topTemperature = currentTemperature
    - return topTemperature

## 해결한 오류
1. function에 return이 없으면 undefinded를 반환함
2. input값을 받을 때 줄이 받는 속성이 서로 다를 경우
    - 라인별로 먼저 나눈다
      ```javascript
      const lines = input.split('\n')
      ```
    - 1번째 줄 먼저 정의(dyas, continuos)
      ```javascript
      const [days, continuos] = lines[0].split(' ').map(Number)
      ``` 
    - 2번째 줄 먼저 정의(temperatures)
      ```javascript
      const temperatures = lines[1].split(' ').map(Number)
      ```
