page link : [https://www.acmicpc.net/problem/2098](https://www.acmicpc.net/problem/2098)

---

# 💡 풀이전략

브루트포스는 너무 많은 경우의 수가 발생하므로, 비트마스킹이나 동적 계획법을 사용.

---

1. 입력 및 초기 설정
    - 도시의 수 N과 비용행렬 W가 주어짐.
    - DP[][]: 방문한 도시 상태와 현재 도시를 기준으로 최소 비용을 저장하는 DP테이블 구성
    - 방문한 도시들 상태는 비트마스킹을 사용해 표현(예를 들어 4개 도시 중 1,3번 도시 방문 시 0101)
2. 재귀 함수 정의(DFS + DP)
    - 현재 도시와방문한 도시 상태를 기준으로 최소 비용 계산.
    - 재귀적으로 현재 도시에 방문하지 않은 다른 도시로 이동하며 최소비용 업데이트.
    - 이미 계산된 상태는 DP테이블을 참조해 중복 계산 방지.
3. 비용계산
    - 모든 도시 방문 시, 출발 도시로 돌아가는 비용 추가하여 최종 비용 계산.
    - 방문할 수 없는 경우 INF값 반환해 최소 값 계산에 방해되지 않도록 처리
4. 출력
    - 초기 도시에서 출발해 모든 도시 방문 뒤 돌아오는 최소비용 출력
</aside>

## 🎨 사용된 알고리즘

> [!tip]
> 비트마스킹(Bitmarsking)
> DFS(Depth First Search)
> DP(Dynamic Programing)

---

# code

## Java

```java
package bitmask;

import java.util.Scanner;

public class Prob2098 {

    static final int INFINITY = 1_000_000_000;
    static int cities;  //도시의 수
    static int[][] cost;  //비용행렬
    static int[][] minTravelCost;  //DP테이블
    static int startCity = 0;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        //입력
        cities = input.nextInt();
        cost = new int[cities][cities];
        minTravelCost = new int[1 << cities][cities];  //2^n 크기의 비트마스크 테이블

        //initializeCost
        for (int i = 0; i < cities; i++) {
            for (int j = 0; j < cities; j++) {
                cost[i][j] = input.nextInt();
            }
        }

        //initializeMinTravelCost
        for (int i = 0; i < (1 << cities); i++) {
            for (int j = 0; j < cities; j++) {
                minTravelCost[i][j] = -1;
            }
        }

        //결과 출력
        System.out.println(calculateMinTravelCost(1, startCity));

    }

    static int calculateMinTravelCost(int visitedCities, int currentCity) {

        //1. 최종반환 메서드: 모든 도시를 방문할 경우, 출발도시로 돌아가는 비용 반환
        if (travelAll(visitedCities)) {
            return cost[currentCity][startCity] != 0 ? cost[currentCity][startCity] : INFINITY;
        }

        //2. minTravelCost 채우는 조건: 이미 계산된 경우 DP 값 반환
        if (minTravelCost[visitedCities][currentCity] != -1) {
            return minTravelCost[visitedCities][currentCity];
        }

        //3. minTravelCost 채우는 로직: 이미 계산되지 않은 경우(즉 != -1이 아닌 경우) 초기화
        minTravelCost[visitedCities][currentCity] = INFINITY;

        //다음 방문할 도시 결정
        for (int nextCity = 0; nextCity < cities; nextCity++) {
            if ((visitedCities & (1 << nextCity)) == 0 && cost[currentCity][nextCity] != 0) {  //다음도시를 방문한 적이 없고, 다음 도시와 현재도시가 연결되어 있으면
                int calculatedCost
                        = cost[currentCity][nextCity]
                        + calculateMinTravelCost(visitedCities | (1 << nextCity), nextCity);

                minTravelCost[visitedCities][currentCity]
                        = Math.min(minTravelCost[visitedCities][currentCity], calculatedCost);
            }
        }

        return minTravelCost[visitedCities][currentCity];
    }

    private static boolean travelAll(int visited) {
        return visited == (1 << cities) - 1;
    }
}
```

## 해결한 오류

### 1. 무한대 숫자는 가급적 사용하지 말 것

- 문제의 비용 제한
    - 문제에서 도시 간 이동 비용은 `1,000,000` 이하의 양의 정수
    - 도시의 최대 개수는 16이므로 모든 도시를 방문하는 최대 비용 경로는 이론상 `15 * 1,000,000` = `15,000,000`
    - 따라서 `1,000,000,000`은 위 값을 크게 초과함.
- 오버플로우 방지
    - `Integer.MAX_VALUE` 인 `2,147,382,647`에 비해 `1,000,000,000`은 여유가 있어 다른 계산과 덧셈에서도 오버플로우 위험이 줄어듦.
- 메모리 효율
    - `1,000,000,000`은 int 자료형에 안전하게 저장되며, 비교연산자에서 명확한 값으로 사용됨. 자주 사용하는 값이므로 프로그램에서 무한대를 설정할 때 성능과 가독성 모두 고려
