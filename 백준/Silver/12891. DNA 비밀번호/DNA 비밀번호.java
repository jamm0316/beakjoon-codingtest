import java.io.*;
import java.util.*;

public class Main {
    static int[] checkArr = new int[4];       // A, C, G, T 최소 조건
    static int[] myArr = new int[4];          // 현재 윈도우 문자 개수
    static int checkSecret = 0;               // 조건 충족한 문자 수

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int S = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());
        char[] A = br.readLine().toCharArray();

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            checkArr[i] = Integer.parseInt(st.nextToken());
            if (checkArr[i] == 0) checkSecret++;  // 0이면 자동 충족
        }

        // 초기 윈도우 설정
        for (int i = 0; i < P; i++) {
            add(A[i]);
        }

        int result = 0;
        if (checkSecret == 4) result++;

        // 슬라이딩 윈도우
        for (int i = P; i < S; i++) {
            int j = i - P;
            add(A[i]);
            remove(A[j]);
            if (checkSecret == 4) result++;
        }

        System.out.println(result);
    }

    // 문자 추가
    private static void add(char c) {
        int idx = toIndex(c);
        myArr[idx]++;
        if (myArr[idx] == checkArr[idx]) checkSecret++;
    }

    // 문자 제거
    private static void remove(char c) {
        int idx = toIndex(c);
        if (myArr[idx] == checkArr[idx]) checkSecret--;
        myArr[idx]--;
    }

    // 문자 → 인덱스 변환
    private static int toIndex(char c) {
        if (c == 'A') return 0;
        else if (c == 'C') return 1;
        else if (c == 'G') return 2;
        else return 3;
    }
}