import java.io.*;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine()); // 색종이 개수

        // paper[y][x] : (x, y)가 검정이면 1, 아니면 0
        // y: 0 = 아래쪽, 99 = 위쪽이라고 생각해도 되고,
        // 실제로는 방향 상관없이 "연속된 1"만 중요함
        int[][] paper = new int[100][100];

        for (int k = 0; k < n; k++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken()); // 왼쪽 변과의 거리
            int y = Integer.parseInt(st.nextToken()); // 아래쪽 변과의 거리

            // 색종이는 (x ~ x+9), (y ~ y+9) 영역을 덮음
            for (int i = x; i < x + 10; i++) {
                for (int j = y; j < y + 10; j++) {
                    paper[j][i] = 1; // j = row(y), i = col(x)
                }
            }
        }

        int[] height = new int[100];
        int maxArea = 0;

        // 각 row(=y)를 바닥으로 하는 히스토그램을 만들고,
        // 그 히스토그램에서 최대 직사각형 넓이를 구함
        for (int row = 0; row < 100; row++) {
            for (int col = 0; col < 100; col++) {
                if (paper[row][col] == 1) {
                    height[col] += 1; // 위로 연속된 1의 개수 누적
                } else {
                    height[col] = 0;  // 끊기면 0으로 초기화
                }
            }

            // 현재 row를 바닥으로 하는 히스토그램에서 최대 직사각형 넓이 계산
            maxArea = Math.max(maxArea, largestRectangleInHistogram(height));
        }

        System.out.println(maxArea);
    }

    // 히스토그램에서 최대 직사각형 넓이를 구하는 함수 (스택 이용 O(n))
    private static int largestRectangleInHistogram(int[] height) {
        int n = height.length;
        Deque<Integer> stack = new ArrayDeque<>();
        int maxArea = 0;

        for (int i = 0; i < n; i++) {
            // 현재 막대가 스택 top보다 작아지는 시점에서,
            // 스택 top을 높이로 하는 직사각형 넓이 계산
            while (!stack.isEmpty() && height[stack.peek()] > height[i]) {
                int h = height[stack.pop()];
                int width;
                if (stack.isEmpty()) {
                    width = i;         // 0 ~ i-1 까지
                } else {
                    width = i - stack.peek() - 1; // (stack.peek()+1) ~ (i-1)
                }
                maxArea = Math.max(maxArea, h * width);
            }
            stack.push(i);
        }

        // 끝까지 왔을 때 스택에 남아있는 막대들 처리
        int i = n;
        while (!stack.isEmpty()) {
            int h = height[stack.pop()];
            int width;
            if (stack.isEmpty()) {
                width = i;            // 0 ~ n-1
            } else {
                width = i - stack.peek() - 1;
            }
            maxArea = Math.max(maxArea, h * width);
        }

        return maxArea;
    }
}