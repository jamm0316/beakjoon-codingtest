import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuffer sb = new StringBuffer();
        int query = Integer.parseInt(br.readLine());
        int[] arr = new int[query];

        for (int i = 0; i < query; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        arr = Arrays.stream(arr)
                .sorted()
                .toArray();
        
        for (int i : arr) {
            sb.append(i + "\n");
        }
        System.out.println(sb);
    }
}
