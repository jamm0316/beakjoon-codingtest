import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine().trim());
        int[] arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(arr);

        int maxCnt = 0;
        int j = 0;

        for (int i = 0; i < N; i++) {
            while (j < N && arr[j] - arr[i] <= 4) {
                j++;
            }
            int cnt = j - i;
            if (cnt > maxCnt) {
                maxCnt = cnt;
            }
        }

        int answer = 0;
        if (maxCnt >= 5) {
            answer = 0;           // 이미 5개 이상 연속 가능
        } else {
            answer = 5 - maxCnt;  // 부족한 개수 채우기
        }

        System.out.println(answer);
    }
}
