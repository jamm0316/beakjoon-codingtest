import java.io.*;
import java.util.*;

public class Main {
    static class Point {
        long x, y;
        Point(long x, long y) {
            this.x = x;
            this.y = y;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Point[] points = new Point[N];

        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            long x = Long.parseLong(st.nextToken());
            long y = Long.parseLong(st.nextToken());
            points[i] = new Point(x, y);
        }

        double area = shoelaceFormula(points);
        System.out.printf("%.1f\n", area);  // 소수점 첫째 자리까지 출력
    }

    private static double shoelaceFormula(Point[] points) {
        long sum1 = 0;
        long sum2 = 0;
        int N = points.length;

        for (int i = 0; i < N; i++) {
            Point current = points[i];
            Point next = points[(i + 1) % N]; // 마지막 점 다음은 첫 점으로 연결
            sum1 += current.x * next.y;
            sum2 += current.y * next.x;
        }

        return Math.abs(sum1 - sum2) / 2.0;
    }
}