import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        while (T-- > 0) {
            int N = Integer.parseInt(br.readLine());
            String[] arr = new String[N];
            for (int i = 0; i < N; i++) arr[i] = br.readLine();
            Arrays.sort(arr);

            boolean isContains = false;
            for (int i = 0; i < N - 1; i++) {
                if (arr[i + 1].startsWith(arr[i])) {
                    isContains = true;
                    break;
                }
            }
            sb.append(isContains ? "NO\n" : "YES\n");
        }
        System.out.println(sb);
    }
}
