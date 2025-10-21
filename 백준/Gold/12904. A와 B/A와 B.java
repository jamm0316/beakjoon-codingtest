import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String test = br.readLine();
        String target = br.readLine();

        StringBuilder sb = new StringBuilder(target);
        while (sb.length() > test.length()) {
            char last = sb.charAt(sb.length() - 1);
            sb.deleteCharAt(sb.length() - 1);
            if (last == 'B') {
                sb.reverse();
            }
        }
        System.out.println(sb.toString().equals(test) ? 1 : 0);
    }
}
