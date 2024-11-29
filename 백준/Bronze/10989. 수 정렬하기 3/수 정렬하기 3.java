
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int query = Integer.parseInt(br.readLine());

        int[] count = new int[10001];

        for (int i = 0; i < query; i++) {
            count[Integer.parseInt(br.readLine())]++;
        }

        for (int i = 1; i < count.length; i++) {
            while (count[i] > 0) {
                sb.append(i).append("\n");
                count[i]--;
            }
        }

        System.out.print(sb);
    }
}