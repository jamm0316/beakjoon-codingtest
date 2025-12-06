import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
        }

        // 2. (값, 원래 인덱스) 쌍 만들기
        // pairs[k][0] = 값, pairs[k][1] = 원래 인덱스
        int[][] pairs = new int[N][2];
        for (int i = 0; i < N; i++) {
            pairs[i][0] = A[i]; // 값
            pairs[i][1] = i;    // 원래 인덱스
        }

        // 3. 값 오름차순, 값이 같으면 원래 인덱스 오름차순으로 정렬
        Arrays.sort(pairs, (o1, o2) -> {
            if (o1[0] != o2[0]) {
                return Integer.compare(o1[0], o2[0]); // 값 기준
            }
            return Integer.compare(o1[1], o2[1]);     // 인덱스 기준
        });

        // 4. 정렬된 순서를 바탕으로 P를 채우기
        int[] P = new int[N];
        for (int pos = 0; pos < N; pos++) {
            int originalIndex = pairs[pos][1];
            P[originalIndex] = pos;
        }

        // 5. 결과 출력
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < N; i++) {
            sb.append(P[i]);
            if (i + 1 < N) sb.append(' ');
        }
        System.out.println(sb);
    }
}
