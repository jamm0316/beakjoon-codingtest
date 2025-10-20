import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] arr = new int[N];
        IntStream.range(1, N + 1).forEach(i -> arr[i -1] = i);

        int left = 0, right = 1;
        int count = 1;  //해당 숫자 포함이므로 무조건 1개는 확보
        int sum = arr[left];
        while (left < N - 1) {
            if (sum == N) {
                count++;
                sum = arr[++left];
                right = left + 1;
            }
            else if (sum < N){
                sum += arr[right++];
            }
            else if (sum > N) {
                sum = arr[++left];
                right = left + 1;
            }
        }
        System.out.println(count);
    }
}
