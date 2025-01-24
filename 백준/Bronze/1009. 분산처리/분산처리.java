import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        int N = Integer.parseInt(br.readLine()); // 테스트 케이스 수
        
        for (int i = 0; i < N; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            
            // a의 마지막 자릿수만 고려
            int base = a % 10;

            if (base == 0) {
                System.out.println(10); // 마지막 자릿수가 0이면 항상 10번 컴퓨터
                continue;
            }

            // 주기 계산
            List<Integer> cycle = new ArrayList<>();
            int current = base;

            while (!cycle.contains(current)) {
                cycle.add(current);
                current = (current * base) % 10;
            }

            // 주기의 길이
            int cycleLength = cycle.size();

            // (b - 1) % cycleLength 번째 값이 마지막 자릿수
            int resultIndex = (b - 1) % cycleLength;
            System.out.println(cycle.get(resultIndex));
        }
    }
}