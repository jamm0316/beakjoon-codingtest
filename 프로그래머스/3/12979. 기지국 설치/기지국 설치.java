class Solution {
    public int solution(int n, int[] stations, int w) {
        int answer = 0;
        int coverage = 2 * w + 1;  // 하나의 5G 기지국이 커버 가능한 아파트 수
        int position = 1;          // 현재 커버되지 않은 시작 지점

        for (int station : stations) {
            int left = station - w;  // 해당 기지국이 커버하는 왼쪽 끝
            if (position < left) {
                int gap = left - position;
                answer += Math.ceil((double) gap / coverage);  // 올림 나눗셈
            }
            position = station + w + 1;  // 다음 커버되지 않은 시작 지점 갱신
        }

        // 마지막 기지국 이후의 구간도 커버해야 할 수 있음
        if (position <= n) {
            int gap = n - position + 1;
            answer += Math.ceil((double) gap / coverage);  // 올림 나눗셈
        }

        return answer;
    }
}