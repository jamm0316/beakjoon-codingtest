import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int[] arrays = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int count = 0;

        for (int eachNumber : arrays) {
            if (isPrime(eachNumber)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private static boolean isPrime(int eachNumber) {
        if (eachNumber < 2) {
            return false;
        } else {
            for (int i = 2; i <= Math.sqrt(eachNumber); i++) {
                if (eachNumber % i == 0) {
                    return false;
                }
            }
        }
        return true;
    }
}
