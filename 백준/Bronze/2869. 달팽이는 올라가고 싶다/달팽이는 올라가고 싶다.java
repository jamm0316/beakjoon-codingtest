import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] array = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int climb = array[0];
        int slide = array[1];
        int summit = array[2];

        int result = (summit - slide) / (climb - slide);
        if ((summit - slide) % (climb - slide) != 0) {
            result += 1;
        }
        System.out.println(result);
    }
}
