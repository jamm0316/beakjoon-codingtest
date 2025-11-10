import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        // 좌표 최대값을 알아야 배열 크기를 결정할 수 있음
        int maxX = 0;
        int[] ice = new int[1_000_001]; // 문제에서 xi 최대가 1,000,000이므로 미리 크게 잡아도 됨

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int g = Integer.parseInt(st.nextToken()); // 얼음 양
            int x = Integer.parseInt(st.nextToken()); // 위치

            ice[x] += g; // 같은 위치에 여러 양동이가 있을 수도 있으니 누적
            if (x > maxX) maxX = x;
        }

        // 슬라이딩 윈도우: [l, r] 구간에서 r-l <= 2K를 만족하도록 유지
        int l = 0;
        long currentSum = 0;
        long maxSum = 0;
        int maxDistance = 2 * K; // 허용 구간 길이

        for (int r = 0; r <= maxX; r++) {
            currentSum += ice[r];

            // 구간 길이가 2K를 넘으면 왼쪽 포인터 이동
            while (r - l > maxDistance) {
                currentSum -= ice[l];
                l++;
            }

            if (currentSum > maxSum) {
                maxSum = currentSum;
            }
        }

        System.out.println(maxSum);
    }
}