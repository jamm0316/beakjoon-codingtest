import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
        //12345 -> 120 1
        //12345678910 -> 3628800 2
        //1~15 ->1307674368000 3
        //1~20 -> 2432902008176640000 4
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int count = 0;
        int five = 5;
        while (N >= five) {
            count += N / five;
            five *= 5;
        }
        System.out.println(count);
    }
}
