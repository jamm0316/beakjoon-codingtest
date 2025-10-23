import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] arr = br.readLine().toCharArray();

        int zero = 0, one = 0;
        if (arr[0] == '0') {
            zero++;
        } else {
            one++;
        }

        char prev = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == '0' && arr[i] != prev) {
                zero++;
            } else if (arr[i] == '1' && arr[i] != prev) {
                one++;
            }
            prev = arr[i];
        }

        System.out.println(Math.min(zero, one));
    }
}
