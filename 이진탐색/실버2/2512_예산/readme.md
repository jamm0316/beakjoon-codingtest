page link : [https://www.acmicpc.net/problem/2512](https://www.acmicpc.net/problem/2512)

---

# 💡 풀이전략

1. 중간지점의 값을 하나 고른다.
2. 상한액 지정 시 전체 금액의 합이 전체예산을 넘는지 확인한다.
3. 넘지 않는 경우 중 최대 상한액을 고른다.

## 🎨 사용된 알고리즘

> [!tip]
> 이진탐색

---

# code

## Java

```java
import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        List<Integer> budget = new ArrayList<>();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            budget.add(Integer.parseInt(st.nextToken()));
        }

        st = new StringTokenizer(br.readLine());
        int totalBudget = Integer.parseInt(st.nextToken());

        budget.sort(Comparator.naturalOrder());

        int limit = upperBudgetLimit(budget, totalBudget);
        System.out.println(limit);
    }

    private static int upperBudgetLimit(List<Integer> budget, int totalBudget) {
        int left = 0;
        int right = budget.get(budget.size() - 1);
        int maxLimit = 0;

        while (left <= right) {
            int limit = (left + right) / 2;

            if (canDistributeBudget(budget, limit, totalBudget)) {
                maxLimit = limit;
                left = limit + 1;
            } else {
                right = limit - 1;
            }
        }
        return maxLimit;
    }

    private static boolean canDistributeBudget(List<Integer> budget, int limit, int totalBudget) {
        int sum = 0;
        for (int b : budget) {
            sum += Math.min(b, limit);
        }

        return sum <= totalBudget;
    }
}
```

## 해결한 오류

### 1. ArrayList.set(index, value)로 기존 배열의 요소를 바꿈

`sumBudget`에 `limit`의 제한조건을 걸고 더하고 싶을 때, 
`Math.min(eachBudget, limit)` 로 계산하면 기존의 배열을 건드리지 않고 계산이 가능함.

```java
private static boolean canDistributeBudget(List<Integer> budget, int limit, int totalBudget) {
    int sum = 0;
    for (int b : budget) {
        sum += Math.min(b, limit);
    }
...
}
```

그러나 기존 코드에서는 `ArrayList.set(index, value)` 를 사용하여, 기존배열을 수정하였기 때문에 다음 로직이 진행될 때, 정확하지 않은 조건으로 로직이 실행됨.

```java
private static boolean canDistributeBudget(List<Integer> budget, int limit, int totalBudget) {
		int midIndex = budget.size() / 2;
		for (int i = midIndex; i < budget.size(); i++) {
		    if (budget.get(i) >= limit) {
		        budget.set(i, limit);
		    }
		}
		
		int sumBudget = budget.stream()
		        .mapToInt(Integer::valueOf)
		        .sum();
...
}
```

### 2. solution

---

- 기존코드
    
    ```java
    import java.io.*;
    import java.util.*;
    
    public class Main {
        public static void main(String[] args) throws IOException {
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            StringTokenizer st = new StringTokenizer(br.readLine());
    
            int N = Integer.parseInt(st.nextToken());
            List<Integer> budget = new ArrayList<>();
    
            st = new StringTokenizer(br.readLine());
            for (int i = 0; i < N; i++) {
                budget.add(Integer.parseInt(st.nextToken()));
            }
    
            st = new StringTokenizer(br.readLine());
            int totalBudget = Integer.parseInt(st.nextToken());
    
            budget.sort(Comparator.naturalOrder());
    
            int limit = upperBudgetLimit(budget, totalBudget);
            System.out.println(limit);
        }
    
        private static int upperBudgetLimit(List<Integer> budget, int totalBudget) {
            int left = budget.get(0);
            int right = budget.get(budget.size() - 1);
            int limit = 0;
            int maxLimit = Integer.MIN_VALUE;
    
            while (left <= right) {
                limit = (left + right) / 2;
    
                if (checkUpperBudgetLimit(budget, limit, totalBudget)) {
                    right = limit - 1;
                } else {
                    left = limit + 1;
                }
                maxLimit = Math.max(maxLimit, limit);
            }
            return maxLimit;
        }
    
        private static boolean checkUpperBudgetLimit(List<Integer> budget, int limit, int totalBudget) {
            int midIndex = budget.size() / 2;
            for (int i = midIndex; i < budget.size(); i++) {
                if (budget.get(i) >= limit) {
                    budget.set(i, limit);
                }
            }
    
            int sumBudget = budget.stream()
                    .mapToInt(Integer::valueOf)
                    .sum();
    
            if (sumBudget > totalBudget) {
                return true;
            } else {
                return false;
            }
        }
    }
    ```
