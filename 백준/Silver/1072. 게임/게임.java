import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        long X = Long.parseLong(st.nextToken()); // 총 게임 수
        long Y = Long.parseLong(st.nextToken()); // 이긴 게임 수
        
        int Z = (int) (Y * 100 / X); // 현재 승률

        // 승률이 이미 99% 이상인 경우 절대 변하지 않음
        if (Z >= 99) {
            System.out.println(-1);
            return;
        }

        long left = 0, right = 1_000_000_000; // 최소 0부터 최대 10억 게임까지 탐색
        long answer = -1;

        while (left <= right) {
            long mid = (left + right) / 2; // 중간값
            long newZ = (Y + mid) * 100 / (X + mid); // 새로운 승률 계산
            
            if (newZ > Z) {
                answer = mid; // 가능한 경우
                right = mid - 1; // 더 작은 값 탐색
            } else {
                left = mid + 1; // 더 큰 값 탐색
            }
        }

        System.out.println(answer);
    }
}