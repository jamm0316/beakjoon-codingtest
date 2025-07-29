import java.io.*;
import java.util.*;

public class Main {
    static int N, M, count;
    static int[] dnaCondition = new int[4]; // A, C, G, T 최소 개수 조건
    static int[] currentCount = new int[4]; // 현재 윈도우의 문자 개수
    static String DNA;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        DNA = br.readLine();
        dnaCondition = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // 초기 윈도우 설정 (첫 M개 문자)
        for (int i = 0; i < M; i++) {
            addChar(DNA.charAt(i));
        }
        if (checkCondition()) {
            count++;
        }

        // 슬라이딩 윈도우
        for (int i = M; i < N; i++) {
            // 이전 윈도우의 첫 문자 제거
            removeChar(DNA.charAt(i - M));
            // 새로운 문자 추가
            addChar(DNA.charAt(i));
            if (checkCondition()) {
                count++;
            }
        }

        System.out.println(count);
    }

    // 문자 추가
    private static void addChar(char c) {
        if (c == 'A') {
            currentCount[0]++;
        } else if (c == 'C') {
            currentCount[1]++;
        } else if (c == 'G') {
            currentCount[2]++;
        } else if (c == 'T') {
            currentCount[3]++;
        }
    }

    // 문자 제거
    private static void removeChar(char c) {
        if (c == 'A') {
            currentCount[0]--;
        } else if (c == 'C') {
            currentCount[1]--;
        } else if (c == 'G') {
            currentCount[2]--;
        } else if (c == 'T') {
            currentCount[3]--;
        }
    }

    // 조건 확인
    private static boolean checkCondition() {
        for (int i = 0; i < 4; i++) {
            if (currentCount[i] < dnaCondition[i]) {
                return false;
            }
        }
        return true;
    }
}