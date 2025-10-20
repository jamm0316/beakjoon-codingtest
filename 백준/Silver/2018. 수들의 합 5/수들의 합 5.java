import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int left = 1, right = 1;
        int count = 0;
        long sum = 1;
        while (left <= N) {
            if (sum == N) {
                count++;
                sum -= left++;
            }
            else if (sum < N){
                if (++right > N) break;
                sum += right;
            }
            else if (sum > N) {
                sum -= left++;
            }
        }
        System.out.println(count);
    }
}
