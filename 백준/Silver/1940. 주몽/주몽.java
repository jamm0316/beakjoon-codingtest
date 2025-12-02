import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        // 빠른 입력을 위한 설정
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 1. 입력 받기
        int N = Integer.parseInt(br.readLine()); // 재료 개수
        int M = Integer.parseInt(br.readLine()); // 갑옷을 만드는 데 필요한 수

        int[] arr = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(st.nextToken());
        }

        // 2. 정렬
        Arrays.sort(arr);

        // 3. 투 포인터 초기화
        int left = 0;          // 가장 작은 값에서 시작
        int right = N - 1;     // 가장 큰 값에서 시작
        int count = 0;         // 만들 수 있는 갑옷 개수

        // 4. 두 포인터가 교차하기 전까지 반복
        while (left < right) {
            int sum = arr[left] + arr[right];

            if (sum == M) {
                // 갑옷 하나 완성
                count++;
                // 이 두 재료는 사용했으니 다음 후보로 이동
                left++;
                right--;
            } else if (sum < M) {
                // 합이 너무 작으므로 더 큰 값을 선택해야 함
                left++;
            } else { // sum > M
                // 합이 너무 크므로 더 작은 값을 선택해야 함
                right--;
            }
        }

        // 5. 결과 출력
        System.out.println(count);
    }
}