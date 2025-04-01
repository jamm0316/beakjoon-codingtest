import java.io.*;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입출력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int B = Integer.parseInt(st.nextToken());

        int[][] field = new int[N][M];
        int max = 0;
        int min = 256;

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                field[i][j] = Integer.parseInt(st.nextToken());
                max = Math.max(max, field[i][j]);
                min = Math.min(min, field[i][j]);
            }
        }

        int minTime = Integer.MAX_VALUE;
        int resultHeight = -1;

        // 가능한 높이 전부 시도
        for (int h = min; h <= max; h++) {
            int time = 0;
            int blocks = B;

            for (int i = 0; i < N; i++) {
                for (int j = 0; j < M; j++) {
                    int diff = field[i][j] - h;

                    if (diff > 0) {
                        // 제거
                        time += diff * 2;
                        blocks += diff;
                    } else if (diff < 0) {
                        // 추가
                        time += -diff;
                        blocks += diff; // diff는 음수니까 더하면 인벤토리 감소
                    }
                }
            }

            if (blocks >= 0 && time <= minTime) {
                minTime = time;
                resultHeight = h;
            }
        }

        System.out.println(minTime + " " + resultHeight);
    }
}