import java.io.*;
import java.util.*;

public class Main {

    static int N, d, k, c;
    static int[] sushi;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken()); // 접시 수
        d = Integer.parseInt(st.nextToken()); // 초밥 가짓수
        k = Integer.parseInt(st.nextToken()); // 연속 먹을 접시 수
        c = Integer.parseInt(st.nextToken()); // 쿠폰 번호

        sushi = new int[N];
        for (int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] count = new int[d + 1]; // 초밥 종류별 개수
        int kind = 0; // 현재 윈도우 내 종류 수

        // 초기 윈도우 설정
        for (int i = 0; i < k; i++) {
            if (count[sushi[i]]++ == 0) kind++;
        }

        int max = kind + (count[c] == 0 ? 1 : 0);

        // 슬라이딩 윈도우
        for (int i = 1; i < N; i++) {
            // 왼쪽 초밥 제거
            int left = sushi[i - 1];
            if (--count[left] == 0) kind--;

            // 오른쪽 초밥 추가
            int right = sushi[(i + k - 1) % N];
            if (count[right]++ == 0) kind++;

            int total = kind + (count[c] == 0 ? 1 : 0);
            max = Math.max(max, total);
        }

        System.out.println(max);
    }
}
