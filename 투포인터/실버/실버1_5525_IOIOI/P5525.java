import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // PN에서 O의 개수
        int M = Integer.parseInt(br.readLine()); // 문자열 길이
        String S = br.readLine();

        int count = 0;      // 결과 개수
        int pattern = 0;    // 현재 OI 반복 횟수

        for (int i = 1; i < M - 1; i++) {
            if (S.charAt(i - 1) == 'I' && S.charAt(i) == 'O' && S.charAt(i + 1) == 'I') {
                pattern++;
                i++; // 'OI'에서 i+1을 검증하였으므로 'I'까지 건너뜀

                if (pattern == N) {
                    count++;
                    pattern--; // 겹치는 패턴 고려
                }
            } else {
                pattern = 0; // 패턴이 끊겼으면 초기화
            }
        }

        System.out.println(count);
    }
}
