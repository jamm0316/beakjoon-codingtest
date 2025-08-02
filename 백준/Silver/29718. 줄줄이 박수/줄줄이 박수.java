import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken()); // 행 수
        int M = Integer.parseInt(st.nextToken()); // 열 수

        int[][] clap = new int[N][M];

        // 각 좌석의 박수 횟수 입력 받기
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                clap[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int A = Integer.parseInt(br.readLine()); // 선택할 열의 개수

        // 슬라이딩 윈도우를 위해 각 행별로 누적합 구하기
        int[][] prefix = new int[N][M + 1]; // 열 인덱스 1부터 시작하기 위해 M+1

        for (int i = 0; i < N; i++) {
            for (int j = 1; j <= M; j++) {
                prefix[i][j] = prefix[i][j - 1] + clap[i][j - 1];
            }
        }

        int max = 0;

        // 열 구간 슬라이딩 윈도우
        for (int start = 1; start <= M - A + 1; start++) {
            int end = start + A - 1;
            int sum = 0;

            for (int i = 0; i < N; i++) {
                sum += prefix[i][end] - prefix[i][start - 1];
            }

            max = Math.max(max, sum);
        }

        System.out.println(max);
    }
}