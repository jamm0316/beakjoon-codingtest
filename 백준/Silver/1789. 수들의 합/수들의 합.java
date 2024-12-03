import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws IOException {
BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        long N = Long.parseLong(st.nextToken());
        long count = 0L;
        while (N > count) {
            count++;
            N -= count;
        }
        System.out.println(count);
    }
}
