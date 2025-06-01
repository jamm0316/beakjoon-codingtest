import java.util.*;

class Solution {
    public int solution(int k, int[] tangerine) {
        Map<Integer, Integer> countMap = new HashMap<>();

        // 1. 각 귤 크기의 개수를 센다
        for (int size : tangerine) {
            countMap.put(size, countMap.getOrDefault(size, 0) + 1);
        }

        // 2. 개수를 기준으로 내림차순 정렬
        List<Integer> counts = new ArrayList<>(countMap.values());
        counts.sort(Collections.reverseOrder());

        // 3. 가장 많이 나온 것부터 누적해서 k 이상이 될 때까지 종류를 센다
        int kind = 0;
        int sum = 0;

        for (int c : counts) {
            sum += c;
            kind++;
            if (sum >= k) break;
        }

        return kind;
    }
}