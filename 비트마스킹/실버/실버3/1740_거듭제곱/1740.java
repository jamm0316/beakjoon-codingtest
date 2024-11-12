import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 입력 받기
        long N = Long.parseLong(br.readLine().trim());
        
        // 결과를 저장할 변수
        long result = 0;
        long power = 1; // 3^0
        
        while (N > 0) {
            // N의 최하위 비트가 1인지 확인
            if ((N & 1) == 1) {
                result += power;
            }
            // 다음 제곱수로 이동
            power *= 3;
            // N을 오른쪽으로 한 비트 이동
            N >>= 1;
        }
        
        // 결과 출력
        System.out.println(result);
    }
}
