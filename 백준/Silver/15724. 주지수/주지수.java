import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // N: 행 수, M: 열 수
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        // 1-based 인덱스를 쓰기 위해 N+1, M+1 크기로 생성
        int[][] map = new int[N + 1][M + 1];
        int[][] psum = new int[N + 1][M + 1];

        // 사람 수 입력
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        // 2차원 누적 합 계산
        // psum[i][j] = (1,1) ~ (i,j) 까지의 합
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                psum[i][j] = psum[i - 1][j]    // 위쪽 사각형
                            + psum[i][j - 1]   // 왼쪽 사각형
                            - psum[i - 1][j - 1] // 두 번 더해진 왼쪽 위 사각형 제거
                            + map[i][j];       // 현재 칸 추가
            }
        }

        // 쿼리 개수 K
        int K = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();

        // 쿼리 처리
        for (int q = 0; q < K; q++) {
            st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            // 직사각형 (x1, y1) ~ (x2, y2)의 합
            int result = psum[x2][y2]
                       - psum[x1 - 1][y2]
                       - psum[x2][y1 - 1]
                       + psum[x1 - 1][y1 - 1];

            sb.append(result).append('\n');
        }

        System.out.print(sb.toString());
    }
}