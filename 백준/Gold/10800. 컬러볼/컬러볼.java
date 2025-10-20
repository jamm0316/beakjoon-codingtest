import java.io.*;
import java.util.*;

public class Main {
    static class Ball implements Comparable<Ball> {
        int index, color, size;

        Ball(int index, int color, int size) {
            this.index = index;
            this.color = color;
            this.size = size;
        }

        public int compareTo(Ball o) {
            return this.size - o.size;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        Ball[] balls = new Ball[N];
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int color = Integer.parseInt(st.nextToken());
            int size = Integer.parseInt(st.nextToken());
            balls[i] = new Ball(i, color, size);
        }

        Arrays.sort(balls); // 크기 기준 정렬

        int[] answer = new int[N];
        int[] colorSum = new int[N + 1]; // 색별 누적합
        int totalSum = 0;
        int j = 0;

        for (int i = 0; i < N; i++) {
            Ball now = balls[i];

            // 현재 공보다 작거나 같은 공까지 누적
            while (balls[j].size < now.size) {
                totalSum += balls[j].size;
                colorSum[balls[j].color] += balls[j].size;
                j++;
            }

            // 전체 누적합 - 같은 색 누적합
            answer[now.index] = totalSum - colorSum[now.color];
        }

        // 출력
        StringBuilder sb = new StringBuilder();
        for (int ans : answer) {
            sb.append(ans).append("\n");
        }
        System.out.print(sb);
    }
}